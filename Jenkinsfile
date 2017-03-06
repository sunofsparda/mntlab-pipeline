try{

node('host'){
//I'LL USE node('host'){ WHEN I TRY IT ON MNT-LAB
//DECLARE ENVIRONMENT VARIABLES
env.status='Everything is working fine!!!'
//def result = 'SUCCESS'
	
 withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin","JAVA_HOME=${tool 'java8'}","PATH+JAVA=${tool 'java8'}/bin"])
 {
//CHECKOUT GIT BRANCH
            stage('Preparation')
            {
		try
		{
		echo 'Checking out git branch'
                checkout([$class: 'GitSCM', branches: [[name: 'origin/imanzhulin']], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline.git']]])
		}
		catch (err)
		{
		env.status = 'An error with connection to GIT'
		throw err
		}    
            }
        //CLEANING WORKSPACE AND BUILDING GRADLE
          stage('Building code')
            {
                try
		{	
		echo 'Building gradle'
                sh 'gradlezz cleanzz build'
		}
		catch (err)
		{
		env.status = 'An error with a gradle building'
		throw err
		}  
            }
        //PERFORMING TESTS (CUCUMBER    
        stage('Testing')
            {
                try
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
		catch (err)
		{
		env.status = 'An error with passing the tests'
		throw err
		}  
            }
        stage('Triggering job and fetching')
            {
                try
		{    
		echo 'Triggering job..'
		sh 'echo ${BRANCH_NAME}'
		build job: "MNTLAB-${BRANCH_NAME}-child1-build-job", parameters: [string(name: 'BRANCH_NAME', value: "origin/${BRANCH_NAME}")]
               	step ([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job"]);
		}
		catch (err)
		{
		env.result = 'An error with building a job from previous project'
		throw err
		}  
            }

         stage('Packaging and Publishing') 
            {
//CREATES AN APP GRADLE-SIMPLE.JAR, EXTRACTS ALL THE FILES FROM DSL-ARCHIVE, ADDS AN APP AND CREATES A NEW ARCHIVE		    
    		try
		{
		sh 'cp ${WORKSPACE}/build/libs/$(basename $WORKSPACE).jar ${WORKSPACE}/gradle-simple.jar'
       		sh 'tar -zxvf ${BRANCH_NAME}_dsl_script.tar.gz jobs.groovy'
        	sh 'tar -czf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile gradle-simple.jar'
        	archiveArtifacts "pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz"
		}
		catch (err)
		{
		env.status = 'An error with extracting a dsl-archive or archiving a pipeline-archive'
		throw err
		}
            }

        stage('Asking for manual approval') 
            {
                echo "Asking for permission..."
//IF YOU DON'T PUSH PROCEED, IT WILL WAIT FOR 5 MIN OT WILL ABORT LATER
		timeout(time:5, unit:'MINUTES')
		{
			input message: 'Do you agree to start deployment? Proceed?'
		}
            }
        stage('Deploying') 
            {
		try    
		{    
                echo 'Deploying an application'
		sh 'java -jar gradle-simple.jar'
		}
		catch (err)
		{
		env.status = 'An error with deploying an application'
		throw err
		}
            }
      stage('Sending OK status') 
  
            {
		env.Msg = """
		****************************
		PASSED SUCCESSFULLY!!!
		"$status"
		COOL!
		****************************
		"""
		echo "$Msg"
	    }
 }
}
	
}
catch (err) { 
		env.Msg = """
		---------------------------
		FAILED!!! "$status"
		LOOK AT THE NECESSARY SECTION!!!
		---------------------------
		"""
		echo "$Msg"
		throw err
	}
