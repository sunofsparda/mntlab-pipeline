#!Jenkinsfile

master {
    agent any

    stages {
        stage('Checking out') {
            git url: 'https://github.com/sunofsparda/mntlab-pipeline.git', branch: 'master'
        }
        stage('Building code’') {
            sh "./gradlew clean build"
            //gradle build
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
