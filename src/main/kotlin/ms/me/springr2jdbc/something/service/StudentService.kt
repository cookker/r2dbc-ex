package ms.me.springr2jdbc.something.service

import ms.me.springr2jdbc.something.domain.Student
import ms.me.springr2jdbc.something.repository.StudentRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

private val log = KotlinLogging.logger { }

@Service
class StudentService(
    val studentRepository: StudentRepository
) {
    fun getStudent(): Mono<Student> {
        return studentRepository.findById(1L)
    }
}
