import static java.net.URLDecoder.decode

def emailList = "xman.mhasrup.com"

def notifyRestartFailure(emailList) {
    env.ForEmailPlugin = env.WORKSPACE
    def subject = """Restart Application Server && Agent and integration servers failed: Job "${decode(env.JOB_NAME, 'UTF-8')}" [${env.NODE_NAME}] on ${decode(env.JOB_NAME, 'UTF-8')}"""

    emailext(
        presendScript: """msg.addHeader("X-Priority", "1 (Highest)"); msg.addHeader("Importance", "High");""",
        subject: subject,
        body: '''${JELLY_SCRIPT, template="html-with-health-and-console"}''',
        recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        to: emailList
    )
}

def notifyRestartComplete(emailList) {
    env.ForEmailPlugin = env.WORKSPACE
    def subject = """Restart Application Server Completed: Job "${decode(env.JOB_NAME, 'UTF-8')}" on ${decode(env.NODE_NAME, 'UTF-8')}"""

    emailext(
        presendScript: """msg.addHeader("X-Priority", "1 (Highest)"); msg.addHeader("Importance", "High");""",
        subject: subject,
        body: '''${JELLY_SCRIPT, template="html-with-health-and-console"}''',
        recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        to: emailList
    )
}




def notifySonarIssue() {
    env.ForEmailPlugin = env.WORKSPACE
    def subject = "SonarQube analysis failed!"

    emailext(
        presendScript: """msg.addHeader("X-Priority", "1 (Highest)"); msg.addHeader("Importance", "High");""",
        subject: subject,
        body: '''${JELLY_SCRIPT, template="html-with-health-and-console"}''',
        recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        to: emailList
    )
}

def notifyQEAutomatedTestSuiteFailed(emailList, artifactID) {
    env.ForEmailPlugin = env.WORKSPACE
    def subject = "${artifactID} WILL BE DEPLOYED TO QA"

    emailext(
        presendScript: """msg.addHeader("X-Priority", "1 (Highest)"); msg.addHeader("Importance", "High");""",
        subject: subject,
        body: '''${JELLY_SCRIPT, template="html-with-health-and-console"}''',
        to: emailList
    )
}
