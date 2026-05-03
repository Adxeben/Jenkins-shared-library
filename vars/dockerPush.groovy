#! /user/bin/env groovy

// Reusable Docker push function

def call(Map config = [:]) {

    // Docker image name
    def imageName = config.imageName

    // Docker image tag
    def imageTag = config.imageTag ?: 'latest'

    // Jenkins credentials ID
    def credentialsId = config.credentialsId


// Load Docker credentials securely
    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {

        // Login to Docker registry
        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'

        // Push Docker image
        sh "docker push ${imageName}:${imageTag}"
    }
}