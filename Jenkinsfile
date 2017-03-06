node('host') {

	tool name: 'java8', type: 'jdk'
	tool name: 'gradle3.3', type: 'gradle'
	def stageStatus = ''

	withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin", "PATH+JAVA=${tool 'java8'}/bin","JAVA_HOME=${tool 'java8'}"]) {
		try {
			stage('preparation') {
				git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'sivanchic'
			}

			stage ('building code'){
				sh 'chmod +x gradlew'
				sh './gradlew build'
			}
	
			stage ('testing'){
				parallel Cucumber: {
					sh './gradlew cucumber'
				}, JUnit: {
					sh './gradlew test'
				}, Jacoco: {
					sh './gradlew jacoco'
				}
				failFast: true|false    
			}

			stage ('triggering job and fetching'){
				build job: 'MNTLAB-${env.BRANCH_NAME}-child1-build-job', parameters: [[$class: 'StringParameterValue', name: 'BRANCH_NAME', value: "${env.BRANCH_NAME}"]]
				step ([$class: 'CopyArtifact', projectName: 'MNTLAB-${env.BRANCH_NAME}-child1-build-job', filter: '*.tar.gz']);
			}

			stage ('packaging and publishing'){
				archive 'jenkinsfile'
			}
	
			stage ('approval asking'){
				input 'deploy'
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
