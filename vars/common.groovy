def compile() {
  if(app_lang == "nodejs") {
        sh 'npm install'
      }
      if(app_lang == "maven") {
        sh 'mvn package ; mv target/${component}-1.0.jar ${component}.jar'
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

def prepareArtifacts() {
  // sh 'echo ${TAG_NAME} >VERSION'    
  // // if (app_lang == "nodejs" || app_lang == "angular") {   
  // //   // sh 'zip -r ${component}-${TAG_NAME}.zip server.js node_moudles VERSION -x Jenkinsfile' 
  // //   sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
  // // }
  // if (app_lang == "maven") {   
  //   sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar schema VERSION'
  // } else {   
  //   sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
  // } 
  sh 'docker build -t 598125288280.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME} .'
}

def artifactupload() {
//   MASKED_SECRET = 'I_SHOULD_BE_MASKED'
//   MASKED_SECRET1 = 'GOOGLE.COM'
//     wrap([$class: 'MaskPasswordsBuildWrapper', 
//     varPasswordPairs: [[password: MASKED_SECRET], [password: MASKED_SECRET1]]]) { 
//     echo 'Retrieve Secret: ' +  MASKED_SECRET
//     echo 'Retrieve Secret1: ' +  MASKED_SECRET1  
//     }
//   NEXUS_USER = sh ( script: 'aws ssm get-parameter --name prod.nexus.user --with-decryption | jq .Parameter.Value | xargs', returnStdout: true ).trim()
              
//   sh 'echo ${TAG_NAME} >VERSION'
//   // if (app_lang == "nodejs" || app_lang == "angular") { 
//   sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://172.31.5.233:8081/repository/${component}/${component}-${TAG_NAME}.zip'
// // }

sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 598125288280.dkr.ecr.us-east-1.amazonaws.com'
sh 'docker push 598125288280.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
}


