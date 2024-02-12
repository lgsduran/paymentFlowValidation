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
    stage('Testing') {
      steps {
        sh "mvn test -Dcucumber.filter.tags=${params.TAG}"
      }
    }
  }

  post {
    always {
      echo 'One way or another, it has finished'
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
  def target = "screenshot_$timeStamp";
  archiveArtifacts artifacts: 'target/*.jar', fingerprint: true;
  dir("${target}") {
    //sh "cp -f ../screenshot/* ${env.WORKSPACE}/${target}";
    sh "cp -f ../screenshot/* ../${target}";
    //zip zipFile: "${env.WORKSPACE}/${target}.zip", archive: true;
    zip zipFile: '${target}.zip', archive: true;
    archiveArtifacts artifacts: '${target}.zip', fingerprint: true;
    deleteDir();
  }
}