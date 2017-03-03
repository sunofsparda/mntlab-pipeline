node ('host'){
  stage('Preparation (Checking out)') {
                git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'mburakouski'
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
   echo 'Hello World'
  }    
  stage ('Deployment'){
   echo 'Hello World'
  }
  stage ('Sending status'){
   echo 'Hello World'
  }
 }
