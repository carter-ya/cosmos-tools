package com.github.carterya.cosmostools.services

import com.intellij.openapi.project.Project
import com.github.carterya.cosmostools.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
