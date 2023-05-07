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
        
        sh 'echo i am okay'
}

def codequality() {
   withAWSParameterStore(credentialsId: 'PARAM1', naming: 'absolute', path: '/sonarqube', recursive: true, regionName: 'us-east-1') {
       
    //   sh 'sonar-scanner -Dsonar.host.url=http://172.31.9.183:9000 -Dsonar.login=${SONARQUBE_USER} -Dsonar.password=${SONARQUBE_PASS} -Dsonar.projectKey=${component} ${sonar_extra_opts}'
  
    //  sh 'sonar-scanner -Dsonar.host.url=http://172.31.9.183:9000 -Dsonar.login=${SONARQUBE_USER} -Dsonar.password=${SONARQUBE_PASS} -Dsonar.projectKey=${component} ${sonar_extra_opts} -Dsonar.qualitygate.wait=true' 
    
    sh 'echo OK'
   }
  
}

// sh 'sonar-scanner -Dsonar.host.url=http://172.31.9.183:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} -Dsonar.java.binaries=./target'