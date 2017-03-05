node ('host') {
tool name: 'gradle3.3', type: 'gradle'
tool name: 'java8', type: 'jdk'
def jdkHome = tool 'java8'
def gradleHome = tool 'gradle3.3'
currentBuild.result = 'SUCCESS'
	stage('Preparation (Checking out)') { 
		try {
       		    git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'mburakouski'
		}
		catch (err) {
			currentBuild.result = 'Fail with Checking out'
		}
	}
	stage ('Building code'){
		try {
			sh 'chmod +x gradlew'
			sh './gradlew build'
		} catch (err) {
			currentBuild.result = "Fail with Building code" 
		}
	}	
  	stage ('Testing'){
    		parallel JUnit: {
      			sh './gradlew test'
    		}, Jacoco: {
      			sh './gradlew jacoco'
    		}, Cucumber: {
      			sh './gradlew jacoco'
    	}
    	failFast: true|false    
  	}
  	stage ('Triggering job and fetching artefact after finishing'){
   		echo 'Hello World'
  	}
  	stage ('Packaging and Publishing results'){
   		archive 'Jenkinsfile'
  	}
  	stage ('Asking for manual approval'){
   		input "Deployment?"
  	}    
  	stage ('Deployment'){
   		echo 'Hello World'
  	}
  	stage ('Sending status'){
     		echo "RESULT: ${currentBuild.result}"
	}
}
