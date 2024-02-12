pipeline {
  agent any

  parameters {
    choice(
      name: 'TAG',
      choices: ['@order', '@positive', '@negative'],
      description: 'Choose any tag.'
    )
  }

  options {
    ansiColor('xterm')
  }

  stages {
    /*stage('Build') {
            steps {
                // Clean before build
                cleanWs()
                // We need to explicitly checkout from SCM here
                checkout scm
                echo "Building ${env.JOB_NAME}..."
            }*/
    }
    stage('Testing') {
      steps {
        sh "mvn test -Dcucumber.filter.tags=${params.TAG}"
      }
    }
  }

  post {
    always {
      echo 'One way or another, it has finished'
      cucumber buildStatus: 'UNCHANGED', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: 'target/cucumber.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
      createZipFile();
      dir("screenshot") {
        deleteDir();
      }
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

def createZipFile() {
  sh 'mvn -B -DskipTests clean package'
  def timeStamp = Calendar.getInstance().getTime().format('ddMMYYYY_hhmmss', TimeZone.getTimeZone('UTC'));
  def dest = "screenshot_${timeStamp}";
  archiveArtifacts artifacts: 'target/*.jar', fingerprint: true;
  dir("${dest}") { 
    echo "dest is ${timeStamp}."
    sh "cp -f ../screenshot/* ${env.WORKSPACE}/${dest}";
    zip zipFile: "${env.WORKSPACE}/${dest}.zip", archive: true;
  }
  archiveArtifacts artifacts: "${dest}.zip", fingerprint: true;
    dir("${dest}") {
        deleteDir();
    }
}