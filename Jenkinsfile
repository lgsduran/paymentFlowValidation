pipeline {
    agent any

    parameters {
        choice (
        name: 'TAG',
        choices: ['@order', '@positive', '@negative'],
        description: 'Choose any tag.'
        )
    }

    options {
       ansiColor('xterm')
    }

    stages {
        stage('Testing') {
            steps {
                sh "mvn test -Dcucumber.filter.tags=${params.TAG}"
            }
        }
    }

    post {
        always {
            echo 'One way or another, I have finished'
            CreateZipFile(timeStamp)
        }
        success {
            echo 'I succeeded!'
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
        }
        changed {
            echo 'Things were different before...'
        }
    }

def CreateZipFile(timeStamp){
    echo 'building project-a'
    sh 'mvn -B -DskipTests clean package'
    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    zip zipFile: "../screenshot_"+timeStamp+".zip", archive: true, dir: "."
}
 def timeStamp = Calendar.getInstance().getTime().format('YYYYMMdd-hhmmss',TimeZone.getTimeZone('CST'));
}
