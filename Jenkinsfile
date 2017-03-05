node('host')
{
    tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'

    stage ('Preparation (Checking out from git)')
        {
    	    git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'abilun'
        }
    
    withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin", "PATH+JAVA=${tool 'java8'}/bin","JAVA_HOME=${tool 'java8'}"]) {
        stage ('Building')
        {
            sh '''gradle build'''
        }
        
        stage ('Testing')
        {
        
            parallel junit:
                {sh '''gradle test'''},
                
                jacoco:
                {sh '''gradle jacoco'''},
                
                cucumber:
                {sh '''gradle cucumber'''}
        }
        
        stage ('Triggering')
        {
            echo "$BRANCH_NAME"
            build job: "MNTLAB-${BRANCH_NAME}-child1-build-job", parameters: [[$class: 'StringParameterValue', name: 'BRANCH_NAME', value: "${BRANCH_NAME}"]]
            step ([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job"]);
            sh "tar -zxf ${BRANCH_NAME}*.tar.gz"
        }
        
        stage ('Packaging')
        {
            sh '''cp ${WORKSPACE}/build/libs/$(basename $WORKSPACE).jar ${WORKSPACE}'''
            sh '''tar -czf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile $(basename $WORKSPACE).jar'''
            archiveArtifacts "pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz"
        }
        
        stage('Asking for approval')
        {
            input 'All done. We are ready for deployment. Proceed?'
        }

	    stage('Deployment')
        {
	        sh '''java -jar $(basename ${WORKSPACE}).jar'''
        }
        
        stage('Status') {
            echo 'Successful Deployment!'
        }
    }
}
