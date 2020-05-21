package com.devops

// 实现 Serializable 接口是为了确保当 pipeline 被 Jenkins挂起后能正确恢复
class DingTalkUtils implements Serializable {

    String url = 'https://oapi.dingtalk.com'
        
    // 获取构建者uid，依赖build-user-vars-plugin
    def getSubmitter() {
        wrap([$class: 'BuildUser']) {
            jobUserId = sh encoding: 'UTF-8', returnStdout: true, script: 'echo "${BUILD_USER_ID}"'
        }

        return jobUserId.trim()
    }
    
    def ApiUrl(api) {
        withCredentials([string(credentialsId: '3ad1ec23-3f99-4f11-9c9f-db5f7a68f069', variable: 'secret'), string(credentialsId: 'f2387bc9-8f0f-4da5-b04a-62d0db6ed1f7', variable: 'key')]) {
                apiurl = this.url + api + '?' + 'appkey=' + key +'&' + 'appsecret=' + secret
                echo apiurl
                req = httpRequest contentType: 'APPLICATION_JSON_UTF8', responseHandle: 'NONE', url: apiurl
        }        
        return res.content
    }
    
    def getAccessToken(){
        return this.ApiUrl('/gettoken') 
    }
}
