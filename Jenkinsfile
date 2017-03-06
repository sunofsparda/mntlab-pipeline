#!/bin/groovy

import hudson.AbortException
@NonCPS
def findInLog(log, re) {
    def match = log.find { line -> line =~ re }
    match ? (match =~ re)[0][1] : null
}
def ABORTED_RE = /^Aborted by (.+)$/

node ('host') {
    try {
        sleep 10
        println "Aborted by ${abortUser} ... "
    } catch (err) {
        def abortUser = findInLog(currentBuild.rawBuild.getLog(100), ABORTED_RE)
        if (abortUser) {
            println "Aborted by ${abortUser} ... "
        } else {
            printls "Just failed by some reason ..."
        }
        throw err
    }
} 
    
  
