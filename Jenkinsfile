#!groovy

/* Written by Siarhei Hreben
   DevOps Lab 2017	*/

try {
node('host') {
    withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin","JAVA_HOME=${tool 'java8'}","PATH+JAVA=${tool 'java8'}/bin"]) {
	stage('\u27A1 Preparation (Checking out)') {
		env.Stage = 'Preparation (Checking out)'
		checkout scm
	}
	stage('\u27A1 Building code') {
		env.Stage = 'Building code'
		sh '''gradle clean
		gradle build'''
	}
	stage('\u27A1 Testing') {
		env.Stage = 'Building code'
		parallel cucumber: {
			sh 'gradle cucumber'
		}, junit: {
			sh 'gradle test'
		}, jacoco: {
			sh 'gradle jacoco'
		}
	}
	
	stage('\u27A1 Triggering job and fetching artefact after finishing') {
		env.Stage = 'Triggering job and fetching artefact after finishing'
		build job: 'MNTLAB-shreben-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'shreben'),string(name: 'WORKSPACE', value: "${WORKSPACE}")]
		step ([$class: 'CopyArtifact',projectName: 'MNTLAB-shreben-child1-build-job',filter: 'shreben_dsl_script.tar.gz']);
		sh 'tar -zxf shreben_dsl_script.tar.gz'
    }
	stage('\u27A1 Packaging and Publishing results') {
		env.Stage = 'Packaging and Publishing results'
		sh "cp build/libs/\$(basename \${WORKSPACE}).jar ."
		sh "tar -czf pipeline-shreben-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile \$(basename \${WORKSPACE}).jar"
		archiveArtifacts "pipeline-shreben-${BUILD_NUMBER}.tar.gz"
    }
	stage('\u27A1 Asking for manual approval') {
		env.Stage = 'Asking for manual approval'
		input message: 'Artifact is built and ready for deployment. Proceed?', submitterParameter: 'submitter'
    }
	stage('\u27A1 Deployment') {
		env.Stage = 'Deployment'
		sh 'java -jar \$(basename \${WORKSPACE}).jar'
	}
	stage('\u27A1 Sending status') {
		env.Msg = '''
		============================
		Build SUCCESSFUL
		============================'''
		echo "$Msg"
	}
}       // withEnv end
}       // node end

} catch (err) {  // try end
		currentBuild.result = "FAILURE"
		env.Msg = """
		============================
		Build FAILED on stage "$Stage"
		============================

		The error message is:
		$err
		"""
		echo "$Msg"
} finally {
		(currentBuild.result = "ABORTED") {
		env.Msg = """
		===============================================
		Build ABORTED on stage "$Stage"  by "$submitter"
		===============================================
		"""
		echo "$Msg"
		}
	}

