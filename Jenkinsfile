#!Groovy

node('host') { timestamps {
    

	tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'
//	def jdktool = tool 'java8'
//  def gradletool = tool 'gradle3.3'
    withEnv(["JAVA_HOME=${tool 'java8'}","PATH+GRADLE=${tool 'gradle3.3'}/bin","PATH+JAVA=${tool 'java8'}/bin"]) {
        // cheking env
        sh '''
            echo $PATH
            echo $JAVA_HOME           
        ''';
        //sh './gradlew build --stacktrace --info --debug'
        
            stage('Preparation (Checking out)') {
            	//git url: 'https://github.com/sunofsparda/mntlab-pipeline.git', branch: 'master'
            	//git url: 'https://github.com/MNT-Lab/mntlab-pipeline.git', branch: 'acherlyonok'
                checkout scm

//                checkout([$class: 'GitSCM', branches: [[name: '*/acherlyonok']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '4da30b2e-bd0e-445d-89bb-3aca0cd96599', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git']]])



            }

            stage('Building code') {
               	sh 'gradle build'
            	//sh './gradlew build --stacktrace --info --debug'
            }

            stage('Testing') {   	
            	parallel (
            		'JUnit Tests': {
            		 	sh 'gradle test'
            		},
            		'Jacoco Tests': {
        			sh 'gradle jacoco'
            		},
            		'Cucumber Tests': {
            			sh 'gradle cucumber'
            		}
            	)
            }
    }

            stage('Triggering job and fetching artefact after finishing') {
                echo 'Building MNTLAB-acherlyonok-child1-build-job'
                build job: 'MNTLAB-acherlyonok-child1-build-job', parameters: [[$class: 'GitParameterValue', name: 'BRANCH_NAME', value: 'acherlyonok']]
                archiveArtifacts '*.tar.gz'             
            }


            stage('Packaging and Publishing results') {
                echo 'NOT WORKING YET........'
            }


            stage('Asking for manual approva') {
            	echo 'NOT WORKING YET........'
            }


            stage('Deployment') {
                echo 'NOT WORKING YET........'
            }


            stage('Sending status') {
                echo 'NOT WORKING YET........'
            }

}}
