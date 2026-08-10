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
    }
}