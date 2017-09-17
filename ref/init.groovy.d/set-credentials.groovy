#! /usr/bin/env groovy
// Original: https://support.cloudbees.com/hc/en-us/articles/217708168-create-credentials-from-groovy
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*

println "--> Pre-configuring credentials"

Credentials c1 = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "github", "GitHub.com", "github-user", "placeholder")
Credentials c2 = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "pcf", "PCF Dev", "user", "pass")
Credentials c3 = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "artifactory", "Artifactory OSS", "admin", "password")

SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c1)
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c2)
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c3)
