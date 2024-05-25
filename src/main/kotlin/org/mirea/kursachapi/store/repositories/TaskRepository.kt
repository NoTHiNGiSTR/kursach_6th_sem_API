package org.mirea.kursachapi.store.repositories

import org.mirea.kursachapi.store.entities.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface TaskRepository: JpaRepository<TaskEntity, String> {

    fun deleteByUuid(@Param("uuid") uuid : String)
    fun findByUuid(@Param("uuid") uuid : String) : TaskEntity
    fun findAllByProjectUuid(@Param("projectUuid") projectUuid : String) : List<TaskEntity>
}