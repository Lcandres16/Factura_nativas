package com.UV_Shield.Factura_nativas.controller



import com.UV_Shield.Factura_nativas.dto.LoginDto
import com.UV_Shield.Factura_nativas.dto.TokenDto
import com.UV_Shield.Factura_nativas.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    @Autowired
    private val jwtUtil: JwtUtil? = null

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<*> {
        try {
            val login = UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
            val authentication: Authentication = authenticationManager!!.authenticate(login)
            val response = TokenDto().apply { jwt = jwtUtil!!.create(loginDto.username) }
            return ResponseEntity(response, HttpStatus.OK)
        } catch (ex: BadCredentialsException) {
            // Credenciales incorrectas
            return ResponseEntity("Invalid username or password", HttpStatus.UNAUTHORIZED)
        } catch (ex: LockedException) {
            // Cuenta bloqueada
            return ResponseEntity("Account locked", HttpStatus.UNAUTHORIZED)
        } catch (ex: DisabledException) {
            // Cuenta deshabilitada
            return ResponseEntity("Account disabled", HttpStatus.UNAUTHORIZED)
        } catch (ex: Exception) {
            // Otro error durante la autenticación
            return ResponseEntity("Authentication error", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}