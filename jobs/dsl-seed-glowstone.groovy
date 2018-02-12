pipelineJob('build-glowstone') {

  def repo = 'https://github.com/danderemer/glowstoneci.git'

  triggers {
    scm('H/15 * * * *')
  }
  description("Pipeline for $repo")

  definition {
    cpsScm {
      scm {
        git {
          remote { url(repo) }
          branches('master')
          scriptPath('jobs/build-glowstone.groovy')
          extensions { }
        }
      }
    }
  }
}