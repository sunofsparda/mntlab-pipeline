node ('host') {
     tool name: 'gradle3.3', type: 'gradle';
     tool name: 'java8', type: 'jdk';
  stage('Preparation')
   {
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
   		phase2: {sh './gradlew jacoco'},
   		phase3: {sh './gradlew cucumber'}
   		)
   }

   stage('"##########Packaging and Publishing results##########') 
   {
   		echo "Deployment"
   		build job: 'MNTLAB-yskrabkou-child1-build-job', parameters: [[$class: 'GitParameterValue', name: 'BRANCH_NAME', value: 'origin/yskrabkou']]
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