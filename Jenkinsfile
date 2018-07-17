pipeline
{
 //Donde se va a ejecutar el Pipeline
  agent
  {
   label 'Slave_Induccion'
  }

  //Opciones especificas de Pipeline dentro del Pipeline
  options
  {
   //Mantener artefactos y salida de consola para el # especifico de ejecuciones
   //recientes del Pipeline
   buildDiscarder(logRotator(numToKeepStr:'3'))
   //No permitir ejecuciones concurrentes de Pipeline
   disableConcurrentBuilds()
  }
  
  //Una seccion que define las herramientas para "autoinstalar" y poner en la PATH
  tools
  {
   jdk 'JDK8_Centos' //Preinstalada en la configuracion del master.
   gradle 'Gradle4.5_Centos'//Preinstalada en la configuracion del master
  }
  
  //Aqui comienzan los items del pipeline
  stages
  { 
    stage('Checkout')
    {
      steps
      {
       echo "------------>Checkout<------------" 
       checkout([$class: 'GitSCM', branches: [ [ name: '*/master' ] ],
       doGenerateSubmoduleConfigurations: false, extensions: [ ], gitTool: 
       'Git_centos',submoduleCfg: [ ], userRemoteConfigs: [ [ credentialsId:
       'GitHub_Yeason10',url:
       'https://github.com/Yeason10/Parqueadero-Ceiba']]])
                                          
       }
     }
     
     step([$class: 'XUnitBuilder', testTimeMargin: '3000', thresholdMode: 1, thresholds: [[$class: 'FailedThreshold', failureNewThreshold: '', failureThreshold: '20', unstableNewThreshold: '1', unstableThreshold: '0'], [$class: 'SkippedThreshold', failureNewThreshold: '', failureThreshold: '', unstableNewThreshold: '', unstableThreshold: '']], tools: [[$class: 'JUnitType', deleteOutputFiles: true, failIfNotNew: false, pattern: '**/build/test-results/*.xml', skipNoTestFiles: true, stopProcessingIfError: true]]])
      
    stage('Unit Tests')
    {
      steps
      {
         echo "------------>Unit Tests<------------"
         sh 'gradle --b ./build.gradle test'
      }
    }
    
    stage('Integration Tests')
    {
      steps
      {
        echo "------------>Integration Tests<------------" 
      }
    }
    
    stage('Static code Analysis')
    {
     steps
     {
       echo '------------>Análisis de código estático<------------' 
       withSonarQubeEnv('Sonar') 
       { 
       	sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
       }
     }
    }
     stage('Build')
     {
       steps
       {
         echo "------------>Build<------------"
         //construir sin tarea test que se ejecuto previamente
         sh 'gradle --b ./build.gradle build -x test'
       }
      }
   }
    
   post
   {
     /*always
     {
      echo 'This will always run'
     }*/
     
     success
     {
      echo 'This will run only if successful'
      //junit '**/build/test-results/test/*.xml'
     }
     
     failure
     {
      echo 'This will run only if failed'
      mail (to:'yeason.ortiz@ceiba.com.co', subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "something is wrong with ${env.BUILD_URL}")
     }

     
     
     /*changed
     {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
     }*/
   }
}
    
   
   