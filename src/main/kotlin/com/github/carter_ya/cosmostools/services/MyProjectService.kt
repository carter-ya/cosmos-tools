package com.github.carter_ya.cosmostools.services

import com.intellij.openapi.project.Project
import com.github.carter_ya.cosmostools.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
