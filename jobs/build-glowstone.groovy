node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git branch: 'dev', 
          url: 'https://github.com/GlowstoneMC/Glowstone.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build

          if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"

            archiveArtifacts artifacts: 'target/glowstone.jar',
                                        fingerprint: true
            junit 'target/surefire-reports/**/*.xml'
          }

   }
}