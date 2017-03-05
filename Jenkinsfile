#!Jenkinsfile

pipeline {
    agent any

    stages {
        stage('Checking out') {
            git clone 'https://github.com/sunofsparda/mntlab-pipeline.git'
        }
        stage('Building code’') {
            gradle build
        }
        stage('Unit Tests') {
            gradle test
        }
        stage('Jacoco Tests') {
            gradle jacoco
        }
        stage('Cucumber Tests') {
            gradle cucumber
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
