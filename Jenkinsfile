#!groovy

node('master') {
	tool name: 'java8', type: 'jdk'
	tool name: 'gradle3.3', type: 'gradle'
	env.JAVA_HOME="${tool 'java8'}"
	env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
	env.GRADLE_HOME="${tool 'gradle3.3'}"
	env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"

//    withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin"])
//	withEnv(["JAVA_HOME=${tool 'java8'}"])        

stage 'Preparation (Checking out)'
	git branch: 'mkuzniatsou', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
	sh 'env | sort'
	sh 'gradle -version'
        sh 'java -version'

stage 'Building code'
	sh 'gradle -b ${WORKSPACE}/build.gradle'
  

stage 'Testing code'
	parallel JUnit: {
	sh 'gradle test'}, 
	Jacoco: {
	sh 'gradle jacoco'}, 
	Cucumber: {
	sh 'gradle cucumber'
	}


stage 'Triggering job and fetching artefact after finishing'
	build job: 'MNTLAB-mkuzniatsou-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'mkuzniatsou')]
	archiveArtifacts '{BRANCH_NAME}_dsl_script.tar.gz,jobs.groovy,script.sh'

  stage 'Packaging and Publishing results'

  stage 'Asking for manual approval'
	timeout(time:30, unit:'SECONDS') {
	input 'Previous stage successful. Deploy this artefact?'
	}
  stage 'Deployment'
 
  stage 'Sending status'
}
