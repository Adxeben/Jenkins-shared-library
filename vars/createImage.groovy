#!/user/bin/env groovy

def call(String imageName) {
    echo "creating the docker image..."
    sh "docker build -t $imageName ."
}