pipeline {
    agent any

    environment {
        IMAGE_NAME = 'fayzhub/gaming-app'
        IMAGE_TAG  = "${env.BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
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
                    sh '''
                        echo "===== MAVEN TEST START ====="
                        whoami
                        pwd
                        java -version
                        mvn -version
                        mvn clean test
                        echo "===== MAVEN TEST END ====="
                    '''
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
                    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        stage('Docker Run') {
            steps {
                sh """
                    docker rm -f gaming-app-container || true

                    docker run -d \
                        --name gaming-app-container \
                        ${IMAGE_NAME}:${IMAGE_TAG}

                    sleep 3

                    docker logs gaming-app-container

                    if [ "\$(docker inspect -f '{{.State.Running}}' gaming-app-container)" != "true" ]; then
                        echo "ERROR: Container stopped unexpectedly."
                        exit 1
                    fi
                """
            }
        }

        stage('Docker Tag') {
            steps {
                sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${IMAGE_NAME}:latest"
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    sh """
                        echo "\$DOCKER_PASSWORD" | docker login \
                            -u "\$DOCKER_USERNAME" \
                            --password-stdin

                        docker push ${IMAGE_NAME}:${IMAGE_TAG}
                        docker push ${IMAGE_NAME}:latest

                        docker logout
                    """
                }
            }
        }

        stage('Deploy') {
            steps {
                sh """
                    docker pull ${IMAGE_NAME}:${IMAGE_TAG}

                    docker rm -f gaming-app-container || true

                    docker run -d \
                        --name gaming-app-container \
                        -p 8081:8080 \
                        ${IMAGE_NAME}:${IMAGE_TAG}

                    sleep 3

                    docker logs gaming-app-container

                    if [ "\$(docker inspect -f '{{.State.Running}}' gaming-app-container)" != "true" ]; then
                        echo "ERROR: Deployment container is not running."
                        exit 1
                    fi
                """
            }
        }

        stage('Health Check') {
            steps {
                sh '''
                    echo "===== APPLICATION HEALTH CHECK ====="

                    for i in 1 2 3 4 5; do

                        if curl -f http://localhost:8081/; then
                            echo "======================================"
                            echo "APPLICATION HEALTH CHECK PASSED"
                            echo "Application is UP"
                            echo "======================================"
                            exit 0
                        fi

                        echo "Application not ready... retrying"
                        sleep 2
                    done

                    echo "======================================"
                    echo "APPLICATION HEALTH CHECK FAILED"
                    echo "======================================"

                    docker logs gaming-app-container

                    exit 1
                '''
            }
        }
    }

    post {
        always {
            echo "Pipeline finished with status: ${currentBuild.currentResult}"
        }

        failure {
            echo 'Pipeline failed — check logs above for details.'
        }

        success {
            echo "Deployed ${IMAGE_NAME}:${IMAGE_TAG} successfully."
        }
    }
}