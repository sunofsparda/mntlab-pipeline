node('master') {
        tool name: 'gradle3.3', type: 'gradle'
        tool name: 'java8', type: 'jdk'
        tool name: 'gradle3.3', type: 'gradle'          
        env.JAVA_HOME="${tool 'java8'}"
        env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
        env.GRADLE_HOME="${tool 'gradle3.3'}"
        env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"
//tab
    stage ('Preparation checkout')
        git branch: 'ikhamiakou', url: 'https://github.com/MNT-Lab/mntlab-pipeline'
//tab    
    stage ('Building')
        sh 'gradle clean build'
        sh 'echo "--------------BUILD OK--------------"'

//tab
    stage ('Testing')
        parallel JUnit: {
            sh 'gradle test'
            sh 'echo "--------------JUnit OK--------------"'
            },
        Jacoco: {
            sh 'gradle jacoco'
            sh 'echo "--------------Jacoco OK--------------"'
        },
        Cucumber: {
            sh 'gradle jacoco'
            sh 'echo "--------------Cucumber OK--------------"'
        }
//tab
    stage ('Triggering job')
        //sh 'echo $BRANCH_NAME'
        //sh '$ORIGIN_NAME="origin/${BRANCH_NAME}"'
        //sh 'echo $ORIGIN_NAME'
        build job: "MNTLAB-$BRANCH_NAME-child1-build-job", parameters: [string(name: 'BRANCH_NAME', value: "${BRANCH_NAME}")]
        sh 'echo "--------------MNTLAB-$BRANCH_NAME-child1-build-job STARTED OK"----------'
        step ([$class: 'CopyArtifact', projectName: 'MNTLAB-$BRANCH_NAME-child1-build-job']);
        sh 'mv *tar.gz ${BRANCH_NAME}_dsl_script.tar.gz'
        sh 'pwd'
        sh 'echo "--------------------PWD------------------------"'
//tab
    stage ('Packaging and Publishing results')        
        sh 'cp ${WORKSPACE}/build/libs/$(basename $WORKSPACE).jar ${WORKSPACE}/gradle-simple.jar'
        sh 'tar -zxvf ${BRANCH_NAME}_dsl_script.tar.gz jobs.groovy'
        sh 'tar -czf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile gradle-simple.jar'
        archiveArtifacts "pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz"
        //, filter: '${BRANCH_NAME}_dsl_script.tar.gz']);
//tab
    stage ('Asking for manual approval')
        timeout(time:1, unit:'HOURS') {
        input 'We are ready for deployment. Deploy gradle-simple.jar ?'
        }
//tab
    stage ('Deployment')
        sh 'java -jar gradle-simple.jar'
//tab
    stage ('Wiping out WORKSPACE')
        timeout(time:1, unit:'HOURS') {
        input 'Are you shure you want to wipeout workspace dir?'
        sh 'rm -rf ${WORKSPACE}/*>>2>&1'
        }
    //    sh 'echo "STASH_TEST">>stash.txt'
    //    stash includes: '*.tar.gz', name: 'test'
//tab
    //stage ('stash_test')
    //    unstash 'test'
    //    sh 'ls -la *.tar.gz>>test.txt'
    //    sh 'cat test.txt'
    //pipeline-{student}-{buildNumber}.tar.gz

}

