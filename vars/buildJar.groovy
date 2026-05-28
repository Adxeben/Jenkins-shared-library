#!/user/bin/env groovy

def call() {
    echo "building and testing the application"
    sh "mvn clean package"
}