pipeline {
    agent any
    tools {
        maven 'localMaven'
       
    }
    stages{
        stage('Build'){
            steps {
                echo 'Now Build...'
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                    
                }
            }
        }
      stage('Checkstyle') {
       steps {
        sh 'vendor/bin/phpcs --report=checkstyle --report-file=`pwd`/build/logs/checkstyle.xml --standard=PSR2 --extensions=php --ignore=autoload.php --ignore=vendor/ . || exit 0'
        checkstyle pattern: 'build/logs/checkstyle.xml'
        }
      }  
        stage ('Deploy to Staging'){
            steps {
                build job: 'Deploy to staging'
            }
        }
        stage ('Deploy to Production'){
            steps{
                timeout(time:5, unit:'DAYS'){
                    input message:'Approve PRODUCTION Deployment?'
                }

                build job: 'Deploy to prod'
            }
            post {
                success {
                    echo 'Code deployed to Production.'
                }

                failure {
                    echo ' Deployment failed.'
                }
            }
        }
    }
}
