pipeline {
    agent any

    parameters {        
        choice (
        name: 'TAGS',
        choices: ['@order', '@positive', '@negative'],
        description: 'Choose the tags.'
        )
    }
    
    options {
       ansiColor('xterm')
    }

    stages { 
        stage('Testing') {           
            steps {
                sh "mvn test -Dcucumber.filter.tags=${\\"params.TAG\\"}"
            }                
        }     
    }

    post {
        always {
            echo 'One way or another, I have finished'            
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
}
