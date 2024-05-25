package org.mirea.kursachapi.store.repositories

import org.mirea.kursachapi.store.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, String> {

    fun findByEmail(email: String): UserEntity?

    fun findByUuid(uuid: String): UserEntity?
}