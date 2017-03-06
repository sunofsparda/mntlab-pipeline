#!groovy

node ('host') {
        env.JAVA_HOME="${tool 'java8'}"
        env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
        sh 'java -version'
        def gradle(command) {
        sh "${tool name: 'gradle3.3', type: 'hudson.plugins.gradle.GradleInstallation'}/bin/gradle ${command}"
        }
        sh 'gradle -version'
    // preparation
    stage ('preparation'){
        checkout([$class: 'GitSCM', branches: [[name: '*/rvashkevich']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline']]])
    }
    //building
    stage ('building') {
        
        sh ' gradle build '
        
    }


}
