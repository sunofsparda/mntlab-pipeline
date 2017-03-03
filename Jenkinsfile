pipeline {
    agent any
    stages {
        stage("Preparation")
        stage('Build') {
            steps {
               git branch: 'pheraska', changelog: false, poll: false, url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
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
