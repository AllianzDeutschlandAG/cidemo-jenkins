# JavaScript CI Demo - Jenkins

For the Do-It-Yourself Full Stack JavaScript Continuous Integration (CI) demo, we will use [Jenkins](https://jenkins.io/) as our automation server for builds, tests and deployments. Jenkins is an open-source tool anyone can easily setup. We will use the [official Jenkins image from the Docker Registry](https://hub.docker.com/_/jenkins/).

Our `Dockerfile` has minimal lines of code:

```
FROM jenkins:latest

COPY plugins.txt /usr/share/jenkins/plugins.txt

RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt
```

## Plugins

The list inside `plugins.txt` are from the default installation, plus two more for the NodeJS Plugin:

- [NodeJS Plugin](https://plugins.jenkins.io/nodejs)
- [Config File Provider Plugin](https://wiki.jenkins.io/display/JENKINS/Config+File+Provider+Plugin)

The `plugins.sh` script is provided in the official image.

## Setup / First Run

You can view Jenkins in your browser at [http://localhost:8080/](http://localhost:8080/)

When you first run Jenkins, you will need to set an admin password and optionally create a user. When you build the image, you will be told to find the initial password at `/var/jenkins_home/secrets/initialAdminPassword`

Just run `docker ps` to get your container ID and use this command to get the password:

```
docker exec -it <CONTAINER_ID> cat /var/jenkins_home/secrets/initialAdminPassword
```
