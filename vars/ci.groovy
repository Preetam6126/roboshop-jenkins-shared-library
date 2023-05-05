def call() {
 // if (!env.sonar_extra_opts) {
 //     env.sonar_extra_opts=""
 // }
  pipeline {
  agent any
  
  stages {
  
    stage('Compile/Build') {
    
    steps {
   
     script {
        if(app_lang== "nodejs") {
         echo 'i love nodejs'
        }
        if(app_lang == "maven") {
         echo 'i love maven'
        }
                   
        }
   
      }
    }
//    stage('Test Cases') { 
//     steps {
//      script {
//        common.testcases()
                   
//         }
//       }
//     }
//   stage('Code Quality') { 
//     steps {
//      script {
//        common.codequality()
                   
//         }
//       }
//     }
//    }
   
//    post {
//      failure{
//         mail bcc: '', body: "critical look into ${component} \n ${BUILD_URL}", cc: '', from: 'preetamknowledge@gmail.com', replyTo: '', subject: "${component} - Pipeline Failed", to: 'preetamknowledge@gmail.com'
//      }
//    }
//   }
// }



// def call(){
//    pipeline {
//      agent any
     
//      stages {
      
//        stage('Compile/Build'){
//         steps {
//         echo 'Compile/build'
//         }
//        } 
       
       stage('Test cases'){
        steps{
        echo 'Test cases'
        }
       }
       
     }
   }
}