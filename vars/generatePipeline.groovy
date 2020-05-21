def call(String lang) {
  if (lang == 'go') {
    pipeline {
      agent any
      environment {
       _version = createVersion()
      }
       stages {
         stage ('build') {
            steps {
                script {
                    def util = new com.hcylus.Utils()
                    def version = util.createVersion("${BUILD_NUMBER}")
                    echo "${version}"
                    sayHello lang
                    echo "${_version}"
                }  
            }
         }
       }
    } 
  } else if (lang == 'java') {
    pipeline {
      agent any
      environment {
       _version = createVersion()
      }
       stages {
         stage ('build') {
            steps {
              script {
                    def util = new com.hcylus.Utils()
                    def version = util.createVersion("${BUILD_NUMBER}")
                    echo "${version}"
                    sayHello lang
                    echo "${_version}"
                }  
            }
         }
       }
    } 
  }
  // 其他语言
}


def createVersion() {
    return new Date().format('yyyyMM') + "-${env.BUILD_NUMBER}"
    
}
