#!groovy

node('master') {
	tool name: 'java8', type: 'jdk'
	tool name: 'gradle3.3', type: 'gradle'
	env.JAVA_HOME="${tool 'java8'}"
	env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
	env.GRADLE_HOME="${tool 'gradle3.3'}"
	env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"


	def err = null
	currentBuild.result = "SUCCESS"

	try {

//    withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin"])
//	withEnv(["JAVA_HOME=${tool 'java8'}"])        

stage 'Preparation (Checking out)'
	git branch: 'mkuzniatsou', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
	sh 'env | sort'
	sh 'gradle -version'
        sh 'java -version'

stage 'Building code'
	sh 'gradle clean build'
  

stage 'Testing code'
	parallel JUnit: {
	sh 'gradle test'}, 
	Jacoco: {
	sh 'gradle jacoco'}, 
	Cucumber: {
	sh 'gradle cucumber'
	}

stage 'Triggering job and fetching artefact after finishing'
	build job: "MNTLAB-{$BRANCH_NAME}-child1-build-job", parameters: [string(name: 'BRANCH_NAME', value: "${BRANCH_NAME}")]
//	archiveArtifacts '{BRANCH_NAME}_dsl_script.tar.gz,jobs.groovy,script.sh'
	step ([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job"]);


  stage 'Packaging and Publishing results'

  stage 'Asking for manual approval'
	timeout(time:30, unit:'SECONDS') {
	input 'Previous stage successful. Deploy this artefact?'
	}
  stage 'Deployment'
} 
//  stage 'Sending status'

catch (caughtError) {
    err = caughtError
    currentBuild.result = "FAILURE"

mail body: "project build error: ${err}" ,
subject: 'project build failed',
to: 'n.g.kuznetsov@gmail.com'
} 

	stage 'Sending status'
	echo "RESULT: ${currentBuild.result}"

finally {

    /* Must re-throw exception to propagate error */
    if (err) {
        throw err
    }
}
}
