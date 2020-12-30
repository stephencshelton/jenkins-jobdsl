pipelineJob('My stupid shit') {
  parameters {
  stringParam('discord_message', 'Posting as bot but DSL', 'Discord message')
  }
    definition {
    cps {
    script(readFileFromWorkspace('resources/first_bot.groovy'))
    sandbox()
  }
  }
 }
