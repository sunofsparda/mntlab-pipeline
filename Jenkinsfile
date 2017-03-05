node ('host') {
tool name: 'gradle3.3', type: 'gradle'
tool name: 'java8', type: 'jdk'
def jdkHome = tool 'java8'
def gradleHome = tool 'gradle3.3'
currentBuild.result = 'SUCCESS'
def ErrorStep = 'No fails'
try{
	stage('Preparation (Checking out)') {
		try{
		   git url:'https://github.com/MNT-Lab/mnt111lab-pipeline.git', branch:'mburakouski'
		} catch (err) {
			${ErrorStep} = 'Fail with Checking' 
		}
		
	}
	stage ('Building code'){
		
			sh 'chmod +x gradlew'
			sh './gradlew build'
		
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
} catch (err) {
currentBuild.result = 'FAILURE'
}
  	stage ('Sending status'){
     		echo "RESULT: ${currentBuild.result} - ${ErrorStep}"
	}
}
