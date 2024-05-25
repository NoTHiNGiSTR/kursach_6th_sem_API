package org.mirea.kursachapi.store.entities

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.Date


@Entity
@Table(name ="task")
data class TaskEntity(
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var uuid: String? = null,
    var taskName: String?,
    var description: String?,
    var status: String?,
    var priority: Boolean?,
    var deadline: String?,
    val projectUuid: String
)