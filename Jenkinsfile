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
				step
				{
					sh 'echo "build gradle dew" ' //вывод для того чтобы хоть отдаленно понимать где фейлится
					sh returnStatus: true, script: 'bash gradlew build' //билд зис //долго билдается что-то
				}
			}




			stage('testing')
			{
				step
				{
					parallel JUnit:
						{
							sh 'bash gradlew test'
						}, //тест зис ин параллел виз ждиюнит, якоко, огурец
						Jacoco:
						{
							sh 'bash gradlew jacoco'
						},
						Cucumber:
						{
							sh 'bash gradlew cucumber'
						}
					failFast: true | false //
				}
			}

			//parallel map //
			//parallel spam: map['spam'], eggs: map['eggs'], failFast: true  
			//parallel map, failFast: true // 




			stage('Starting child job')
			{
				build job: "MNTLAB-${BRANCH_NAME}-child1-build-job", parameters: [[$class: 'StringParameterValue', name: "${BRANCH_NAME}", value: "${BRANCH_NAME}"]]
				step([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job", filter: '*.tar.gz']);
			}


			stage('create archive and stuff into archive')
			{
				step
				{
					sh 'echo "create archive and stuff into archive" ' //падает с трещиной от шеи до жопы
					sh 'cp ${WORKSPACE}/build/libs/$(basename "${WORKSPACE}").jar ${WORKSPACE}' //постоянно еб твою мать
					sh 'tar xvzf ${BRANCH_NAME}_dsl_script.tar.gz' //распаковали и достали jobs.groovy
					sh 'tar cvzf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz Jenkinsfile jobs.groovy *.jar' //сделаем тарку
					archiveArtifacts 'pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz' //делаем доступным
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
					input "Requesting permission to deploy"
				}
				catch (err)
				{
					result = "Permisson denied" //без трайкетча не знаю как сделать
				} //без трайкетча просто инпут

			}

			stage('Deployment')
			{
				try
				{
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
			if (currentBuild.result = 'FAILURE'  ) 
			{
   				echo "*****fail*****"
			}else (currentBuild.result = 'SUCCESS')
			{
				echo "*****YEAH BITCH*****"
			} 

		}
	}

}