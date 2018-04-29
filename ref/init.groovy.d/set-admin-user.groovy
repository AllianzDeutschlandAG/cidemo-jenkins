#! /usr/bin/env groovy
import hudson.security.*
import jenkins.model.*

println "--> Configuring default user and password"

String user = System.getenv('ADMIN_USER')
String pass = System.getenv('ADMIN_PASSWORD')
def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(user, pass)
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)
instance.save()
