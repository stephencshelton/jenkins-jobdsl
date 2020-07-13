pipelineJob('Jenkins Build no-ip container') {
    definition {
        cps {
            script(readFileFromWorkspace('resources/buildNoIp.groovy'))
            sandbox()
        }
    }
}
