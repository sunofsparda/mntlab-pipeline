node ('host') {
   
 stage('Prepare for Deploy:')
   {
  // sh 'which java'
   //sh 'echo $JAVA_HOME'
     // echo BUILD_NUMBER
     // echo WORKSPACE
      tool name: 'java8', type: 'jdk'
         def gradletool = tool 'java8'
   tool name: 'gradle3.3', type: 'gradle'
   def gradletool = tool 'gradle3.3'

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

stage ('Trigerred')
{   
 build job: 'MNTLAB-aslesarenka-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'aslesarenka')], quietPeriod: 0
    step ([$class: 'CopyArtifact',
          projectName: 'MNTLAB-aslesarenka-child1-build-job',
          filter: 'aslesarenka_dsl_script.tar.gz']);     
// build job: 'MNTLAB-aslesarenka-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'aslesarenka'),string(name: 'WORKSPACE', value: "${WORKSPACE}")]
       //     step ([$class: 'CopyArtifact',projectName: 'MNTLAB-aslesarenka-child1-build-job',filter: 'aslesarenka_dsl_script.tar.gz']);
         //   sh 'tar -zxf aslesarenka_dsl_script.tar.gz'
}
/*stage ('Package')
{

          sh '''
                cp ${WORKSPACE}/build/libs/$(basename "$PWD").jar ${WORKSPACE}/${BRANCH_NAME}-${BUILD_NUMBER}.jar
                tar -zxvf aslesarenka_dsl_script.tar.gz jobs.groovy
                tar -czf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile ${BRANCH_NAME}-${BUILD_NUMBER}.jar
                ''';
                archiveArtifacts artifacts: "pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz"
           
}*/

    stage('Approve for Deploy:') 
   {
                timeout(time:60, unit:'SECONDS') 
                {
                    input message:'New package are ready for Deploy.Your decision?'
                }
   }

   stage('Deployment') 
   {

    sh 'java -jar ${BRANCH_NAME}-${BUILD_NUMBER}.jar'

   }

   stage('Sending Final Status') 
   {
        echo 'Deployment success!'
   }


  }
