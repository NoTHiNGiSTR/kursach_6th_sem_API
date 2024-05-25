package org.mirea.kursachapi.store.repositories

import org.mirea.kursachapi.store.entities.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface ProjectRepository: JpaRepository<ProjectEntity, String> {

    fun deleteByUuid(@Param("uuid") uuid : String)
    fun findByUuid(@Param("uuid") uuid: String) : ProjectEntity
    fun findAllByUserUuid(@Param("userUuid") userUuid: String): List<ProjectEntity>
}