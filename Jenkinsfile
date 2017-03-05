#!groovy

/* Written by Siarhei Hreben
   DevOps Lab 2017	*/

node('host') {
    withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin","PATH+JAVA=${tool 'java8'}/bin}"]) {
	stage('\u27A1 Preparation (Checking out)') {
	    checkout scm
	}
	stage('\u27A1 Building code') {
	    sh '''env
		ls -la
		gradle -version'''
	}
	stage('\u27A1 Testing') {
	    parallel cucumber: {
			sh 'gradle cucumber'
		}, junit: {
			sh 'gradle test'
		}, jacoco: {
			sh 'gradle jacoco'
		}
	}
	stage('\u27A1 Triggering job and fetching artefact after finishing') {
            build job: 'MNTLAB-shreben-child1-build-job', parameters: [string(name: 'BRANCH_NAME', value: 'shreben'),string(name: 'WORKSPACE', value: "${WORKSPACE}")]
            step ([$class: 'CopyArtifact',projectName: 'MNTLAB-shreben-child1-build-job',filter: 'shreben_dsl_script.tar.gz']);
            sh 'tar -zxf shreben_dsl_script.tar.gz'
    }
	stage('\u27A1 Packaging and Publishing results') {
            sh "tar -czf pipeline-shreben-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile build/libs/MNT-pipeline-test.jar"
            archiveArtifacts "pipeline-shreben-${BUILD_NUMBER}.tar.gz"
    }
	stage('\u27A1 Asking for manual approval') {
            input 'Artifact is built and ready for deployment. Proceed?'
    }
	stage('\u27A1 Deployment') {
            sh 'java -jar build/libs/MNT-pipeline-test.jar'
    }
    stage('\u27A1 Sending status') {
            echo 'Deployment is successful!'
    }
}
}
