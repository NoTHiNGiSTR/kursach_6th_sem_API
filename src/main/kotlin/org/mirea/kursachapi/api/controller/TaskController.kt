package org.mirea.kursachapi.api.controller

import org.mirea.kursachapi.api.services.TaskService
import org.mirea.kursachapi.store.dto.ProjectDto
import org.mirea.kursachapi.store.dto.TaskDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService) {

    @PostMapping("/create")
    fun createTask(@RequestBody taskDto: TaskDto) : TaskDto{
        return taskService.createTask(taskDto)
    }

    @PostMapping("/edit")
    fun editTask(@RequestBody taskDto: TaskDto): TaskDto?{
        return taskService.editTask(taskDto)
    }

    @PostMapping("/change-priority/{taskUuid}")
    fun changePriority(@PathVariable("taskUuid") taskUuid: String){
        taskService.changePriority(taskUuid)
    }


}