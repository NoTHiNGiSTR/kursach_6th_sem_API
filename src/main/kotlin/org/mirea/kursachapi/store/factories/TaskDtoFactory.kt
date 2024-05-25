package org.mirea.kursachapi.store.factories

import org.mirea.kursachapi.store.dto.TaskDto
import org.mirea.kursachapi.store.entities.TaskEntity
import org.springframework.stereotype.Component


@Component
class TaskDtoFactory {

    fun makeTaskDto(entity: TaskEntity) : TaskDto{
        return TaskDto(
            uuid = entity.uuid,
            taskName = entity.taskName,
            description = entity.description,
            status = entity.status,
            priority = entity.priority,
            deadline = entity.deadline,
            projectUuid = entity.projectUuid
        )
    }
}