node {
   stage 'Stage 1'
   echo 'Hello World 1'

   stage 'Stage 2'
   echo 'Hello World 2'

   stage 'Stage 3'
   build job: 'hello-task', parameters: [[$class: 'StringParameterValue', name: 'CoolParam', value: 'hello']]
}
