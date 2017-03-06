node ('host') {
prepStatus = "\nPreparation Stage: [FAIL]"
buildStatus = "\nBuilding code Stage: [FAIL]"
testStatus = "\nTesting Stage: [FAIL]"
packStatus = "\nPackaging and Publishing results Stage: [FAIL]"
askStatus = "\nAsking for manual approval Stage: [FAIL]"
deplStatus = "\nDeployment Stage: [FAIL]"
trigStatus = "\nTriggering job and fetching artefact Stage: [FAIL]"


try {

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

    prepStatus = "\nPreparation Stage: [OK]"
   }

  stage('Building code') 
   {
   		echo "##########Building code##########"
   		sh 'chmod +x gradlew'
   		sh './gradlew build'

   		buildStatus = "\nBuilding code Stage: [OK]"
   }

   stage('Testing') 
   {
   		echo "##########Testing##########"
   		parallel (
   		unit_tests: {sh './gradlew build'},
   		jacoco_tests: {sh './gradlew jacoco'},
   		cucumber_tests: {sh './gradlew cucumber'}
   		)

   		testStatus = "\nTesting Stage: [OK]"
   }

    stage('Triggering job and fetching artefact after finishing') 
   {
    	echo "##########Triggering job and fetching artefact##########"
   	    trigStatus = "\nTriggering job and fetching artefact Stage: [OK]"
   	    build job: 'MNTLAB-yskrabkou-child1-build-job', parameters: [[$class: 'GitParameterValue', name: 'BRANCH_NAME', value: 'yskrabkou'], string(name: 'WORKSPACE', value: "${WORKSPACE}")]
   	    step ([$class: 'CopyArtifact', projectName: 'MNTLAB-yskrabkou-child1-build-job', filter: 'yskrabkou_dsl_script.tar.gz']);
   }

     stage('Packaging and Publishing results') 
   {
   artefactName = sh (script: "basename ${WORKSPACE}" + '.jar', returnStdout: true) 
   echo "ARTEFACT NAME: ${artefactName}"
   		echo "##########Packaging and Publishing results##########"   	
   		
   	    sh "tar -zxf yskrabkou_dsl_script.tar.gz"
   		sh "cp build/libs/\$(basename \${WORKSPACE}).jar ."
   		echo "22##############################"
   		//sh "cp build/libs/$artefactName ./"
   		sh "tar czvf pipeline-yskrabkou-${BUILD_NUMBER}.tar.gz \$(basename \${WORKSPACE}).jar jobs.groovy Jenkinsfile"
   		archiveArtifacts artifacts: 'pipeline-yskrabkou-${BUILD_NUMBER}.tar.gz', excludes: null

   		packStatus = "\nPackaging and Publishing results Stage: [OK]"
   }

    stage('Asking for manual approval') 
    timeout(time:8, unit:'HOURS')
   {
   		echo "##########Asking for manual approval##########"
   	    input message:'Approve Deployment?'

        askStatus = "\nAsking for manual approval Stage: [OK]"
   }

   stage('Deployment') 
   {
    echo "##########Deployment##########"
   	sh 'java -jar \$(basename \${ORKSPACE}).jar'

   	 deplStatus = "\nDeployment Stage: [OK]"
   }

}
catch (ex)
{
    echo "###################SOMETHING FAIL################"
}
   stage('Sending status') 
   {
   		echo "###################Sending status###################"
   		echo prepStatus + buildStatus + testStatus + packStatus + askStatus + deplStatus
   }


  }