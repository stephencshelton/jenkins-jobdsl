pipelineJob('Jenkins Build OpenJDK11 Arm64') {
    definition {
        cps {
            script(readFileFromWorkspace('resources/buildJenkinsOpenJDK11Arm64.groovy'))
            sandbox()
        }
    }
}
