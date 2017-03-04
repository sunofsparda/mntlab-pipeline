node('master') {
                tool name: 'java8', type: 'jdk'
                tool name: 'gradle3.3', type: 'gradle'

  stage 'Preparation (Checking out)'
	git branch: 'mkuzniatsou', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
	 sh 'env | sort'


	stage ('test-gradle') 
    env.GRADLE_HOME="${tool 'gradle3.3'}"
    env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"
    sh 'gradle -version'

  stage 'Building code'
 sh 'gradle -version'
   	sh 'chmod +x gradlew'
	sh "${JENKINS_HOME}/tools/hudson.plugins.gradle.GradleInstallation/gradle3.3/bin/gradle -b ${WORKSPACE}/build.gradle"
//	sh 'mv ${WORKSPACE}/build/libs/*.jar ${WORKSPACE}/gradle-simple.jar'
echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}" 
  

 stage 'Testing code'
	parallel JUnit: {
	sh './gradlew test'}, 
	Jacoco: {
	sh 'gradle jacoco'}, 
	Cucumber: {
	sh './gradlew cucumber'
	}


  stage 'Triggering job and fetching artefact after finishing'
	build job: 'MNTLAB-mkuzniatsou-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'mkuzniatsou')]
	archiveArtifacts '{BRANCH_NAME}_dsl_script.tar.gz,jobs.groovy,script.sh'

  stage 'Packaging and Publishing results'

  stage 'Asking for manual approval'
	
  stage 'Deployment'
 
  stage 'Sending status'
}
