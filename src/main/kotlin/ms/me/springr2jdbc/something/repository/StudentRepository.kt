package ms.me.springr2jdbc.something.repository

import ms.me.springr2jdbc.something.domain.Student
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface StudentRepository : R2dbcRepository<Student, Long>{
}
