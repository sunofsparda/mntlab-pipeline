node('master') {
        tool name: 'gradle3.3', type: 'gradle'
        tool name: 'java', type: 'jdk'
        tool name: 'gradle3.3', type: 'gradle'          
        env.JAVA_HOME="${tool 'java'}"
        env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
        env.GRADLE_HOME="${tool 'gradle3.3'}"
        env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"
//tab
    stage 'Preparation checkout'
        git branch: 'ikhamiakou', url: 'https://github.com/MNT-Lab/mntlab-pipeline'
//tab    
    stage 'Building'
        sh 'gradle clean build'
        sh 'echo "--------------BUILD OK--------------"'

//tab
    stage 'Testing'
        parallel JUnit:{
            sh 'gradle test'
        }
        cucumber:{
            sh 'gradle cucumber'
        }
        jacoco{
            sh 'gradle jacoco'
        }
}
