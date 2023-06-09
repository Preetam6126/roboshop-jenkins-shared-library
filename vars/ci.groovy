def call() {
 if (!env.sonar_extra_opts) {
     env.sonar_extra_opts=""
 }
 
 if(env.TAG_NAME ==~ ".*") {
    env.GTAG = "true"
 }
 
   node('workstation') {
    
    try { 
   
   stage('Check OUt Code') {
    sh 'ls -l'
     cleanWs()
     sh 'ls -l'
     git branch: 'main', url: "https://github.com/Preetam6126/${component}"
     sh 'ls -l'
   }
   
   sh 'env'
   
   if(env.BRANCH_NAME != "main") {
   
    stage('Compile/Build') {
     sh 'env'
      common.compile()
     }
   }
   
     
    if(BRANCH_NAME ==~ "PR-.*"){
     stage('Code Quality') { 
       common.codequality()
     }
    }
    
    
   if(env.GTAG != "true" && env.BRANCH_NAME != "main") {
     stage('Test Cases') { 
       common.testcases()
     }
    }
    
     if(env.GTAG == "true"){
     stage('Package') { 
       common.prepareArtifacts()
     }
    
     stage('Artifact Upload') { 
       common.artifactupload()
      }
     }
    
   }catch (e) {
    mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'preetamknowledge@gmail.com', subject: "${component} - Pipeline Failed", to: 'preetamknowledge@gmail.com',  mimeType: 'text/html'
   }
  }
}



 
//  if (env.TAG_NAME ==~ ".*") {
//      env.GTAG= "true"
//    }
   
//   node( "workstation" ) {   
  
//   try { 
   
//    stage('Check OUt Code') {
//      cleanWs()
//      git branch: 'main', url: 'https://github.com/Preetam6126/cart'
//    }
   
//    sh 'env'
   
//    if (env.BRANCH_NAME != "main") {
//     stage('Compile/Build') {
//     common.compile()
//      }
//    }
  
  
//    if (env.GTAG != "true" && env.BRANCH_NAME != "main") {  
//      stage('Test Cases') { 
//     common.testcases()
//     }
//    }
   
//    if (BRANCH_NAME ==~ "PR-.*") {  
//     stage('Code Quality') {
//      common.codequality()
//      }
//    }
   
   
//    if (env.GTAG == "true") {  
//      stage('Package') { 
//     common.testcases()
//     }
//      stage('Artifact Upload') { 
//     common.testcases()
//     }
//    }
//   } catch (e) {
//     mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'preetamknowledge@gmail.com', subject: "${component} - Pipeline Failed", to: 'preetamknowledge@gmail.com',  mimeType: 'text/html'
//     }
//   }
// }

