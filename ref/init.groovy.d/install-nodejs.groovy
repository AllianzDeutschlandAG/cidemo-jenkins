#! /usr/bin/env groovy
import jenkins.model.*
import hudson.model.*
import jenkins.plugins.nodejs.tools.*
import hudson.tools.*

println "--> Pre-installing NodeJS global tools"

def instance = Jenkins.getInstance()
def descriptor = instance.getDescriptor('jenkins.plugins.nodejs.tools.NodeJSInstallation')

def versions = [
  'node-8': '8.5.0',
  'node-7': '7.10.1',
  'node-6': '6.11.3'
]
def installations = []

for (v in versions) {
  def installer = new NodeJSInstaller(v.value, '', 100)
  def installerProps = new InstallSourceProperty([installer])
  def installation = new NodeJSInstallation(v.key, '', [installerProps])
  installations.push(installation)
}

descriptor.setInstallations(installations.toArray(new NodeJSInstallation[0]))
descriptor.save()
