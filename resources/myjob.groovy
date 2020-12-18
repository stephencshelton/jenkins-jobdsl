node('master') {
    git credentialsId: 'git_ssh_key', url: 'git@github.com:stephencshelton/k3-pi-cluster.git'
    dir("${WORKSPACE}/directory") {
    // some block
    writeFile file: 'text', text: 'THIS IS A TEST FILE CREATED BY PIPELINE DSL'
    sh label: '', script: 'cat text'
}
}
