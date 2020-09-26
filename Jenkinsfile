pipeline {

    agent any // defini qual agente irá executar o pipeline. any -> primeiro ambiente disponível (Jenkins Master - Própria máquina)

    stages {
        stage('Clean') {
            steps {
                echo "./mvnw clean"
            }
        }
        stage("Build") {
            steps {
                echo "./mvnw package -X test"
            }
        }
        stage('Test') {
            steps {
                echo "./mvnw test"
                //step([$class: "JUnitResultArchiver", testResults: "**/target/surefire-reports/TEST-*.xml"])
            }
        }
        stage('JaCoCo') {
            steps {
                echo 'Code Coverage'
                jacoco()
            }
        }
    }
}