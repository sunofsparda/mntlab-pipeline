#!groovy

node ('host') {
	tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'
	def JDK_TOOL = tool 'java8'
    def GRADLE_TOOL = tool 'gradle3.3'
    stage('Checking out') {
    	//git url: 'https://github.com/sunofsparda/mntlab-pipeline.git', branch: 'master'
    	git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'acherlyonok'
    }
    stage('Building code') {
       	sg '''
       		export PATH=$PATH:$GRADLE_TOOL
    		export JAVA_HOME=$JDK_TOOL
    		echo $PATH
    		echo $JAVA_HOME
    		chmod +x gradlew
    		./gradlew build
    	''';
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

    