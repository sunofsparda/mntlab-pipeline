node  {
   stage('Preparation')
   {
    	echo "Start"
    	checkout([$class: 'GitSCM', branches: [[name: '*/yskrabkou']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline']]])
   }

  stage('Building code') 
   {
   		echo "Building"
   		tool name: 'gradle3.3', type: 'gradle'
   		sh 'chmod +x gradlew'
   		sh './gradlew build'
   }

   stage('Testing') 
   {
   		echo "Testing"
   		parallel (
   		Unit_tests: {sh './gradlew test'},
   		Jacoco_tests: {sh './gradlew jacoco'},
   		Cucumber_tests: {sh './gradlew cucumber'}
   		)
   }

   stage('Packaging and Publishing results') 
   {
   		echo "Deployment"
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