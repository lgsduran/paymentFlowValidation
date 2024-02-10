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
  echo 'building project-a'
  sh 'mvn -B -DskipTests clean package'
  archiveArtifacts artifacts: 'target/*.jar', fingerprint: true;
  def timeStamp = Calendar.getInstance().getTime().format('ddMMYYYY_hhmmss', TimeZone.getTimeZone('CST'));
  def target = "screenshot_$timeStamp";
  dir("${target}") {
    sh "cp -f ../screenshot/* ${env.WORKSPACE}/${target}";
    zip zipFile: "${env.WORKSPACE}/${target}.zip", archive: true;

    deleteDir();
  }
}