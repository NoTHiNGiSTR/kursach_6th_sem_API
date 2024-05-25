package org.mirea.kursachapi.api.controller

import org.mirea.kursachapi.api.services.ProjectService
import org.mirea.kursachapi.store.dto.ProjectDto
import org.mirea.kursachapi.store.dto.TaskDto
import org.mirea.kursachapi.store.entities.ProjectEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/projects")
class ProjectController(private val projectService: ProjectService) {


    @PostMapping("/create")
    fun createProject(@RequestBody projectDto: ProjectDto): ProjectDto?{
        return projectService.createProject(projectDto)
    }

    @DeleteMapping("/delete/{projectUuid}")
    fun deleteProject(@PathVariable projectUuid: String){
        projectService.deleteProject(projectUuid)
    }


    @PostMapping("/edit")
    fun editProject(@RequestBody projectDto: ProjectDto): ProjectDto?{
        return projectService.editProject(projectDto)
    }

    @PostMapping("/add-task")
    fun addTaskToProject(@RequestBody taskDto: TaskDto){
        projectService.addTaskToProject(taskDto)
    }

    @GetMapping("/get/{userUuid}")
    fun getProjectsByUserUuid(@PathVariable("userUuid") userUuid : String) : List<ProjectEntity>?{
        return projectService.getProjectsByUserUuid(userUuid)
    }

    @GetMapping("/get-all")
    fun getAllProjects() : List<ProjectEntity>{
        return projectService.getAllProjects()
    }

    @GetMapping("/get-project-tasks/{projectUuid}")
    fun getProjectTasks(@PathVariable projectUuid: String) : List<TaskDto>{
        return projectService.getProjectTasks(projectUuid)
    }

    @DeleteMapping("/delete-task-from-project/{taskUuid}/{projectUuid}")
    fun deleteTask(@PathVariable("taskUuid") taskUuid: String, @PathVariable("projectUuid") projectUuid: String){
        projectService.deleteTaskFromProject(taskUuid, projectUuid)
    }

    @PostMapping("/change-priority/{projectUuid}")
    fun changePriority(@PathVariable("projectUuid") projectUuid: String){
        projectService.changePriority(projectUuid)
    }

}