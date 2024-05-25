package org.mirea.kursachapi.store.entities

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.annotations.UuidGenerator


@Setter
@Getter
@Entity
@Table(name = "project")
data class ProjectEntity(
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var uuid: String? = null,
    @Column
    var projectName: String?,
    @Column
    var description: String?,
    @Column
    var status: String?,
    @Column
    var priority: Boolean?,
    @Column
    var userUuid : String?,
    @OneToMany(mappedBy = "projectUuid", cascade = [CascadeType.ALL])
    var taskList: MutableList<TaskEntity>?
    )
