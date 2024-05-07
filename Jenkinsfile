pipeline{
  agent any
  tools{
    maven "maven-3.9"
  }
  stages{

    stage("increment version"){
        steps{
            script{
                echo "incrementing app version"
                sh 'mvn build-helper:parse-version versions:set \
                -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                versions:commit'
                def matcher = readFile(pom.xml) =~ '<version>(.+)</version>'
                def version = matcher[0][1]
                env.IMAGE_NAME = "$version-$BUILD_NUMBER"
            }
        }
    }

    stage("build jar"){

        steps{
          script{
            echo "building app"
            sh "mvn clean package"
          }
        }
    }

    stage("build image"){
        steps{
          script{
            echo "building docker image"
            withCredentials([usernamePassword(credentialsId: "dockerhub-credentials", passwordVariable: "PASS", usernameVariable:"USER")]){
            sh "docker build -t omarriad07/demo-app:${IMAGE_NAME} ."
            sh 'echo $PASS | docker login -u $USER --password-stdin'
            sh "docker push omarriad07/demo-app:${IMAGE_NAME}"
          }
        }
      }
    }
    stage("deploy"){

        steps{
          script{
            echo "deploying app"
          }
        }
    }
    
  
  }
}
