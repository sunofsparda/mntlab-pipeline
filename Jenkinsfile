node('master')
{
    tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'

withEnv(["JAVA_HOME=${ tool 'java8' }", "PATH+GRADLE=${tool 'gradle3.3'}/bin:${env.JAVA_HOME}/bin"]) { 
 
stage ('Preparation (Checking out).')
    {
git branch: 'akutsko', credentialsId: 'fc03fd58-f4e9-44ba-aea8-6c3ce8863ec3', url: 'git@github.com:MNT-Lab/mntlab-pipeline.git'
    }

stage ('Building code'){sh 'gradle build'}
stage ('Testing code'){
    parallel firstBranch: {
                  stage ('Unit Tests'){sh 'gradle test'}
             }, secondBranch: {
                  stage ('Jacoco Tests'){sh 'gradle jacoco'}
             }, thirdBranch: {
                  stage ('cucumber'){sh 'gradle cucumber'}
             }
    }
stage ('Triggering job and fetching artefact after finishing'){
    build job: 'MNTLAB-akutsko-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'akutsko')], quietPeriod: 0
    step ([$class: 'CopyArtifact',
          projectName: 'MNTLAB-akutsko-child1-build-job',
          filter: 'akutsko_dsl_script.tar.gz']);
    sh 'tar -xf akutsko_dsl_script.tar.gz jobs.groovy'
    }
stage ('Packaging and Publishing results'){
    archiveArtifacts artifacts: 'jobs.groovy Jenkinsfile  build/libs/$WORKSPACE.jar', excludes: null
    }   
}
}
