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

## Preinstalled Configurations

Our `Dockerfile` has minimal lines of code:

```
FROM jenkins/jenkins:2.71-alpine

COPY ["config/jenkins.CLI.xml", "/var/jenkins_home"]
COPY ["ref/init.groovy.d/*", "/usr/share/jenkins/ref/init.groovy.d/"]

COPY ["plugins.txt", "/usr/share/jenkins/ref/plugins.txt"]
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
```

which on top of the base image it:

- skips setup wizard.
- sets basic security defaults to avoid warnings on load.
- pre-installs set of plugins.

These changes were made for convenience for a presentation and are _not meant as a recommendation_. For more more about preinstalled configurations, see the [official documentation](https://github.com/jenkinsci/docker/blob/master/README.md).

## Plugins

The `plugins.txt` list are from the default installation plus:

- [NodeJS Plugin](https://plugins.jenkins.io/nodejs)
- [Pipeline Utility Steps](https://plugins.jenkins.io/pipeline-utility-steps) for zipping files
- [Job DSL](https://plugins.jenkins.io/job-dsl) for programmatically adding jobs
- [Blue Ocean](https://plugins.jenkins.io/blueocean) for a redesigned Jenkins experience
