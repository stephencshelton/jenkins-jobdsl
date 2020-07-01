pipelineJob('Test-Kubernetes-Plugin') {
    definition {
        cps {
            script(readFileFromWorkspace('resources/testKubernetesPlugin.groovy'))
            sandbox()
        }
    }
}
