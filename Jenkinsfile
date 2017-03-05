node {
     tool name: 'gradle3.3', type: 'gradle';
     tool name: 'java8', type: 'jdk';
  stage('Preparation')
   {
   sh 'which java'
   sh 'echo $JAVA_HOME'
    	echo "##########Preparation##########"
    	checkout([$class: 'GitSCM', branches: [[name: '*/yskrabkou']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline']]])
   }

  stage('Building code') 
   {
   		echo "##########Building code##########"
   		sh 'chmod +x gradlew'
   		sh './gradlew build'
   }

   stage('Testing') 
   {
   		echo "##########Testing##########"
   		parallel (
   		unit_tests: {sh './gradlew build'},
   		jacoco_tests: {sh './gradlew jacoco'},
   		cucumber_tests: {sh './gradlew cucumber'}
   		)
   }

   stage('Packaging and Publishing results') 
   {
   		echo "##########Packaging and Publishing results##########"
   		build job: 'MNTLAB-yskrabkou-child1-build-job', parameters: [[$class: 'GitParameterValue', name: 'BRANCH_NAME', value: 'origin/yskrabkou']]
   		sh 'pwd'
   		sh 'ls -R'
   		sh 'cp build/libs/pipeline_project.jar .'
   		sh 'cp  ../MNTLAB-yskrabkou-child1-build-job/jobs.groovy .'
   		sh 'tar czvf pipeline-yskrabkou-46.tar.gz pipeline_project.jar jobs.groovy Jenkinsfile'
   		archiveArtifacts artifacts: 'pipeline-yskrabkou-46.tar.gz', excludes: null

   }

    stage('Asking for manual approval') 
   {
   		echo "Checking"
   }

   stage('Deployment') 
   {
   		echo "Checking"
   }

   stage('Sending status') 
   {
   		echo "Checking"
   }


  }