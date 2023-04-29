def call() {
 if (!env.sonar_extra_opts) {
     env.sonar_extra_opts=""
 }
 
 if (env.TAG_NAME ==~ ".*") {
     env.GTAG= "true"
   } else {
    env.GTAG = "false"
  }
   
  node( "workstation" ) {   
  
  try { 
   
   stage('Check OUt Code') {
     cleanWs()
     git branch: 'main', url: 'https://github.com/Preetam6126/cart'
   }
   
   sh 'env'
   
   if (env.BRANCH_NAME != "main") {
    stage('Compile/Build') {
    common.compile()
     }
   }
  
  printIn GTAG
  printIn BRANCH_NAME
  
   if (env.GTAG != "true" && env.BRANCH_NAME != "main") {  
     stage('Test Cases') { 
    common.testcases()
    }
   }
   
   if (BRANCH_NAME ==~ "PR-.*") {  
    stage('Code Quality') {
     common.codequality()
     }
   }
 
 
  } catch (e) {
    mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'preetamknowledge@gmail.com', subject: "${component} - Pipeline Failed", to: 'preetamknowledge@gmail.com',  mimeType: 'text/html'
    }
  }
}

