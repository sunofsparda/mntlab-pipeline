node('master')
{
    tool name: 'java_8', type: 'jdk'
    tool name: 'gradle_3.3', type: 'gradle'

    stage ('Preparation (Checking out from git)')
        {
    	    git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'abilun'
        }
    
    withEnv(["PATH+GRADLE=${tool 'gradle_3.4.1'}/bin","JAVA_HOME=${tool 'java_8'}"]) {
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
    }

}
