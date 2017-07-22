FROM jenkins/jenkins:2.70-alpine

MAINTAINER Julie Ng <hello@julie.io>

COPY ["config/jenkins.CLI.xml", "/var/jenkins_home"]
COPY ["ref/init.groovy.d/*", "/usr/share/jenkins/ref/init.groovy.d/"]

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
