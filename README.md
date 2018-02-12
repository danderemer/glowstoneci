# Jenkins CI implementation for Glowstone

## Introduction

This project was developed to provide a working demo of the Jenkins CI process I developed for the [Glowstone](https://glowstone.net/) open source Minecraft Server. This project is to demonstrate my ability to leverage the Jenkins Job DSL plugin to provide a source controlled, repeatable process to build Jenkins jobs. This project contains the documenation and scripts necessary for one to implement a pipelined Jenkins CI build process for the Glowstone Minecraft Server.

## Background

The [GlowstoneMC](https://github.com/GlowstoneMC/Glowstone) GitHub page contains a `.circleci` file used by the project team internally but the suggested method of building Glowstone is a process executed via bash script. The decision to use Glowstone for this demonstration was that it is a small project with a working Maven `pom.xml` provided by developers but no Jenkins CI process. This is a common scenario found in most DevOps practices: a process to build the code manually is made available to the DevOps engineer and the DevOps engineer is tasked with building the CI process for the code produced.

## Project Overview

This glowstoneci project is demonstration of my ability to construct a build pipeline in Jenkins for a project that does not have a Jenkins CI pipeline. Using the Maven `pom.xml` provided by the GlowstoneMC project, I assembled job scripts used by Jenkins to run the build, static analysis and unit testing used by the Glowstone project team.

## Execution

Steps to create the build process for Glowstone can be found under [./docs/runbook.md](https://github.com/danderemer/glowstoneci/blob/master/docs/runbook.md)

## Callouts

1. Glowstone is not threadsafe

    While assembling the Jenkins job scripts, I leveraged examples provided by the Jenkins project documentation and the Job DSL plugin reference. The examples provided were sufficient to build the Glowstone server as is. I did notice warnings in the build logs regarding the `md-5` Maven plugin was generating warnings about not being threadsafe. I resolved this issue by restricting the Maven build process to a single thread using using the following command switch:

    ```bash
    -T 1C
    ```

2. Pipeline scripts are taken from examples and heavily modified

    I started with examples for similar pipeline jobs and modified them to make them my own. This was a highly iterative process and I revised the scripts many times to get them working in a satisfactory way. I did learn quite a bit along the way about Jenkins Job DSL.

## Opportunities for Improvement

1. Automate installation and configuration of Jenkins.
    * Including (and not limited to) plugin installation, global configuration settings, and LDAP user authentication using an enterprise LDAP solution.
        * Automation to be provided in Ansible (preferably).

2. Implement Jenkins as a scalable microservice.

3. Implement deployment process for Glowstone builds produced by build environment.