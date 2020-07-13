containers = [
  'JNLP':
    [
      [name:'DOCKERFILE', defaultValue: 'resources/containers/Dockerfile-jnlp-openjdk11', description: 'Path to the dockerfile to build inside the github repository'],
      [name:'CONTAINER_NAME', defaultValue: 'stephenshelton/jnlp-agent', description: 'Name of repository on docker hub to push container to'],
      [name:'GITHUB_URL', defaultValue: 'https://github.com/stephencshelton/jenkins-jobdsl.git', description: 'URL for the github repository to checkout']
    ]
  'NOIP':
    [
      [name:'DOCKERFILE', defaultValue: 'Dockerfile', description: 'Path to the dockerfile to build inside the github repository'],
      [name:'CONTAINER_NAME', defaultValue: 'stephenshelton/no-ip', description: 'Name of repository on docker hub to push container to'],
      [name:'GITHUB_URL', defaultValue: 'https://github.com/coppit/docker-no-ip', description: 'URL for the github repository to checkout']
    ]
]
containers.each { container ->
  pipelineJob("Build ${container.getKey()} Container") {
    parameters {
      container.getValue().each { param ->
        stringParam(param.name, param.defaultValue, param.description)
      }
    }
    definition {
      cps {
        script(readFileFromWorkspace('resources/buildFromGithub.groovy'))
        sandbox()
      }
    }
  }
}
