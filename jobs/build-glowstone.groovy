node {
   def mvnHome
   stage('Preparation') {
      // Get some code from a GitHub repository
      git branch: 'dev', 
          url: 'https://github.com/GlowstoneMC/Glowstone.git'
      // Get the Maven tool.
      // ** NOTE: Configured in Global Tool Configuration   
      mvnHome = tool 'Maven-3.5.2'
   }
   stage('Build') {
      // Run the maven build
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
            archiveArtifacts artifacts: 'target/glowstone.jar',
                                        fingerprint: true
            junit 'target/surefire-reports/**/*.xml'
          }
    stage('Analysis') {
        step([$class: 'CheckStylePublisher', pattern: 'target/checkstyle-result.xml'])
    }

}