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
          docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
  }
}
