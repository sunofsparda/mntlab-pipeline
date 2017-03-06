node ('host') {
    tool name: 'gradle3.3', type: 'gradle'
    tool name: 'java8', type: 'jdk'
    withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin","JAVA_HOME=${tool 'java8'}","PATH+JAVA=${tool 'java8'}/bin"])
    {

stage('Checking out') { 
    git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'amatveenko'
    }
    
stage('Building code') {
        sh '''      
        gradle clean build
        ''';
        }
        // chmod +x gradlew
    	// ./gradlew build
    	
    
stage('Testing code') 
    parallel junit: {
	sh 'gradle test'}, 
	jacoco: {
	sh 'gradle jacoco'}, 
	cucumber: {
	sh 'gradle cucumber'}
    }
 
stage('Deploy') {
    echo 'Deploying....'
    } 
  }
}
