pipelineJob('cucumber-selenium-testing') {
    definition {
        cpsScm {
            scm {
                git('https://github.com/leoduprates/cucumber-selenium-testing') {  node ->
                    node / gitConfigName('DSL User')
                    node / gitConfigEmail('jenkinsdsl@xpto.xpto')
                }
            }
            triggers {
                scm('H/5 * * * *')
            }
            scriptPath('./Jenkinsfile')
        }
    }
}