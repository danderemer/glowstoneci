# Jenkins Project Demo Runbook

## Introduction

This document discusses how to automate the build process of Glowstone using Jenkins Job DSL. The resultant job produced by the Job DSL seed job points to a pipeline script in SCM.

## Intended Audience

This document is intended for individuals with familiarity in administering Linux systems and Java applications. This document assumes the audience is capable of producing a Fedora Server 27 VM with a standard user account which is capable of executing commands using the `sudo` command.

## Steps

1. Setup base OS
    * Fedora Server 27
        * 2 vCPUs
        * 4 GB RAM
        * 20 GB disk
            * LVM partitioning
                * 8 GB root partition (/)
                * 4 GB swap partition
                * 8 GB var partition (/var)

2. Install OpenJDK-1.8-headless
3. Install Jenkins

    * Added DNF repo and key in order to get latest stable version of Jenkins 2.x
        ```bash
        # Download repo file and place in /etc/yum.repos.d/
        sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo

        # Import key for the repo
        sudo rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key

        # ***NOTE*** the author of this document suggests the reader check the available version of Jenkins with 'dnf info jenkins' prior to installation to ensure the version available in the repo is the latest listed here: https://pkg.jenkins.io/redhat-stable/

        # Install Jenkins
        sudo dnf install jenkins
        ```
    * Start and enable Jenkins service
        ```bash
        sudo systemctl start jenkins
        sudo systemctl enable jenkins
        ```
    * Add Jenkins service port to `firewalld`
        ```bash
        sudo firewall-cmd --permanent --add-port=8080/tcp
        sudo firewall-cmd --reload
        ```

4. Initial Jenkins setup

    * URL will be `http://ip_address_of_vm:8080/`
    * Unlock Jenkins by locating the Administrator password in `/var/lib/jenkins/secrets/initialAdminPassword`
    * Customize Jenkins
        * The screen following the Unlock screen will ask about additional plugin installation. Select `Install suggested plugins`.
    * Jenkins will provide a summary/status screen of the plugin installation. Once the installation is complete, provide Jenkins with a new admin user account and password.

5. Preparation for job creation

    * Configure Maven
        * Click `Manage Jenkins` -> `Global Tool Configuration`
        * Locate the `Maven` section at the bottom of the page and click `Maven Installations`
            * Set the `Name` field to 'Maven-3.5.2'
            * Check `Install automatically`
            * Ensure the `Install from Apache` field lists version '3.5.2'
            * Click the `Save` button at the bottom of the page.
            * Click `Manage Jenkins` at the top left portion of the page.
    * Add Checkstyle Static Analysis plugins
        * Click `Manage Plugins`
        * Click the `Available` tab.
            * The author suggests the reader click `Check Now` at the bottom of the page to update the plugin list.
        * Click the checkboxes next to `Checkstyle Plug-in` and `Static Analysis Collector Plug-in` (not required but can be used to provide trend graphs)
        * Click `Download now and install after restart` to download and install the plugins.
        * Click the `Restart Jenkins...` checkbox and refresh your browser to ensure that Jenkins has restarted.
        * Click on the `Back to Dashboard` button to get back to the home screen.

6. Create DSL Seed Job.

    * Click `New Item` on the top left of the home screen.
    * Name the item "dsl-seed-glowstone"
    * On the configure page, scroll down to "Build" and select `Add build step`
        * Select `Process Job DSLs`
        * Under Process Job DSLs, select "Use the provided DSL script"
        * Paste in the contents of <https://raw.githubusercontent.com/danderemer/glowstoneci/master/jobs/dsl-seed-glowstone.groovy>
        * Save the job by clicking save at the bottom of the screen.
    * Run the job by clicking `Build Now` on the upper left corner of the screen.
    * Exectution of the seed job produced a job called "build-glowstone"
    * Click `Back to Dashboard` to check for the existence of the "build-glowstone" job.

7. Run "build-glowstone"
    * Hover the mouse cursor over the "build-glowstone" job and click `Build Now` to execute a build of glowstone.
    * Click the progress bar under the "build-glowstone" entry under "Build Executor Status" on the left side of the page to view the status of the build while it is active.
    * Observe the status page of the build for errors. Click `Console Output` to see the logs of the build.

## Conclusion

Provided all steps were completed without issue, a working build of Glowstone Minecraft server should be able to be reliably produced in the Jenkins build enviroment set up in this document. Further instructions for deployment and execution of Glowstone server will be provided in future documentation.