# Jenkins CI implementation for Glowstone

## Introduction

This project was developed to provide a working demo of the Jenkins CI process I developed for the [Glowstone](https://glowstone.net/) open source Minecraft Server. [Glowstone](https://glowstone.net/) is a Java-based project built with Maven (using circleci internally) and provides end users with Bash scripts to build Glowstone with Maven on their own machines. This GitHub project contains the steps necessary to implement a pipelined Jenkins CI build process using the scripts and documentation provided within this project.

## Opportunities for improvement

1. Automate installation and configuration of Jenkins.
    * Including (and not limited to) plugin installation, global configuration settings, and LDAP user authentication using an enterprise LDAP solution.
        * Automation to be provided in Ansible (preferably).

2. Implement Jenkins as a scalable microservice.

3. Implement deployment process for Glowstone builds produced by build environment.