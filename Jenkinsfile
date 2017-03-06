#!groovy

 node ('host') {
//   node {
        env.JAVA_HOME="${tool 'java8'}"
        env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
        sh 'java -version'
        env.GRADLE_HOME="${tool 'gradle3.3'}"
        env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"
        sh "gradle tasks"
    // preparation
    stage ('preparation'){
        checkout([$class: 'GitSCM', branches: [[name: '*/rvashkevich']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/MNT-Lab/mntlab-pipeline']]])
    }
    //building
    stage ('building') {
        
        sh "gradle clean build"
        
    }
    stage ('testing') {
       parallel cucumber: {
    	sh 'gradle cucumber'
	},      junit: {
		sh 'gradle test'
	},      jacoco: {
		sh 'gradle jacoco'
		}
    stage ('Triggering job and fetching artefact after finishing') {
   		build job: "MNTLAB-${BRANCH_NAME}-child1-build-job", parameters: [[$class: 'StringParameterValue', name: "${BRANCH_NAME}", value: "${BRANCH_NAME}"]]
            	step ([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job", filter: '*.tar.gz']);
		} 
stage ('Packaging and publishing results') {
       	sh '''
	cp ${WORKSPACE}/build/libs/$(basename $WORKSPACE).jar ${WORKSPACE}/${BRANCH_NAME}-${BUILD_NUMBER}.jar
	tar -zxvf ${BRANCH_NAME}_dsl_script.tar.gz jobs.groovy       	
	tar -czf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile ${BRANCH_NAME}-${BUILD_NUMBER}.jar'''
	archiveArtifacts "pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz"
		}
    }
    stage 'Asking for manual approval'
	timeout(time:1, unit:'HOURS') {
	input 'Artefact is ready for deploy. Do you want proceed?', submitter:'admin' 
	}

stage 'Deployment'
	sh 'java -jar ${BRANCH_NAME}-${BUILD_NUMBER}.jar'

stage 'Sending status'
	echo "RESULT: Success"
}

}
