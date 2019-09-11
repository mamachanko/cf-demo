package io.github.mamachanko.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

@Entity
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val name: String
)

@Repository
@RestResource(path = "people", rel = "people")
interface PersonRepository : CrudRepository<Person, Long>

@Configuration
class Config(private val personRepository: PersonRepository) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun createPeople(): CommandLineRunner = CommandLineRunner {
        listOf("Himalee", "Sam", "Max").forEach {
            val person = Person(0, it)
            logger.info("Creating {}", person)
            personRepository.save(person)
        }
        logger.info("There are ${personRepository.findAll()}")
    }
}
