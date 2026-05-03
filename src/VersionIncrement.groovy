// use case in docker stage for all of the below
stage('Docker Build') {
    steps {
        sh "docker build -t mygoapp:${APP_VERSION} ."
    }
}



// ---------GO------------------//

stage('Generate Version') {
    steps {
        script {

            env.APP_VERSION = sh(
                script: "git rev-parse --short HEAD",
                returnStdout: true
            ).trim()

            echo "Version: ${APP_VERSION}"
        }
    }
}

// -----------.NET----------------//

stage('Increment Version') {
    steps {
        script {

            def version = sh(
                script: "grep '<Version>' app.csproj | sed 's/.*<Version>\\(.*\\)<\\/Version>.*/\\1/'",
                returnStdout: true
            ).trim()

            def parts = version.tokenize('.')

            parts[2] = (parts[2].toInteger() + 1).toString()

            env.APP_VERSION = parts.join('.')

            sh """
            sed -i 's/<Version>${version}<\\/Version>/<Version>${APP_VERSION}<\\/Version>/g' app.csproj
            """

            echo "New Version: ${APP_VERSION}"
        }
    }
}


// ------------PYHTON---------------------//

stage('Increment Version') {
    steps {
        script {

            sh 'poetry version patch'

            env.APP_VERSION = sh(
                script: "poetry version -s",
                returnStdout: true
            ).trim()

            echo "New Version: ${APP_VERSION}"
        }
    }
}


// -----------NODE JS-----------------//

stage('Increment Version') {
    steps {
        script {

            sh 'npm version patch --no-git-tag-version'

            env.APP_VERSION = sh(
                script: "node -p \"require('./package.json').version\"",
                returnStdout: true
            ).trim()

            echo "New Version: ${APP_VERSION}"
        }
    }
}

// --------------GRADLE----------------------------//
stage('Increment Version') {
    steps {
        script {
            def version = sh(
                script: "grep '^version' build.gradle | cut -d\"'\" -f2",
                returnStdout: true
            ).trim()

            def parts = version.tokenize('.')

            parts[2] = (parts[2].toInteger() + 1).toString()

            env.APP_VERSION = parts.join('.')

            sh """
            sed -i "s/version = '${version}'/version = '${APP_VERSION}'/" build.gradle
            """

            echo "New Version: ${APP_VERSION}"
        }
    }
}

// -----------------PHP LARAVEL-----------------------//
stage('Increment Version') {
    steps {
        script {

            def version = readFile('version.txt').trim()

            def parts = version.tokenize('.')

            parts[2] = (parts[2].toInteger() + 1).toString()

            env.APP_VERSION = parts.join('.')

            writeFile file: 'version.txt', text: env.APP_VERSION

            echo "New Version: ${APP_VERSION}"
        }
    }
}


// -----------------MAVEN-------------------//
stage('Increment Version') {
    steps {
        script {
            sh '''
            mvn build-helper:parse-version versions:set \
            -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion} \
            versions:commit
            '''

            env.APP_VERSION = sh(
                script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout",
                returnStdout: true
            ).trim()

            echo "New Version: ${APP_VERSION}"
        }
    }
}

