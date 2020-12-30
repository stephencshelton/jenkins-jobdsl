pipelineJob('My stupid shit') {
  parameters {
  stringParam('discord', 'Posting as bot', 'Discord message')
  }
    definition {
    cps {
    script(readFileFromWorkspace('resources/first_bot.groovy'))
    sandbox()
  }
  }
 }
