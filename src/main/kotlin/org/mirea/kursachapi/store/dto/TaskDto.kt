package org.mirea.kursachapi.store.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.*
import lombok.experimental.FieldDefaults
import java.util.Date

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
data class TaskDto(
    @JsonProperty("uuid")
    val uuid: String?,
    @JsonProperty("taskName")
    val taskName: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("status")
    val status: String?,
    @JsonProperty("priority")
    val priority: Boolean?,
    @JsonProperty("deadline")
    val deadline: String?,
    @JsonProperty("projectUuid")
    val projectUuid: String?
)