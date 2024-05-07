def buildJar(){
    echo "building app"
    sh "mvn package"
}

def buildImage(){
    echo "building docker image"
    withCredentials([usernamePassword(credentialsId: "dockerhub-credentials", passwordVariable: "PASS", usernameVariable:"USER")]){
    sh "docker build -t omarriad07/demo-app:$IMAGE_NAME ."
    sh 'echo $PASS | docker login -u $USER --password-stdin'
    sh "docker push omarriad07/demo-app:$IMAGE_NAME"
    }
}

def deployApp(){
    echo "deploying app"
}
return this