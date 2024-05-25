package org.mirea.kursachapi.store.factories

import org.mirea.kursachapi.store.dto.ProjectDto
import org.mirea.kursachapi.store.entities.ProjectEntity
import org.springframework.stereotype.Component


@Component
class ProjectDtoFactory {

    private final val taskDtoFactory = TaskDtoFactory()
    fun makeProjectDto(entity: ProjectEntity): ProjectDto{
        return ProjectDto(
            uuid = entity.uuid,
            projectName = entity.projectName,
            description = entity.description,
            status = entity.status,
            priority = entity.priority,
            taskList = entity.taskList!!.stream().map { taskDtoFactory.makeTaskDto(it) }.toList(),
            userUuid = entity.userUuid
        )
    }
}