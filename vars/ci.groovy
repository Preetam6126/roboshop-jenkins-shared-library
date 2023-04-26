def call() {
 if (!env.sonar_extra_opts) {
     env.sonar_extra_opts=""
 }
  pipeline {
  agent any
  
  stages {
  
    stage('Compile/Build') {
    
    steps {
      sh 'env'
   
     script {
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
         mail bcc: '', body: 'critical look into ${component} \n ${BUILD_URL}', cc: '', from: 'preetamknowledge@gmail.com', replyTo: '', subject: '${component} - Pipeline Failed', to: 'preetamknowledge@gmail.com'
     }
   }
  }
}