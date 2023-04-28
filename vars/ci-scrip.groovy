def call() {
 if (!env.sonar_extra_opts) {
     env.sonar_extra_opts=""
 }
 
 node('workstation') {   
  
  try { 
   stage('Compile/Build') {
    common.compile()
   }
   
   stage('Test Cases') { 
    common.testcases()
   }
 stage('Code Quality') {
  
  common.codequality()
     }
  } catch (e) {
   
   mail bcc: '', body: "critical look into ${component} \n ${BUILD_URL}", cc: '', from: 'preetamknowledge@gmail.com', replyTo: '', subject: "${component} - Pipeline Failed", to: 'preetamknowledge@gmail.com'
   
    }
  }
}
