node('host') {

	tool name: 'java8', type: 'jdk'
	tool name: 'gradle3.3', type: 'gradle'
	
	withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin", "JAVA_HOME=${tool 'java8'}","PATH+JAVA=${tool 'java8'}/bin"]){
		try {
			stage('preparation') {
				git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'sivanchic'
			}

			stage ('building code'){
				sh 'chmod +x gradlew'
				sh './gradlew build'
			}
	
			stage ('testing'){
				parallel junit: {sh 'gradle test'},
				jacoco: {sh 'gradle jacoco'},
                                cucumber: {sh 'gradle cucumber'}
			}

			stage ('triggering job and fetching'){
				build job: "MNTLAB-${BRANCH_NAME}-child1-build-job", parameters: [[$class: 'StringParameterValue', name: 'BRANCH_NAME', value: "${BRANCH_NAME}"]]
				step ([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job", filter: 'artifacts/*.tar.gz']);
			}

			stage ('packaging and publishing'){

				sh '''
   				tar -xvzf ${BRANCH_NAME}_dsl_script.tar.gz
   				cp build/libs/\$(basename \${WORKSPACE}).jar .
	   			tar czvf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz \$(basename \${WORKSPACE}).jar jobs.groovy Jenkinsfile
				''';

	   			archiveArtifacts artifacts: 'pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz', excludes: null

			}
	
			stage ('approval asking'){
				input 'deploy?'
			}
  
			stage ('deployment'){
				echo 'deploy'
			}

		} catch (Exception err){
			currentBuild.result = 'FAILURE'
		}

		stage ('status send'){
			echo "RESULT: ${currentBuild.result}"
		}
	}
}
