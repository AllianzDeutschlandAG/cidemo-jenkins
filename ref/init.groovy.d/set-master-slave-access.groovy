#! /usr/bin/env groovy
import jenkins.model.*
import jenkins.security.s2m.*

println "--> Setting master -> slave security"

Jenkins.instance.injector.getInstance(AdminWhitelistRule.class)
    .setMasterKillSwitch(false)
Jenkins.instance.save()
