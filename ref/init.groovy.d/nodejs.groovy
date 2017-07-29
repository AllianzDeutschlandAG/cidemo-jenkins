#! /usr/bin/env groovy

// Original: https://gist.github.com/mllrjb/8c8c30d9bd54532bf6e6a62031e7e3a6

import jenkins.model.*
import hudson.model.*
import jenkins.plugins.nodejs.tools.*
import hudson.tools.*

def inst = Jenkins.getInstance()
def desc = inst.getDescriptor("jenkins.plugins.nodejs.tools.NodeJSInstallation")

def versions = [
  "node-8": "8.2.1",
  "node-7": "7.10.1",
  "node-6": "6.11.1"
]
def installations = [];

for (v in versions) {
  def installer = new NodeJSInstaller(v.value, "", 100)
  def installerProps = new InstallSourceProperty([installer])
  def installation = new NodeJSInstallation(v.key, "", [installerProps])
  installations.push(installation)
}

desc.setInstallations(installations.toArray(new NodeJSInstallation[0]))

desc.save()
