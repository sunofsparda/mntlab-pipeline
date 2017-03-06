node {

stage('Checking out') { 
    git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'amatveenko'
    }
    
stage('Building code') {
        sh '''      
    	chmod +x gradlew
    	./gradlew build
    	''';
    	}
    
stage('Testing code') {  
    echo 'Deploying....'
    }
 
stage('Deploy') {
    echo 'Deploying....'
    } 
  }
