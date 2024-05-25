package org.mirea.kursachapi.store.dto

import lombok.*
import lombok.experimental.FieldDefaults


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
data class UserDto(
    val uuid: String = "",
    val name: String,
    val email: String,
    val password: String
)