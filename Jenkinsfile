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
        //sh 'BRANCH_NAME=$(echo $BRANCH_NAME | cut -c 8-)'
        sh 'TRUE_BRANCH=$BRANCH_NAME'
        sh 'echo $TRUE_BRANCH'
        build job: "MNTLAB-$BRANCH_NAME-child1-build-job", parameters: [string(name: 'BRANCH_NAME', value: "${BRANCH_NAME}")]
        sh 'echo "--------------MNTLAB-$BRANCH_NAME-child1-build-job STARTED OK"----------'
        step ([$class: 'CopyArtifact', projectName: 'MNTLAB-$BRANCH_NAME-child1-build-job']);
        sh 'TRUE_BRANCH=$(echo $TRUE_BRANCH | cut -c 8-)'
        sh 'mv ${TRUE_BRANCH}_dsl_script.tar.gz $BRANCH_NAME_dsl_script.tar.gz'
        sh 'echo "--------------COPIED OK"----------'
        sh 'pwd'
        sh 'echo "pwd is ^^"'
        //, filter: '${BRANCH_NAME}_dsl_script.tar.gz']);
    //    sh 'echo "STASH_TEST">>stash.txt'
    //    stash includes: '*.tar.gz', name: 'test'
//tab
    //stage ('stash_test')
    //    unstash 'test'
    //    sh 'ls -la *.tar.gz>>test.txt'
    //    sh 'cat test.txt'
    //pipeline-{student}-{buildNumber}.tar.gz

}

