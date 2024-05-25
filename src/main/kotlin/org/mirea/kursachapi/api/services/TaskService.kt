package org.mirea.kursachapi.api.services

import org.mirea.kursachapi.store.dto.ProjectDto
import org.mirea.kursachapi.store.dto.TaskDto
import org.mirea.kursachapi.store.entities.TaskEntity
import org.mirea.kursachapi.store.factories.TaskDtoFactory
import org.mirea.kursachapi.store.repositories.ProjectRepository
import org.mirea.kursachapi.store.repositories.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository, private val projectRepository: ProjectRepository, private val taskDtoFactory: TaskDtoFactory) {
    fun createTask(taskDto: TaskDto) : TaskDto{
        System.out.println(taskDto)
        val project = projectRepository.findByUuid(taskDto.projectUuid!!)
        val task = taskRepository.save(
            TaskEntity(
                taskName = taskDto.taskName,
                status = taskDto.status,
                description = taskDto.description,
                priority = taskDto.priority,
                deadline = taskDto.deadline,
                projectUuid = project.uuid!!
            )
        )
        project.taskList!!.add(task)
        return taskDtoFactory.makeTaskDto(task)
    }

    fun editTask(taskDto: TaskDto) : TaskDto?{
        System.out.println(taskDto)
        val edited = taskRepository.findByUuid(taskDto.uuid!!)
        edited.taskName = taskDto.taskName
        edited.description = taskDto.description
        edited.status = taskDto.status
        edited.priority = taskDto.priority
        edited.deadline = taskDto.deadline
        taskRepository.save(edited)
        return taskDtoFactory.makeTaskDto(edited)
    }

    fun changePriority(taskUuid : String){
        val task = taskRepository.findByUuid(taskUuid)
        if (task.priority == false){
            task.priority = true
        }
        else task.priority = false
        taskRepository.save(task)
    }

}