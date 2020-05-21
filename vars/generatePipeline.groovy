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
            def version = util.createVersion("${BUILD_NUMBER}")
            echo "${version}"
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
