#! /usr/bin/env groovy
import jenkins.plugins.git.GitSCMSource
import org.jenkinsci.plugins.workflow.libs.GlobalLibraries
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration
import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever

println "--> Pre-configuring demo-pipeline-library"

String remote = System.getenv('PIPELINE_LIBRARY_REMOTE')
String id = java.util.UUID.randomUUID().toString()
String libraryId = 'demo-pipeline-library'
String credentialsId = 'github'
String remoteName = 'origin'
String rawRefSpecs = '+refs/heads/*:refs/remotes/origin/*'
String includes = '*'
String excludes = ''
boolean ignoreOnPushNotifications = false

GlobalLibraries descriptor = GlobalLibraries.get()
GitSCMSource scmSource = new GitSCMSource(id,
        remote,
        credentialsId,
        remoteName,
        rawRefSpecs,
        includes,
        excludes,
        ignoreOnPushNotifications)

SCMSourceRetriever retriever = new SCMSourceRetriever(scmSource)
LibraryConfiguration libraryConfiguration = new LibraryConfiguration(libraryId, retriever)
libraryConfiguration.setDefaultVersion('master')
libraryConfiguration.setImplicit(true)

descriptor.setLibraries([libraryConfiguration])
descriptor.save()
