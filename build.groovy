node {
  git url: 'https://github.com/prdur1/maven-project.git'
  def mvnHome = tool 'localMaven'
  env.PATH = "${mvnHome}/bin:${env.PATH}"
  stage('Example')
  {
     echo 'I Example'
    // Load the file 'externalMethod.groovy' from the current directory, into a variable called "externalMethod".
   // def externalMethod = load("externalMethod.groovy")

    // Call the method we defined in externalMethod.
  //  externalMethod.lookAtThis("Steve")
   }
  stage('buid')
  {
    echo 'Build'
  sh 'mvn clean package'
  }
}
