pipeline {
  environment {
    registry = "stephenshelton/jenkins-openjdk11-arm64"
    registryCredential = 'docker-creds'
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/jenkinsci/docker.git'
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerfile = "Dockerfile-openj9-jdk11"
          docker.build(registry + ":$BUILD_NUMBER", "-f ${dockerfile}")
        }
      }
    }
  }
}
