#!/user/bin/env groovy


def call() {
    echo "publishing to docker hub repo..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push sunesis003/app-jenkins:5.0"
    }
}