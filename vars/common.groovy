def compile() {
  if(app_lang == "nodejs") {
        sh 'npm install'
      }
      if(app_lang == "maven") {
        sh 'mvn package'
    }
}

def testcases() {
//   if(app_lang == "nodejs") {
//         sh 'echo test'
//       }
//       if(app_lang == "maven") {
//         sh 'echo maven'
//     }


        // npm test
        // mvn test
        // python -m unittests
        // go test
        
        sh 'echo OK'
}