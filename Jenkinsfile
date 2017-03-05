node('master')
{
    tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'
    
    stage 'Preparation (Checking out)'
	    git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'pheraska'
    
    stage 'Building code'
        sh "export PATH=$PATH:${JENKINS_HOME}/tools/hudson.plugins.gradle.GradleInstallation/gradle3.3/bin/"
   	    sh "gradle build"
}







/*
import hudson.model.*;
pipeline {
    agent any
    stages {
        stage('Preparation (Checking out)') {
            steps {
                git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'pheraska'
            }
        }
        stage('Building code') {
            steps {
                tool name: 'java8', type: 'jdk'
                tool name: 'gradle3.3', type: 'gradle'
                
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
//asdasdasdukyfuyf
*/
