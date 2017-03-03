pipeline {
    agent any
    stages {
        stage("Preparation (Checking out)")
        {
            git branch: 'pheraska', changelog: false, poll: false, url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
        }
        stage('Build') {
            steps {
               echo "Building.."
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
