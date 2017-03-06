node('master')
{ //определить чем будет польоваться пайплайн 
	tool name: 'gradle3.3', type: 'gradle' //
	tool name: 'java8', type: 'jdk'
	currentBuild.result = 'SUCCESS'
	def result = ""
	withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin", "JAVA_HOME=${tool 'java8'}"])
	{
		try
		{

			stage('scm checkout')
			{
				git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'mnikolayev' //сцм чекаут
			}
			stage('build gradle dew')
			{
				try
				{
					sh 'gradle build'
					sh 'ls -al'
				}
				catch (err)
				{
					result = "Fail with Building code"
				}
			}




			stage('testing')
			{
				try
				{
					parallel JUnit:
					{
						sh 'gradle test'
					}, Jacoco:
					{
						sh 'gradle cucumber'
					}, Cucumber:
					{
						sh 'gradle jacoco'
					}
				}
				catch (err)
				{
					result = "Fail with Testing"
				}
				failFast: true | false
			}

			//parallel map
			//parallel spam: map['spam'], eggs: map['eggs'], failFast: true  
			//parallel map, failFast: true  




			stage('Triggering job and fetching artefact after finishing')
			{
				try
				{
					build job: "MNTLAB-${BRANCH_NAME}-child1-build-job", parameters: [
						[$class: 'StringParameterValue', name: "${BRANCH_NAME}", value: "${BRANCH_NAME}"]
					]
					step([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job", filter: '*.tar.gz']);
				}
				catch (err)
				{
					result = "Fail with Triggering job and fetching artefact"
				}
			}


			stage('Packaging and Publishing results')
			{
				try
				{
					sh 'ls -al'
					sh 'cp ${WORKSPACE}/build/libs/$(basename "${WORKSPACE}").jar ${WORKSPACE}'
					sh 'tar xvzf ${BRANCH_NAME}_dsl_script.tar.gz'
					sh 'tar cvzf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz Jenkinsfile jobs.groovy *.jar'
					archiveArtifacts 'pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz'
				}
				catch (err)
				{
					result = "Fail with Packaging and Publishing results"
				}
			}



			//stage ('create archive and stuff into archive')
			//{
			//		step{
			//			sh 'cp ${WORKSPACE}/build/libs/$(basename "${WORKSPACE}").jar ${WORKSPACE}'
			//			sh 'tar xvzf ${BRANCH_NAME}_dsl_script.zip'									
			//			sh 'tar cvzf pipeline-${BUILD_NUMBER}.zip Jenkinsfile jobs.groovy *.jar' 				//сделаем архив
			//			archiveArtifacts 'pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz'						//делаем доступным
			//		}
			//	}

			stage('Request deploy permission')
			{
				try
				{
					input "Do you want to release this build?"
				}
				catch (err)
				{
					result = "Permisson denied"
				} //без трайкетча просто input


			}
			stage('Deployment')
			{
				try
				{
					sh 'ls -al'
					sh 'java -jar $(basename "${WORKSPACE}").jar'
				}
				catch (err)
				{
					result = "Fail with Deployment"
				}
			}

		}
		catch (err)

		{
			currentBuild.result = 'FAILURE'
			echo "*****fail*****"
		}

		stage('Sending status')
		{
			if (currentBuild.result == 'FAILURE')
			{
				echo "*****fail*****"
			}
			else(currentBuild.result == 'SUCCESS')
			{
				echo "*****YEAH BITCH*****"
			}

		}
	}

}