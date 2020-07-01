podTemplate(containers: [
    containerTemplate(name: 'k3c', image: 'rancher/k3c:v0.2.1', privileged: true, ttyEnabled: true, command: 'cat')
  ]) {

    node(POD_LABEL) {
        stage('Build Container in K3c') {
            git 'https://github.com/jenkinsci/kubernetes-plugin.git'
            container('k3c') {
                stage('Build Commands') {
                    sh 'echo hi'
                    sh 'which k3c'
                }
            }
        }
    }
}
