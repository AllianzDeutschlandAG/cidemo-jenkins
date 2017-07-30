FROM jenkins/jenkins:2.71

# Install Chrome as root
USER root
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' && \
    apt-get update && \
    apt-get install google-chrome-stable -y

# Drop back to jenkins user
USER jenkins

COPY ["config/jenkins.CLI.xml", "/var/jenkins_home"]
COPY ["ref/init.groovy.d/*", "/usr/share/jenkins/ref/init.groovy.d/"]

COPY ["plugins.txt", "/usr/share/jenkins/ref/plugins.txt"]
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

ENV CHROME_BIN=/usr/bin/google-chrome-stable \
    JAVA_OPTS=-Djenkins.install.runSetupWizard=false
