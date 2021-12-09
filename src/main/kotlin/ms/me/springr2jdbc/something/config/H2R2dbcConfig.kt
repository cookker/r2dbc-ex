package ms.me.springr2jdbc.something.config

import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import io.r2dbc.h2.H2ConnectionOption
import io.r2dbc.spi.ConnectionFactory
import ms.me.springr2jdbc.something.repository.StudentRepository
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

private val log = KotlinLogging.logger { }

@Profile("h2")
@Configuration
@EnableR2dbcRepositories
class H2R2dbcConfig : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory =
        H2ConnectionFactory(
            H2ConnectionConfiguration.builder()
            .inMemory("testdb")
            .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1") // db연결이 닫혀도 유지되도록 함.
            .property("mode", "MySQL")
            .property("DATABASE_TO_LOWER", "TRUE")
            .username("sa")
            .build()
        )

    @Bean
    fun h2DbInitializer(): ConnectionFactoryInitializer {
        val resourceDatabasePopulator =
            ResourceDatabasePopulator().apply {
                addScript(ClassPathResource("h2.sql"))
            }

        val initializer =
            ConnectionFactoryInitializer().apply {
                setConnectionFactory(connectionFactory())
                setDatabasePopulator(resourceDatabasePopulator)
            }

        return initializer
    }
}