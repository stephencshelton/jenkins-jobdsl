pipeline {
  environment {
    registry = "stephenshelton/no-ip"
    registryCredential = 'docker-creds'
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/coppit/docker-no-ip.git'
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerfile = "Dockerfile"
          docker.build(registry + ":$BUILD_NUMBER", "-f ${dockerfile}")
        }
      }
    }
  }
}
