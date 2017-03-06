node ('master') {
   
 stage('Prepare for Deploy:')
   {
   sh 'which java'
   sh 'echo $JAVA_HOME'
      echo BUILD_NUMBER
      echo WORKSPACE
   tool name: 'gradle', type: 'gradle'
   def gradletool = tool 'gradle'

  git branch: 'aslesarenka', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
   }

  stage('Build source:') 
   {
   		sh 'chmod +x gradlew'
   		sh './gradlew build'
   }

   stage('Parallel Testing') 
   {
   		parallel (
   		unit: {sh './gradlew build'},
   		jacoco: {sh './gradlew jacoco'},
   		cucumber: {sh './gradlew cucumber'}
   		)
   }

stage ('Package')
{echo "skip"}

    stage('Approve for Deploy:') 
   {
                timeout(time:60, unit:'SECONDS') 
                {
                    input message:'New package are ready for Deploy.Your decision?'
                }
   }

   stage('Deployment') 
   {
   		echo "skip"
   }

   stage('Sending Final Status') 
   {
   		echo "skip"
   }


  }
