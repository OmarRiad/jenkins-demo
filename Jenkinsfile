pipeline{
  agent any
  tools{
    maven "maven-3.9"
  }
  stages{

    stage("build jar"){
        steps{
          echo "building app"
          sh "mvn package"
        }
    }
    stage("build image"){
        steps{
          echo "building docker image"
          withCredentials([usernamePassword(credentialsId: "dockerhub-credentials", passwordVariable: "PASS", usernameVariable:"USER")]){
            sh 'docker build -t omarriad07/demo-app:jma-2.0 .'
            sh "echo $PASS | docker login -u $USER --password-stdin"
            sh 'dokcer push omarriad07/demo-app:jma-2.0'
          }
        }
    }
    stage("deploy"){
        steps{
          echo "deploying app"
        }
    }
  }
}
