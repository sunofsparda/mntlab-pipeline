node {
	currentBuild.result = 'SUCCESS'
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
			echo 'triggering'
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
