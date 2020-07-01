pipelineJob('Build-Jenkins-Jnlp-OpenJdk11') {
  definition {
    cps {
      script(readFileFromWorkspace('resources/buildJnlpJdk11.groovy'))
      sandbox()
    }
  }
}
