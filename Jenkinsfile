//ALL FIXED. DONT TOUCH!
node('host')
{
    try
    {
        tool name: 'java8', type: 'jdk'
        tool name: 'gradle3.3', type: 'gradle'
        withEnv(["PATH+GRADLE=${tool 'gradle3.3'}/bin","JAVA_HOME=${tool 'java8'}","PATH+JAVA=${tool 'java8'}/bin"])
        {
            def errorArray = []
            stage ('cleanup')
            {
                try
                {
                    step([$class: 'WsCleanup'])
                }
                catch (error)
                {
                    errorArray.push("ERROR: Cant cleanup workspace!")
                }        
            }

            stage ('Preparation (Checking out).')
            {
                try
                {
                    git url:'https://github.com/MNT-Lab/mntlab-pipeline.git', branch:'pheraska'
                }
                catch (error)
                {
                    errorArray.push("ERROR: Cant clone from git!")
                }
            }

            stage ('Building code.')
            {
                try
                {
                    sh 'gradle build';
                }
                catch (error)
                {
                    errorArray.push("ERROR: Cant build with gradle!")
                }
            }

            stage ('Testing.')
            {
                try
                {
                    parallel JUnit:
                    {
                        sh 'gradle test';
                    },
                    Jacoco:
                    {
                        sh 'gradle cucumber';
                    },
                    Cucumber:
                    {
                        sh 'gradle jacoco';
                    }
                }
                catch (error)
                {
                    errorArray.push("ERROR: Something goes wrong with tests!")
                }
            }

            stage ('Triggering job and fetching artefact after finishing.')
            {
                try
                {
                    build job: "MNTLAB-${BRANCH_NAME}-child1-build-job", parameters: [[$class: 'StringParameterValue', name: 'BRANCH_NAME', value: "${BRANCH_NAME}"]]
                    step ([$class: 'CopyArtifact', projectName: "MNTLAB-${BRANCH_NAME}-child1-build-job"]);
                }
                catch (error)
                {
                    errorArray.push("ERROR: Cant trigger other project!")
                }
            }

            stage ('Packaging and Publishing results.')
            {
                try
                {
                    sh '''
                    cp ${WORKSPACE}/build/libs/$(basename "$PWD").jar ${WORKSPACE}/${BRANCH_NAME}-${BUILD_NUMBER}.jar
                    tar -zxvf ${BRANCH_NAME}_dsl_script.tar.gz jobs.groovy
                    tar -czf pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz jobs.groovy Jenkinsfile ${BRANCH_NAME}-${BUILD_NUMBER}.jar
                    ''';
                    archiveArtifacts artifacts: "pipeline-${BRANCH_NAME}-${BUILD_NUMBER}.tar.gz"
                }
                catch (error)
                {
                    errorArray.push("ERROR: Cant create artifacts!")
                }
            }

            stage ('Asking for manual approval.')
            {
                try
                {
                    timeout(time:3, unit:'MINUTES') 
                    {
                        input message:'Approve deployment?'
                    }
                }
                catch (error)
                {
                    errorArray.push("ERROR: Somet wrong with with approve!")
                }
            }

            stage ('Deployment.')
            {
                try
                {
                    sh 'java -jar ${BRANCH_NAME}-${BUILD_NUMBER}.jar'
                }
                catch (error)
                {
                    errorArray.push("ERROR: Somet wrong with deployent!")
                }
            }

            stage ('Sending status.')
            {
                def arrSize = errorArray.size();
                if (arrSize != 0)
                {
                    echo "${errorArray}"
                }
                else
                {
                    echo "SUCCESS: No errors found!"
                }
            }
        }
    }
    catch (hudson.AbortException ae)
    {
        if (ae.getMessage().contains('script returned exit code 143'))
        {
            echo "DETECTED DAVAI DAVAI"
            //throw new UserInterruptedException(ae)
        }
        /*if (ae.getMessage().contains('script returned exit code 143'))
        {
            throw new UserInterruptedException(ae)
        } else {
            throw ae
        }*/
    }
}
