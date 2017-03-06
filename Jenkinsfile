#!groovy

node ('host') {
// preparation
    stage ('preparation'){
        checkout([$class: 'GitSCM', branches: [[name: '*/rvashkevich']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline']]])
    }
    //building
    stage ('building') {
        
        sh ' gradle build '
        
    }


}
