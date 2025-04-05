pipeline {
    agent any

    environment {
        EMAIL_RECIPIENTS = 'xman.mhasrup@gmail.com'
    }

    options {
        // Ensures entire pipeline gets marked failed on any stage failure
        skipStagesAfterUnstable()
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Simulate failure
                // error("Build failed") 
            }
        }

        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }

    post {
        success {
            emailext(
                subject: "✅ Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) Succeeded",
                body: """
                    <p>Good news!</p>
                    <p>Job <b>${env.JOB_NAME}</b> build #<b>${env.BUILD_NUMBER}</b> was successful.</p>
                    <p><a href="${env.BUILD_URL}">Click here to view the build</a></p>
                """,
                mimeType: 'text/html',
                to: "${env.EMAIL_RECIPIENTS}"
            )
        }

        failure {
            emailext(
                subject: "❌ Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) Failed",
                body: """
                    <p><b>Failure Alert!</b></p>
                    <p>Job <b>${env.JOB_NAME}</b> build #<b>${env.BUILD_NUMBER}</b> failed.</p>
                    <p><a href="${env.BUILD_URL}">Click here to view the build log</a></p>
                """,
                mimeType: 'text/html',
                to: "${env.EMAIL_RECIPIENTS}"
            )
        }

        always {
            echo 'Pipeline finished.'
        }
    }
}
