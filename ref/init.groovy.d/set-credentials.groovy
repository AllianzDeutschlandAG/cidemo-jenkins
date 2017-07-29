#! /usr/bin/env groovy
// Original: https://support.cloudbees.com/hc/en-us/articles/217708168-create-credentials-from-groovy
import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

// Create Placeholder Credentials
Credentials c = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, "github", "GitHub.com Credentials", "github-user", "secret")
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c)
