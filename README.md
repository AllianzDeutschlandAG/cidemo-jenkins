# JavaScript CI Demo - Jenkins

For the Do-It-Yourself Full Stack JavaScript Continuous Integration (CI) demo, we will use [Jenkins](https://jenkins.io/) as our automation server for builds, tests and deployments. Jenkins is an open-source tool anyone can easily setup. 

We will use the [Jenkins community image from the Docker Registry](https://hub.docker.com/r/jenkins/jenkins/).

## Use

Start Jenkins in the [CI Demo parent project](https://github.com/julie-ng/js-cidemo) with docker compose

```
docker-compose up -d --build jenkins
```

- Then you can view Jenkins in your browser at [http://localhost:8080/](http://localhost:8080/)
- Login with username `cidemo` and password `cidemo`.

## Jenkins Image

Our `Dockerfile` has created a custom Jenkins image, which two additional features:

- the Chrome browser is installed and will be used for unit tests.
- we've added the Cloud Foundry CLI which will be using for deployment.

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

These changes were made for convenience for a presentation:

- skips setup wizard.
- sets basic security defaults to avoid warnings on load.

and are _not meant as a recommendation_! For more more about preinstalled configurations, see the [official documentation](https://github.com/jenkinsci/docker/blob/master/README.md).

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
        nodejs 'node-7'
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

The pipeline code above is just an example of simple it is to define jobs using the [Jenkins Declarative Pipeline Syntax](https://jenkins.io/doc/book/pipeline/syntax/).

## License (MIT)

Copyright (c) 2017 Julie Ng.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
