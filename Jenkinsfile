node('master') {
                tool name: 'java8', type: 'jdk'
                tool name: 'gradle3.3', type: 'gradle'

  stage 'Preparation (Checking out)'
	git branch: 'mkuzniatsou', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'

  stage 'Building code'
//   	sh 'chmod +x gradlew'
//	sh "${JENKINS_HOME}/tools/hudson.plugins.gradle.GradleInstallation/gradle3.3/bin/gradle -b ${WORKSPACE}/build.gradle"
	sh './gradlew build'
   
 stage 'Testing code'
	parallel JUnit: {
	sh './gradlew test'}, 
	Jacoco: {
	sh './gradlew jacoco'}, 
	Cucumber: {
	sh './gradlew cucumber'



  stage 'Triggering job and fetching artefact after finishing'

  stage 'Packaging and Publishing results'

  stage 'Asking for manual approval'
	
  stage 'Deployment'
 
  stage 'Sending status'
}
