FROM jenkins/jenkins:2.70-alpine

MAINTAINER Julie Ng <hello@julie.io>

COPY ["config/jenkins.CLI.xml", "/var/jenkins_home"]

COPY plugins.txt /usr/share/jenkins/plugins.txt

RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt
