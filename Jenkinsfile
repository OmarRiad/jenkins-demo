pipeline{
  agent any
  tools{
    maven "maven-3.9"
  }
  stages{
    stage("test"){
        steps{
          script{
            echo "Testing the app"
            echo "On branch $BRANCH_NAME"
          }
        }
    }

    stage("build"){
      when{
        expression{
          BRANCH_NAME == "main"
        }
      }
        steps{
          script{
            echo "Building the app"
          }
        }
    }

    stage("deploy"){
      when{
        expression{
          BRANCH_NAME == "main"
        }
      }
        steps{
          script{
            echo "Deploying the app"
          }
        }
    }
    
  }
}
