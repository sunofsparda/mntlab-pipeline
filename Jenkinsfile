#!groovy

node ('host') {
	tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'
	def JDK_HOME = tool 'java8'
    def gradleHome = tool 'gradle3.3'
    stage('Checking out') {
    	//git url: 'https://github.com/sunofsparda/mntlab-pipeline.git', branch: 'master'
    	git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'acherlyonok'
    }
    stage('Building code') {
    	sh 'echo $JDK_HOME'
    	sh 'chmod +x gradlew'
    	sh './gradlew build'
    	//sh './gradlew build --stacktrace --info --debug'
    }
    stage('Testing code') {   	
    	parallel (
    		Junit: {
    		 	sh './gradlew test'
    		},
    		Jacoco: {
    			sh './gradlew jacoco'
    		},
    		Cucumber: {
    			sh './gradlew cucumber'
    		}
    	)
    }
    stage('Deploy') {
    	echo 'Deploying....'
    }
    
}

