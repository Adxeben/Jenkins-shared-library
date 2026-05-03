#! /user/bin/env groovy
  
// Reusable application build function

def call(Map config = [:]) {

    // Application type
    def type = config.type

    switch(type) {

        // Java Maven application
        case 'maven':
            sh 'mvn clean package'
            break

        // Java Gradle application
        case 'gradle':
            sh './gradlew build'
            break

        // Node.js application
        case 'node':
            sh 'npm install'
            sh 'npm run build'
            break

        // Python application
        case 'python':
            sh 'pip install -r requirements.txt'
            break

        // PHP application
        case 'php':
            sh 'composer install'
            break

        default:
            error "Unsupported application type: ${type}"
    }
}


// use case in jenkinsfile

// stage('Build') {
//     steps {
//         buildApplication(type: 'maven')
//     }
// }

