package org.mirea.kursachapi.api.services

import jakarta.transaction.Transactional
import org.mirea.kursachapi.store.dto.ProjectDto
import org.mirea.kursachapi.store.dto.TaskDto
import org.mirea.kursachapi.store.entities.ProjectEntity
import org.mirea.kursachapi.store.entities.TaskEntity
import org.mirea.kursachapi.store.factories.ProjectDtoFactory
import org.mirea.kursachapi.store.factories.TaskDtoFactory
import org.mirea.kursachapi.store.repositories.ProjectRepository
import org.mirea.kursachapi.store.repositories.TaskRepository
import org.springframework.stereotype.Service


@Service
class ProjectService(private val projectRepository: ProjectRepository, private val taskRepository: TaskRepository) {

    private final val projectDtoFactory = ProjectDtoFactory()
    private final val taskDtoFactory = TaskDtoFactory()


    fun createProject(projectDto : ProjectDto) : ProjectDto{

        val project = projectRepository.saveAndFlush(
            ProjectEntity(
                projectName = projectDto.projectName,
                status = projectDto.status,
                priority = projectDto.priority,
                description = projectDto.description,
                taskList = mutableListOf(),
                userUuid = projectDto.userUuid
            )
        )

        if (projectDto.taskList != null){
            val tasks: MutableList<TaskEntity> = mutableListOf()
            projectDto.taskList!!.map {
                tasks.add(
                taskRepository.save(TaskEntity(
                        taskName = it.taskName,
                        status = it.status,
                        description = it.description,
                        priority = it.priority,
                        deadline = it.deadline,
                        projectUuid = project.uuid!!
                )
                )
                )
            }
            project.taskList = tasks
        }
        return projectDtoFactory.makeProjectDto(project)
    }


    fun editProject(projectDto: ProjectDto) : ProjectDto?{
        val edited = projectRepository.findByUuid(projectDto.uuid!!)
        edited.projectName = projectDto.projectName
        edited.description = projectDto.description
        edited.status = projectDto.status
        edited.priority = projectDto.priority
        projectRepository.save(edited)
        return projectDtoFactory.makeProjectDto(edited)
    }

    @Transactional
    fun deleteProject(projectUuid: String){
        projectRepository.deleteByUuid(projectUuid)
    }

    @Transactional
    fun deleteTaskFromProject(taskUuid: String, projectUuid: String){
        val project = projectRepository.findByUuid(projectUuid)
        project.taskList?.removeIf{it.uuid == taskUuid}
        taskRepository.deleteByUuid(taskUuid)
    }


    fun addTaskToProject(taskDto: TaskDto){
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
    }

    fun getProjectTasks(projectUuid: String): List<TaskDto>{
        val tasks = projectRepository.findByUuid(projectUuid).taskList.orEmpty()
        return tasks.stream().map { it->taskDtoFactory.makeTaskDto(it) }.toList()
    }

    fun getAllProjects() : List<ProjectEntity>{
        return projectRepository.findAll()
    }

    fun getProjectsByUserUuid(userUuid : String) : List<ProjectEntity>{
        return projectRepository.findAllByUserUuid(userUuid)
    }

    fun changePriority(projectUuid : String){
        val project = projectRepository.findByUuid(projectUuid)
        if (project.priority == false){
            project.priority = true
        }
        else project.priority = false
        projectRepository.save(project)
    }
}