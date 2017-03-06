node ('host') {
   
 stage('Preparation')
   {   
    tool name: 'java8', type: 'jdk'
	tool name: 'gradle3.3', type: 'gradle'
	env.JAVA_HOME="${tool 'java8'}"
	env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
	env.GRADLE_HOME="${tool 'gradle3.3'}"
    env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"
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
   artefactName = sh (script: "basename ${WORKSPACE}" + '.jar', returnStdout: true) 
   echo "ARTEFACT NAME: ${artefactName}"
   		echo "##########Packaging and Publishing results##########"
   		build job: 'MNTLAB-yskrabkou-child1-build-job', parameters: [[$class: 'GitParameterValue', name: 'BRANCH_NAME', value: 'yskrabkou'], string(name: 'WORKSPACE', value: "${WORKSPACE}")]
   		step ([$class: 'CopyArtifact', projectName: 'MNTLAB-yskrabkou-child1-build-job', filter: 'yskrabkou_dsl_script.tar.gz']);
   	    sh "tar -zxf yskrabkou_dsl_script.tar.gz"
   		sh "cp build/libs/\$(basename \${WORKSPACE}).jar ."
   		echo "22##############################"
   		//sh "cp build/libs/$artefactName ./"
   		sh "tar czvf pipeline-yskrabkou-${BUILD_NUMBER}.tar.gz \$(basename \${WORKSPACE}).jar jobs.groovy Jenkinsfile"
   		archiveArtifacts artifacts: 'pipeline-yskrabkou-${BUILD_NUMBER}.tar.gz', excludes: null
   }

    stage('Asking for manual approval') 
    timeout(time:8, unit:'HOURS')
   {
   		echo "##########Asking for manual approval##########"
   		//stage 'PO Approval'
        //timeout(time:8, unit:'HOURS') {
        input message:'Approve Deployment?'
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