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
                echo 'Packaging and Publishing..'
		echo '##############'    
		echo ${WORKSPACE}
		echo '##############'    
		echo ${basename}
		sh "cp build/libs/\$(basename \${WORKSPACE}).jar ."
		sh "tar -czf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile \$(basename \${WORKSPACE}).jar"
		archiveArtifacts "pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz"
            }
/*
        stage('Asking for manual approval') 
            {
                echo 'Do you want to approve?'
            }
        stage('Deploying') 
            {
                echo 'Deploying'
            }
        stage('Sending status') 
            {
                echo 'Finished'
            }
*/
 }
}
