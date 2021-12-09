package ms.me.springr2jdbc.something.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Student(
    @Id
    val id: Long? = null,
    val name: String,
)


enum class Grade {
    Junior,
    Senior
}