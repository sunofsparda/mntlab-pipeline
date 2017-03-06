#!groovy

 node ('host') {
//   node {
        env.JAVA_HOME="${tool 'java8'}"
        env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
        sh 'java -version'
        env.GRADLE_HOME="${tool 'gradle3.3'}"
        env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"
        sh "gradle tasks"
    // preparation
    stage ('preparation'){
        checkout([$class: 'GitSCM', branches: [[name: '*/rvashkevich']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline']]])
    }
    //building
    stage ('building') {
        
        sh "gradle clean build"
        
    }
    stage ('testing') {
       parallel cucumber: {
    	sh 'gradle cucumber'
	},      junit: {
		sh 'gradle test'
	},      jacoco: {
		sh 'gradle jacoco'
		}
    }
}
