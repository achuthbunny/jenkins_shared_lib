def baseUrl = "https://github.com/achuthbunny/"
def repoName = "$reponame"
def gitRepoUrl= baseUrl + repoName + '.git'
def jobName = "$reponame"
  pipelineJob(jobName) {
        properties {
         pipelineTriggers {
          triggers {
            pollSCM {
              scmpoll_spec('*/1 * * * *')
              ignorePostCommitHooks(true)
            }
          }
         }
        }
        logRotator{
         numToKeep(5)
        }
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitRepoUrl)
                            credentials('jenkins_credentials')
                        }
                        branches('master')
                        extensions {
                            cleanBeforeCheckout()
                        }
                    }
                }
                scriptPath("Jenkinsfile")
            }
        }
    }
