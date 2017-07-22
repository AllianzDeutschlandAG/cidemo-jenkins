# JavaScript CI Demo - Jenkins

For the Do-It-Yourself Full Stack JavaScript Continuous Integration (CI) demo, we will use [Jenkins](https://jenkins.io/) as our automation server for builds, tests and deployments. Jenkins is an open-source tool anyone can easily setup. We will use the [official Jenkins image from the Docker Registry](https://hub.docker.com/_/jenkins/).

## Preinstalled Configurations

Our `Dockerfile` has minimal lines of code on top of the base image it

- sets the default admin username and password to `cidemo`
- skips setup wizard
- sets basic security defaults to avoid warnings on load
- pre-installs set of plugins

These changes were made for convenience for a presentation and are not meant as a recommendation. For more more about preinstalled configurations, see the [official docker image readme](https://github.com/jenkinsci/docker/blob/master/README.md).

## Plugins

The list inside `plugins.txt` are from the default installation, plus two more for the NodeJS Plugin:

- [NodeJS Plugin](https://plugins.jenkins.io/nodejs)
- [Pipeline Utility Steps](https://plugins.jenkins.io/pipeline-utility-steps) for zipping files
- [Job DSL](https://plugins.jenkins.io/job-dsl) for programmatically adding jobs
- [Blue Ocean](https://plugins.jenkins.io/blueocean) for a redesigned Jenkins experience

The `plugins.sh` script is provided in the official image.

## Setup / First Run

You can view Jenkins in your browser at [http://localhost:8080/](http://localhost:8080/)

When you first run Jenkins, you will need to set an admin password and optionally create a user. When you build the image, you will be told to find the initial password at `/var/jenkins_home/secrets/initialAdminPassword`

Just run `docker ps` to get your container ID and use this command to get the password:

```
docker exec -it <CONTAINER_ID> cat /var/jenkins_home/secrets/initialAdminPassword
```
