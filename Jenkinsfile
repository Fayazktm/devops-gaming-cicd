pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Git') {
            steps {
                sh 'git --version'
            }
        }

        stage('Java') {
            steps {
                sh 'java -version'
            }
        }

        stage('Maven') {
            steps {
                sh 'mvn -version'
            }
        }

        stage('Docker') {
            steps {
                sh 'docker --version'
            }
        }

        stage('Maven Test') {
            steps {
                dir('application') {
                    sh 'mvn clean test'
                }
            }
        }

        stage('Maven Package') {
            steps {
                dir('application') {
                    sh 'mvn package -DskipTests'
                }
            }
        }

        stage('Docker Build') {
            steps {
                dir('application') {
                    sh 'docker build -t gaming-app:1.0 .'
                }
            }
        }

        stage('Docker Run') {
            steps {
                sh '''
                    docker rm -f gaming-app-container || true
                    docker run --name gaming-app-container gaming-app:1.0
                    docker logs gaming-app-container
                '''
            }
        }

        stage('Docker Tag') {
            steps {
                sh 'docker tag gaming-app:1.0 fayzhub/gaming-app:1.0'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD'
                )]) {
                    sh '''
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                        docker push fayzhub/gaming-app:1.0
                        docker logout
                    '''
                }
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker pull fayzhub/gaming-app:1.0
                    docker rm -f gaming-app-container || true
                    docker run --name gaming-app-container fayzhub/gaming-app:1.0
                    docker logs gaming-app-container
                '''
            }
        }
    }
}