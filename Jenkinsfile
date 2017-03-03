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
                sh "echo ${JENKINS_HOME}"
            }
            sh 'gradle build --info'
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
