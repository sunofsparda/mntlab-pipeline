node('master')
{
    tool name: 'java8', type: 'jdk'
    tool name: 'gradle3.3', type: 'gradle'
    tool name: 'git', type: 'git'
    def gradleHome  = tool "gradle3.3" 
    def javaHome  = tool "java8"
    println gradleHome
    println javaHome
//export GRADLE_HOME=/opt/gradle/gradle-3.3
//export PATH=${PATH}:${GRADLE_HOME}/bin

withEnv(["JAVA_HOME=${ tool 'java8' }", "GRADLE_HOME=${tool 'gradle3.3'}", "PATH=${PATH}:${GRADLE_HOME}/bin"]) { 
 
stage ('Preparation (Checking out).')
    {
git branch: 'akutsko', credentialsId: 'fc03fd58-f4e9-44ba-aea8-6c3ce8863ec3', url: 'git@github.com:MNT-Lab/mntlab-pipeline.git'
    }

stage ('Building code')
    {
      sh '''
      gradle build
      '''  
        }
stage ('Testing code'){
    parallel firstBranch: {
        stage ('Unit Tests'){
      sh '''
      gradle test
      '''
    }
    }, secondBranch: {
      stage ('Jacoco Tests'){
      sh '''
      gradle jacoco
      '''
    }
    }, thirdBranch: {
      stage ('cucumber'){
      sh '''
      gradle cucumber
      '''
      }
    }
    }
}
}
