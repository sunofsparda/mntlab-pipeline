node ('master') {
   
 stage('Preparation')
   {
   sh 'which java'
   sh 'echo $JAVA_HOME'
      echo BUILD_NUMBER
      echo WORKSPACE
        tool name: 'gradle', type: 'gradle'
        def gradletool = tool 'gradle'


  git branch: 'aslesarenka', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
   }

  stage('Building code') 
   {
   		sh 'chmod +x gradlew'
   		sh './gradlew build'
   }

   stage('Testing') 
   {
   		parallel (
   		unit: {sh './gradlew build'},
   		jacoco: {sh './gradlew jacoco'},
   		cucumber: {sh './gradlew cucumber'}
   		)
   }

stage ('Package')
{echo "skip"}

    stage('Asking for manual approval') 
   {
   		echo "Checking"
   }

   stage('Deployment') 
   {
   		echo "skip"
   }

   stage('Sending status') 
   {
   		echo "skip"
   }


  }
