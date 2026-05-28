#!/user/bin/env groovy

def call() {
    echo "creating the docker image..."
    sh "docker build -t sunesis003/app-jenkins:5.0 ."
}