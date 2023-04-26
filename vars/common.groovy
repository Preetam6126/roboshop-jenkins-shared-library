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

def codequality() {
  
  sh 'sonar-scanner -Dsonar.host.url=http://172.31.9.183:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} ${sonar_extra_opts}'
}



// sh 'sonar-scanner -Dsonar.host.url=http://172.31.9.183:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} -Dsonar.java.binaries=./target'