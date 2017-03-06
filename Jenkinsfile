//IS NECESSARY FOR NODES
node{
//('host'){
//DECLARE ENVIRONMENT VARIABLES
	tool name: 'java8', type: 'jdk'
	tool name: 'gradle3.3', type: 'gradle'
	env.JAVA_HOME="${tool 'java8'}"
	env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
	env.GRADLE_HOME="${tool 'gradle3.3'}"
        env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"
//pipeline{

//agents is a mandatory section
agent any
        stages {
        
        //CHECKOUT GIT BRANCH
            stage('Preparation')
            {
                steps {
                    echo 'Checking out git branch'
                    checkout([$class: 'GitSCM', branches: [[name: '*/imanzhulin']], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline.git']]])
                        }
            }
        //BUILDING GRADLE
          stage('Building code')
            {
                steps {
                    echo 'Building gradle'
                    //sh 'chmod +x ./gradlew'
                    sh '${GRADLE_HOME}/bin/gradle clean build'
                        }
            }
    /*    stage('Testing') {
            steps {
                echo 'Tests....'
            }
        }
         stage('Triggering job and fetching') {
            steps {
                echo 'Triggering job..'
            }
        }
         stage('Packaging and Publishing') {
            steps {
                echo 'Packaging and Publishing..'
            }
        }
        stage('Asking for manual approval') {
            steps {
                echo 'Do you want to approve?'
            }
        }
        stage('Deploying') {
            steps {
                echo 'Deploying'
            }
        }
              stage('Sending status') {
            steps {
                echo 'Finished'
            }
        }*/

    }
}





