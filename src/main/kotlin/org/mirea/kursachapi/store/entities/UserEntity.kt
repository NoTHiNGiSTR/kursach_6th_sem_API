package org.mirea.kursachapi.store.entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.annotations.UuidGenerator
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserTable")
data class UserEntity(
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    val uuid: String,
    @Column
    val username: String,
    @Column
    val password: String,
    @Column
    val email: String,
    @OneToMany(mappedBy = "userUuid")
    val projectList: MutableList<ProjectEntity>?
) {

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }
}



