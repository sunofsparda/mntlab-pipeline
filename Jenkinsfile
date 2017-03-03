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
               tool('gradle','java8')
               sh 'gradle build'
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
//asdasdasd
