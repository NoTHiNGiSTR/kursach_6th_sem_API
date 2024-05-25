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
data class ProjectDto (
    @JsonProperty("uuid")
    var uuid: String?,
    @JsonProperty("projectName")
    var projectName: String?,
    @JsonProperty("description")
    var description: String?,
    @JsonProperty("status")
    var status: String?,
    @JsonProperty("priority")
    var priority: Boolean?,
    @JsonProperty("taskList")
    var taskList: MutableList<TaskDto>?,
    @JsonProperty("userUuid")
    var userUuid: String?
)