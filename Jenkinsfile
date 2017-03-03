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
               sh 'gradle build --info'
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
