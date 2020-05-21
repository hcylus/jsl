def call(String lang) {
  pipeline {
    agent any
    environment {
      _version = createVersion()
    }
    stages {
      stage ('build') {
        steps {
          script {
            def util = new com.devops.Utils()
            def dtalk = new com.devops.DingTalkUtils()
            def uid = dtalk.getSubmitter()
            def ac = dtalk.getAccessToken()
            def version = util.createVersion("${BUILD_NUMBER}")
            echo "${version}"
            echo uid
            echo ac
            if (lang == 'go') {
              echo 'go build'
              sayHello lang
            } else if (lang == 'java') { 
              echo 'java build'
              sayHello lang 
            }  
            echo "${_version}"
          }  
        }
      }
    }
  } 
}

def createVersion() {
  return new Date().format('YYYYMMddHHmmss') + "-${env.BUILD_NUMBER}"
}
