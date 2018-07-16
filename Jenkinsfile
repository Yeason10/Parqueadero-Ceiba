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
      }
    }
    
    stage('Unit Tests')
    {
      steps
      {
         echo "------------>Unit Tests<------------"
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
       echo '------------>An�lisis de c�digo est�tico<------------'
       withSonarQubeEnv('sonar')
       {
         sh "${tool name: 'SonarScanner',type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
       }
      }
     }
     
     stage('Build')
     {
       steps
       {
         echo "------------>Build<------------"
       }
      }
   }
    
   post
   {
     always
     {
      echo 'This will always run'
     }
     
     success
     {
      echo 'This will run only if successful'
     }
     
     failure
     {
      echo 'This will run only if the run was marked as unstable'
     }
     
     changed
     {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
     }
   }
}
    
   
   