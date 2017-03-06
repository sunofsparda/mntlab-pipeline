#!groovy

node ('host') {
	tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'
	//def jdktool = tool 'java8'
    //def gradletool = tool 'gradle3.3'
    withEnv(["JAVA_HOME=${tool 'java8'}","PATH+JAVA=${tool 'java8'}/bin"])

    stage('Checking out') {
    	//git url: 'https://github.com/sunofsparda/mntlab-pipeline.git', branch: 'master'
    	git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'acherlyonok'
        //git checkout scm
    }
    stage('Building code') {
       	sh '''
       		export PATH=$PATH:${gradletool}/bin/
            export JAVA_HOME=$PATH:$jdktool
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
