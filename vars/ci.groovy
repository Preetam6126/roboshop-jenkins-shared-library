def call() {
 if (!env.sonar_extra_opts) {
     env.sonar_extra_opts=""
 }
  pipeline {
  agent any
  
  stages {
  
    stage('Compile/Build') {
    
    steps {
   
     script {
      withAWSParameterStore(credentialsId: 'PARAM', naming: 'absolute', path: '/sonarqube.user', recursive: true, regionName: 'us-east-1') {
      sh 'env'
      sh 'exit1'
}
       common.compile()
                   
        }
   
      }
    }
   stage('Test Cases') { 
    steps {
     script {
       common.testcases()
                   
        }
      }
    }
  stage('Code Quality') { 
    steps {
     script {
       common.codequality()
                   
        }
      }
    }
   }
   
   post {
     failure{
        mail bcc: '', body: "critical look into ${component} \n ${BUILD_URL}", cc: '', from: 'preetamknowledge@gmail.com', replyTo: '', subject: "${component} - Pipeline Failed", to: 'preetamknowledge@gmail.com'
     }
   }
  }
}