node ('host') {
    tool name: 'java8', type: 'jdk'
      tool name: 'gradle3.3', type: 'gradle'
     withEnv(["JAVA_HOME=${ tool 'java8' }", "PATH+GRADLE=${tool 'gradle3.3'}/bin", "PATH+JAVA=${tool 'java8'}/bin"]) {
 stage('Prepare for Deploy:')
   {
 
  git branch: 'aslesarenka', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
   }

  stage('Build source:') 
   {
   		sh 'chmod +x gradle'
   		sh 'gradle build'
   }

   stage('Parallel Testing') 
   {
   		parallel (
   		unit: {sh 'gradle build'},
   		jacoco: {sh 'gradle jacoco'},
   		cucumber: {sh 'gradle cucumber'}
   		)
   }

stage ('Trigerred')
{   
 build job: 'MNTLAB-aslesarenka-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'aslesarenka')], quietPeriod: 0
//build job: 'MNTLAB-aslesarenka-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'aslesarenka'),string(name: 'WORKSPACE', value: "${WORKSPACE}")]
    step ([$class: 'CopyArtifact',
          projectName: 'MNTLAB-aslesarenka-child1-build-job',
          filter: 'aslesarenka_dsl_script.tar.gz']);     
build job: 'MNTLAB-aslesarenka-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'aslesarenka'),string(name: 'WORKSPACE', value: "${WORKSPACE}")]
       //     step ([$class: 'CopyArtifact',projectName: 'MNTLAB-aslesarenka-child1-build-job',filter: 'aslesarenka_dsl_script.tar.gz']);
         //   sh 'tar -zxf aslesarenka_dsl_script.tar.gz'
}
stage ('Package')
{

          sh '''
                cp ${WORKSPACE}/build/libs/$(basename "$PWD").jar ${WORKSPACE}/aslesarenka-${BUILD_NUMBER}.jar
                tar -zxvf aslesarenka_dsl_script.tar.gz jobs.groovy
                tar -czf pipeline-aslesarenka-${BUILD_NUMBER}.tar.gz jobs.groovy aslesarenka-${BUILD_NUMBER}.jar
                ''';
                archiveArtifacts artifacts: "pipeline-aslesarenka-${BUILD_NUMBER}.tar.gz"
           
}

    stage('Approve for Deploy:') 
   {
                timeout(time:60, unit:'SECONDS') 
                {
                    input message:'New package are ready for Deploy.Your decision?'
                }
   }

   stage('Deployment') 
   {

    sh 'java -jar aslesarenka-${BUILD_NUMBER}.jar'

   }

   stage('Sending Final Status') 
   {
        echo 'Deployment success!'
   }


  }
}
