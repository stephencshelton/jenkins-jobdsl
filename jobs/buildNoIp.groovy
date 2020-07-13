pipelineJob('Jenkins Build no-ip container') {
    definition {
        cps {
            script(readFileFromWorkspace('resources/buildNoIpContainer.groovy'))
            sandbox()
        }
    }
}
