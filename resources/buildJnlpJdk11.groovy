podTemplate(containers: [
  containerTemplate(name: 'k3c', image: 'rancher/k3c:v0.2.1', privileged: true, ttyEnabled: true, command: 'k3c daemon')
]) {
  node(POD_LABEL) {
    stage('Build jnlp-agent') {
      git 'https://github.com/stephencshelton/jenkins-jobdsl.git'
      container('k3c') {
        stage('Build in k3c container') {
          sh "k3c build -f resources/containers/Dockerfile-jnlp-openjdk11 -t stephenshelton/jnlp-agent:0.0.${BUILD_NUMBER} ."
          // Wait on k3c to show the new image, seems to be a little slow in that department
          imageOutput = sh(script: "k3c images", returnStdout: true)
          while(!imageOutput.contains("stephenshelton/jnlp-agent")) {
            echo "Waiting on image to be available to push"
            sh "sleep 30"
            println imageOutput
            imageOutput = sh(script: "k3c images", returnStdout: true)
          }
        }
        // Will add this once I have the JCasC creating the secrets for jenkins
        stage('Push image to dockerhub') {
          echo "k3c push stephenshelton/jnlp-agent:0.0.${BUILD_NUMBER}"
        }
      }
    }
  }
}
