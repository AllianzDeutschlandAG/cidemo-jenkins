#! /usr/bin/env groovy
import jenkins.model.*
import jenkins.security.s2m.*

println "--> Enable Slave → Master Access Control"

Jenkins.instance.injector.getInstance(AdminWhitelistRule.class)
    .setMasterKillSwitch(false);
Jenkins.instance.save()
