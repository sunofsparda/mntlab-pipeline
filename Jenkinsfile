pipeline {
    agent any
    stages {
        stage("Preparation (Checking out)")
        {
            git ([url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'pheraska'])
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
