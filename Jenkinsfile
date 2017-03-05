#!Jenkinsfile

node {
	tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'

    stage('Checking out') {
    	git url: 'https://github.com/sunofsparda/mntlab-pipeline.git', branch: 'master'
    	//git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'acherlyonok'
    }
    stage('Building code') {
    	sh 'chmod +x gradlew'
    	sh './gradlew build'
    }
    stage('Testing code') {   	
    	parallel Junint: {
    		sh './gradlew test'
    	},
    	Jacoco: {
    		sh './gradlew jacoco'
    	},
    	Cucumber: {
    		sh './gradlew cucumber'
    	}
    }
    stage('Deploy') {
    	echo 'Deploying....'
    }
    
}

