node ('host') {
	tool name: 'gradle3.3', type: 'gradle'
	tool name: 'java8', type: 'jdk'
	def jdkHome = tool 'java8'
    def gradleHome = tool 'gradle3.3'
	def stepFail = 'No fails. Congratulations!'
	
	currentBuild.result = 'SUCCESS'
	try{
	stage('Preparation (Checking out)') {try {
               git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'mburakouski'
		}
		catch{
			echo 'Fail with Checking out!' > ${stepFail}
		}
	}
	stage ('Building code'){
		try {
			sh 'chmod +x gradlew'
			sh './gradlew build'
		} catch{
			echo 'Fail with Building code' > ${stepFail}
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
  } catch (Exception err){
    currentBuild.result = 'FAILURE'
	}
  stage ('Sending status'){
     echo "RESULT: ${currentBuild.result} - ${stepFail}"
 }
 }
