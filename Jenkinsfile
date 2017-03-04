node('master') {
                tool name: 'java8', type: 'jdk'
                tool name: 'gradle3.3', type: 'gradle'
//    withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin"])
//	withEnv(["JAVA_HOME=${tool 'java8'}"])        
//env.JAVA_HOME="${tool 'java8'}"
 //       env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
      env.GRADLE_HOME="${tool 'gradle3.3'}"

stage 'Preparation (Checking out)'
	git branch: 'mkuzniatsou', url: 'https://github.com/MNT-Lab/mntlab-pipeline.git'
	sh 'env | sort'


stage ('test-gradle') 
//	env.GRADLE_HOME="${tool 'gradle3.3'}"
//	env.PATH="${env.GRADLE_HOME}/bin:${env.PATH}"
	sh 'gradle -version'
        sh 'java -version'

stage 'Building code'
	sh 'gradle -version'
//   	sh 'chmod +x gradlew'
	sh 'gradle -b ${WORKSPACE}/build.gradle'
//	sh 'mv ${WORKSPACE}/build/libs/*.jar ${WORKSPACE}/gradle-simple.jar'
  

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
	
  stage 'Deployment'
 
  stage 'Sending status'
}
