#! /usr/bin/env groovy
import java.util.Arrays
import java.util.HashSet
import jenkins.util.SystemProperties
import jenkins.CLI
import jenkins.model.Jenkins
import org.jenkinsci.remoting.engine.JnlpProtocol4Handler

println "--> Disabling deprecated JNLP protocols"

Jenkins instance = Jenkins.getInstance()

// Disable jnlp by default, but honor system properties
instance.setSlaveAgentPort(SystemProperties.getInteger(Jenkins.class.getName()+".slaveAgentPort",-1))

// Disable CLI over Remoting
CLI.get().setEnabled(false)

// Disable old Non-Encrypted protocols ()
HashSet<String> newProtocols = new HashSet<>(instance.getAgentProtocols())
newProtocols.removeAll(Arrays.asList(
         "JNLP3-connect", "JNLP2-connect", "JNLP-connect", "CLI-connect"
))
instance.setAgentProtocols(newProtocols)
instance.save()
