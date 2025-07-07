@Library('jenkins-shared-lib') _


pipeline {
    agent any

    tools {
        maven 'maven3'
        dockerTool 'docker'
    }

    environment {
        CI = 'true'
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'
        DOCKER_ORGANIZATION = 'tonyq2k3'
        DOCKER_IMAGE_NAME = 'library-backend'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout code') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        // stage('Build and push image') {
        //     steps {
        //         dockerBuildAndPush(
        //             organization: DOCKER_ORGANIZATION,
        //             imageName: DOCKER_IMAGE_NAME,
        //             tag: DOCKER_IMAGE_TAG,
        //             credentialsId: DOCKER_CREDENTIALS_ID
        //         )
        //     }
        // }
    }
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}