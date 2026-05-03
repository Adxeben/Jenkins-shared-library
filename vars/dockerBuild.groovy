#! /user/bin/env groovy


// Reusable Docker build function

def call(Map config = [:]) {

    // Docker image name
    def imageName = config.imageName

    // Docker image tag
    def imageTag = config.imageTag ?: 'latest'

    // Build Docker image
    sh "docker build -t ${imageName}:${imageTag} ."
}