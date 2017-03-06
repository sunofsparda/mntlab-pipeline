node('host'){
//I TRY IT ON MNT-LAB
//DECLARE ENVIRONMENT VARIABLES
 withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin","JAVA_HOME=${tool 'java8'}","PATH+JAVA=${tool 'java8'}/bin"])
 {
//CHECKOUT GIT BRANCH
            stage('Preparation')
            {
                    echo 'Checking out git branch'
                    checkout([$class: 'GitSCM', branches: [[name: '*/imanzhulin']], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline.git']]])
            }
        //CLEANING WORKSPACE AND BUILDING GRADLE
          stage('Building code')
            {
                    echo 'Building gradle'
                    sh 'gradle clean build'
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
