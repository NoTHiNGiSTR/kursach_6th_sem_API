package org.mirea.kursachapi.api.services

import org.mirea.kursachapi.store.entities.UserEntity
import org.mirea.kursachapi.store.repositories.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository){
    fun save(user: UserEntity): UserEntity {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): UserEntity? {
        return this.userRepository.findByEmail(email)
    }

    fun checkEmail(email: String): Boolean {
        return this.userRepository.findByEmail(email) != null
    }

    fun getByUuid(uuid: String): UserEntity? {
        return this.userRepository.findByUuid(uuid)
    }

}