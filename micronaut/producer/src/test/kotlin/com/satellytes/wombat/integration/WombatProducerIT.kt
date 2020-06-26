package com.satellytes.wombat.integration

import com.satellytes.wombat.WombatProducer
import com.satellytes.wombat.model.WombatHuggedEvent
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.PropertySource
import io.micronaut.runtime.server.EmbeddedServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
class WombatProducerIT {

    private val WOMBAT_NAME = "any Name"

    private lateinit var producer : WombatProducer

    private lateinit var testListener : WombatProducerTestListener

    private lateinit var server: EmbeddedServer

    var kafka: KafkaContainer = KafkaContainer()

    @BeforeAll
    fun init()
    {
        kafka.start()
        val kafkaBootstrap = kafka.bootstrapServers

        server = ApplicationContext.run(EmbeddedServer::class.java, PropertySource.of("test", mapOf(
                "kafka.bootstrap.servers" to kafkaBootstrap
        )))

        producer = server.applicationContext.getBean(WombatProducer::class.java)
        testListener = server.applicationContext.getBean(WombatProducerTestListener::class.java)
    }

    @Test
    fun testHugAWombat()
    {
        var event = WombatHuggedEvent(UUID.randomUUID(), WOMBAT_NAME, 1)
        producer.sendWombatHugged(event)

        val message = testListener.getMessage().poll(10, TimeUnit.SECONDS)
        assertThat(message).isNotNull
        assertThat(message!!.wombatName).isEqualTo(WOMBAT_NAME)
    }

    @AfterAll
    fun shutdown()
    {
        server.stop()
    }
}