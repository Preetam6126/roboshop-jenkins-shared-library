def call() {
 if (!env.sonar_extra_opts) {
     env.sonar_extra_opts=""
 }
 
  node( "workstation" ) {   
  
  try { 
   
   stage('Check OUt Code') {
     sh 'ls -l'
     cleanWs()
     sh 'ls -l'
     git branch: 'main', url: 'https://github.com/Preetam6126/cart'
     sh 'ls -l'
   }
   
   stage('Compile/Build') {
     sh 'env'
    common.compile()
   }
   
   stage('Test Cases') { 
    common.testcases()
   }
 stage('Code Quality') {
  
  common.codequality()
     }
  } catch (e) {
    mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'preetamknowledge@gmail.com', subject: "${component} - Pipeline Failed", to: 'preetamknowledge@gmail.com',  mimeType: 'text/html'
    }
  }
}

