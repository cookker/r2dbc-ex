package ms.me.springr2jdbc.something.domain

import kotlin.properties.Delegates
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var name: String by Delegates.observable("") { _, old, new ->

    }
}