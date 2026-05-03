#! /user/bin/env groovy


// Reusable Slack notification function

def call(Map config = [:]) {

    // Slack message
    def message = config.message

    // Slack channel
    def channel = config.channel ?: '#devops'

    // Send Slack notification
    slackSend(
        channel: channel,
        message: message
    )
}


// usage in jenkinsfile

// post {

//     success {
//         slackNotification(
//             message: 'Deployment Successful'
//         )
//     }

//     failure {
//         slackNotification(
//             message: 'Pipeline Failed'
//         )
//     }
// }