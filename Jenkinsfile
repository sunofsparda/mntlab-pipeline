node('host'){
//I'LL USE node('host'){ WHEN I TRY IT ON MNT-LAB
//DECLARE ENVIRONMENT VARIABLES
 withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin","JAVA_HOME=${tool 'java8'}","PATH+JAVA=${tool 'java8'}/bin"])
 {
//CHECKOUT GIT BRANCH
            stage('Preparation')
            {
                echo 'Checking out git branch'
                checkout([$class: 'GitSCM', branches: [[name: 'origin/imanzhulin']], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline.git']]])
            }
        //CLEANING WORKSPACE AND BUILDING GRADLE
          stage('Building code')
            {
                echo 'Building gradle'
                sh 'gradle clean build'
            }
        //PERFORMING TESTS (CUCUMBER    
        stage('Testing')
            {
                echo 'Tests....'
                parallel cucumber: 
                {
                    echo 'Cucumber test'    
			        sh 'gradle cucumber'
                },
		        junit:
		        {
		            echo 'Junit test'    
			        sh 'gradle test'
		        },
		        jacoco:
		        {
		            echo 'Jacoco test'    
			        sh 'gradle jacoco'
		        }
            }
        stage('Triggering job and fetching')
            {
                echo 'Triggering job..'
		sh 'echo ${BRANCH_NAME}'
		build job: "MNTLAB-${BRANCH_NAME}-child1-build-job", parameters: [string(name: 'BRANCH_NAME', value: "origin/${BRANCH_NAME}")]
               	step ([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job"]);
            }

         stage('Packaging and Publishing') 
            {
//CREATES AN APP GRADLE-SIMPLE.JAR, EXTRACTS ALL THE FILES FROM DSL-ARCHIVE, ADDS AN APP AND CREATES A NEW ARCHIVE		    
    		sh 'cp ${WORKSPACE}/build/libs/$(basename $WORKSPACE).jar ${WORKSPACE}/gradle-simple.jar'
       		sh 'tar -zxvf ${BRANCH_NAME}_dsl_script.tar.gz jobs.groovy'
        	sh 'tar -czf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile gradle-simple.jar'
        	archiveArtifacts "pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz"
            }

        stage('Asking for manual approval') 
            {
                echo "Asking for permission..."
//IF YOU DON'T PUSH PROCEED, IT WILL WAIT FOR 5 MIN OT WILL ABORT LATER
		timeout(time:5, unit:'MINUTES')
		input message: 'Do you agree to start deployment?', submitter: 'submitter'
            }
        stage('Deploying') 
            {
                echo 'Deploying an application'
		sh 'java -jar gradle-simple.jar'
            }
   /*     stage('Sending status') 
            {
                echo 'Finished'
            }
*/
 }
}
