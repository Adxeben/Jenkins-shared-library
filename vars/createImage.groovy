#!/user/bin/env groovy

def call() {
    echo "creating the docker image..."
    sh "docker build -t sunesis003/app-jenkins:jsl-6.0 ."
}