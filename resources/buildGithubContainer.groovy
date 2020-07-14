podTemplate(containers: [
  containerTemplate(name: 'k3c', image: 'rancher/k3c:v0.2.1', privileged: true, ttyEnabled: true, command: 'k3c daemon')
]) {
  node(POD_LABEL) {
    stage('Build Container') {
      git GITHUB_URL
      container('k3c') {
        stage('Build in k3c container') {
          sh "k3c build -f $DOCKERFILE -t $CONTAINER_NAME:0.0.${BUILD_NUMBER} ."
          // Wait on k3c to show the new image, seems to be a little slow in that department
          imageOutput = sh(script: "k3c images", returnStdout: true)
          while(!imageOutput.contains("$CONTAINER_NAME")) {
            echo "Waiting on image to be available to push"
            sh "sleep 30"
            println imageOutput
            imageOutput = sh(script: "k3c images", returnStdout: true)
          }
        }
        // Will add this once I have the JCasC creating the secrets for jenkins
        stage('Push image to dockerhub') {
          withCredentials([file(credentialsId: 'docker-login', variable: 'DOCKER_CONFIG')]) {
            dir("$WORKSPACE/.docker") {
              sh "cp $DOCKER_CONFIG ."
              sh "DOCKER_CONFIG=$DOCKER_CONFIG k3c push $CONTAINER_NAME:0.0.${BUILD_NUMBER}"
            }
          } 
        } 
      }
    }
  }
}
