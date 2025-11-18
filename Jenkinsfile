pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Get source code from repository'
                checkout scm
            }
        }
        stage('Compile') {
            steps {
                echo 'Compile the project'
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                echo 'Test the project'
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                echo 'Package the project'
                sh 'mvn package -DskipTests'
            }
        }
        stage('Docker build') {
             steps {
                 echo 'Build Docker image'
                 sh 'docker build -t user-service-ajh:1.0 .'
             }
        }
         stage('Docker Compose Restart') {
              steps {
                  echo 'Restarting user services'
                  sh """
                      docker compose up -d postgres-user-dev user-service-dev
                  """

              }
         }

    }

}