node('host')
{
    tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'

    stage ('Preparation (Checking out from git)')
        {
    	    git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'abilun'
        }
    
    withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin","JAVA_HOME=${tool 'java8'}"]) {
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
            echo '$BRANCH_NAME'
            build job: 'MNTLAB-abilun-child1-build-job', parameters: [[$class: 'StringParameterValue', name: 'BRANCH_NAME', value: "${BRANCH_NAME}"]]
            step ([$class: 'CopyArtifact', projectName: 'MNTLAB-abilun-child1-build-job']);
            sh 'tar -zxf *.tar.gz'
        }
    }

}
