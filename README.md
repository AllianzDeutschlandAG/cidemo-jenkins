# JavaScript CI Demo - Jenkins

For the Do-It-Yourself Full Stack JavaScript Continuous Integration (CI) demo, we will use [Jenkins](https://jenkins.io/) as our automation server for builds, tests and deployments. Jenkins is an open-source tool anyone can easily setup.

![Beautiful UI with Blue Ocean UI](./images/blue-ocean-1.png)

We will use the [Jenkins community image from the Docker Registry](https://hub.docker.com/r/jenkins/jenkins/).

## Use

Clone this repository. And then run:

```
docker-compose up --build
```

- Then you can view Jenkins in your browser at [http://localhost:8080/](http://localhost:8080/)
- Login with username `cidemo` and password `cidemo`.

## Jenkins Image

Our `Dockerfile` has created a custom Jenkins image with two additional features:

- Chrome browser is installed and is used for unit tests.
- Cloud Foundry CLI which is used for deployment.

### LTS Tag

This demo uses the Long Term Support (LTS) tag, instead of default latest.

```
FROM jenkins/jenkins:lts
```

When logged-in, the UI sometimes shows this kind of message appears:

> New version of Jenkins (2.121.1) is available for download (changelog).

It may also accompany a warning. This is just a demo. When you customizing your own Jenkins image, you will probably want to be more specific about which tag you choose.

### Plugins

We also preloaded our Jenkins with some plugins for our toolchain and an improved workflow:

- [NodeJS Plugin](https://plugins.jenkins.io/nodejs)
- [Pipeline 2.5](https://plugins.jenkins.io/workflow-aggregator) for latest syntax including declarative pipelines
- [Pipeline Utility Steps](https://plugins.jenkins.io/pipeline-utility-steps) for zipping files
- [Pipeline: Build Step](https://plugins.jenkins.io/pipeline-build-step) for triggering other jobs
- [Job DSL](https://plugins.jenkins.io/job-dsl) for programmatically adding jobs
- [Timestamper](https://plugins.jenkins.io/timestamper) for timestamps in console output
- [Blue Ocean](https://plugins.jenkins.io/blueocean) for a redesigned Jenkins experience
- [Artifactory](https://plugins.jenkins.io/artifactory) for managing our build artifacts

### Skip Startup

These changes were made for convenience of demo presentations:

- skips setup wizard.
- sets basic security defaults to avoid warnings on load.

These  are _not meant as a recommendation_! For more about preinstalled configurations, see the [official documentation](https://github.com/jenkinsci/docker/blob/master/README.md).

## Pipelines

Jenkins is an automation server. We define workflows and jobs with pipelines, which can be groovy or a beautiful DSL, for example:

```groovy
#!/usr/bin/env groovy

pipeline {
    agent any

    options {
        disableConcurrentBuilds()
        timestamps()
    }

    tools {
        nodejs 'node-8'
    }

    parameters {
        string( name: 'APP_BASE_URL',
                defaultValue: 'https://localhost/',
                description: 'App Base URL to run e2e tests against')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('NPM Install') {
            steps {
                sh 'npm install'
            }
        }

        stage('E2E Tests') {
            environment {
                APP_BASE_URL = "$params.APP_BASE_URL"
            }

            steps {
                sh 'npm run e2e:prod'
            }
        }
    }
}
```

It is that simple to define a pipeline using the [Jenkins Declarative Pipeline Syntax](https://jenkins.io/doc/book/pipeline/syntax/).
