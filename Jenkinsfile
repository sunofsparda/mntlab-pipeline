node ('host') {
    tool name: 'gradle3.3', type: 'gradle'
    tool name: 'java8', type: 'jdk'
    {

stage('Checking out') { 
    git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'amatveenko'
    }
    
stage('Building code') {
        sh '''      
        gradle build
    	//chmod +x gradlew
    	//./gradlew build
    	''';
    	}
    
stage('Testing code') {  
    echo 'Deploying....'
    }
 
stage('Deploy') {
    echo 'Deploying....'
    } 
  }
}
