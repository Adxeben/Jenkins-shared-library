#! /user/bin/env groovy


// Reusable application testing function

def call(Map config = [:]) {

    // Application type
    def type = config.type

    switch(type) {

        // Java Maven tests
        case 'maven':
            sh 'mvn test'
            break

        // Java Gradle tests
        case 'gradle':
            sh './gradlew test'
            break

        // Node.js tests
        case 'node':
            sh 'npm test'
            break

        // Python tests
        case 'python':
            sh 'pytest'
            break

        // PHP tests
        case 'php':
            sh 'phpunit'
            break

        default:
            error "Unsupported application type: ${type}"
    }
}

// use case in jenkinsfile

// stage('Test') {
//     steps {
//         testApplication(type: 'maven')
//     }
// }