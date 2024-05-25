package org.mirea.kursachapi.api.controller

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.mirea.kursachapi.api.services.UserService
import org.mirea.kursachapi.store.dto.LoginDto
import org.mirea.kursachapi.store.dto.Message
import org.mirea.kursachapi.store.dto.ProjectDto
import org.mirea.kursachapi.store.dto.UserDto
import org.mirea.kursachapi.store.entities.UserEntity
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api")
class AuthController(private val userService: UserService){

    @PostMapping("/register")
    fun register(@RequestBody body: UserDto): ResponseEntity<UserEntity> {
        val user = UserEntity(
            uuid = "",
            username = body.name,
            email = body.email,
            password = BCryptPasswordEncoder().encode(body.password),
            projectList = mutableListOf()
        )
        return ResponseEntity.ok(this.userService.save(user))
    }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDto, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.findByEmail(body.email)
        if (user != null){
            if (!BCryptPasswordEncoder().matches(body.password, user.password))  {
                return ResponseEntity.badRequest().body(Message("invalid login or password!"))
            }
        }
        else return ResponseEntity.badRequest().body(Message("invalid login or password!"))

        return ResponseEntity.ok(UserDto(uuid = user.uuid, name = user.username, password = user.password, email = user.email ))
    }

    @GetMapping("/check-email/{email}")
    fun checkEmail(@PathVariable("email") email : String) : Message{
        if(userService.findByEmail(email) != null){
            return Message("true")
        }
        else return Message("false")
    }
}