#!/user/bin/env groovy

def call() {
    echo "building and testing the application for $BRANCH_NAME branch"
    sh "mvn clean package"
}