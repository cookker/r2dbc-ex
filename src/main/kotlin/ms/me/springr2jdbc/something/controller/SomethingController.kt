package ms.me.springr2jdbc.something.controller

import ms.me.springr2jdbc.something.domain.Student
import ms.me.springr2jdbc.something.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@RestController
class SomethingController(
    val studentService: StudentService
) {

    @GetMapping("/greeting")
    fun greeting(@CookieValue(value = "username", defaultValue = "anyone") username: String) =
        "say hello $username"

    @GetMapping("/hello")
    fun hello(response: ServerHttpResponse){
        val cookie = ResponseCookie.from("username", "337")
            .httpOnly(true)
            .secure(true)
            .build()
        response.addCookie(cookie)

        response.setComplete()
    }

    @GetMapping("/hell")
    fun hell(exchange: ServerWebExchange) =
        exchange.response
            .addCookie(
                ResponseCookie.from("pay", "kko")
                    .httpOnly(true)
                    .secure(true)
                    .build()
            )

    @GetMapping("/student")
    fun getStudent(): Mono<ResponseEntity<Student>> =
        studentService.getStudent()
            .map{
                ResponseEntity.ok(it)
            }
}
