package com.github.uonagent.kotlindslbeansextendedplugin.services

import com.github.uonagent.kotlindslbeansextendedplugin.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
