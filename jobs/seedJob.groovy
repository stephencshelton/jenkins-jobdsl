job('Seed/Seed_Job') {
  label('master')
  multiscm {
    git {
      remote {
        url('https://github.com/stephencshelton/jenkins-jobdsl.git')
      }
    }
  }
  steps {
    dsl {
      lookupStrategy('SEED_JOB')
      external('jobs/*')
    }
  }
  triggers {
    cron('H/15 * * * *')
  }
}

