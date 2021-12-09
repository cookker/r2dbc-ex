package ms.me.springr2jdbc.something.config

import mu.KotlinLogging
import org.h2.tools.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger { }

@Component
@Profile("h2")
class H2ServerConfig (
    @Value("\${h2-server.h2-console-port:8081}") private val port: Int
){
    lateinit var h2Server: Server

    @EventListener(ContextRefreshedEvent::class)
    fun start() {
        log.info { "starting h2 console at port: $port" }
        h2Server = Server.createWebServer("-webPort", port.toString(), "-tcpAllowOthers").start()
    }

    @EventListener(ContextClosedEvent::class)
    fun stop() {
        log.info { "stopping h2 console at port: $port" }
        h2Server.stop()
    }


}