package com.satellytes.wombat.integration

import com.satellytes.wombat.WombatProducer
import com.satellytes.wombat.model.WombatHuggedEvent
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.KafkaContainer
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@MicronautTest
class WombatProducerIT : TestPropertyProvider {

    private val WOMBAT_NAME = "any Name"

    @Inject
    private lateinit var producer: WombatProducer

    @Inject
    private lateinit var testListener: WombatProducerTestListener

    var kafka = KafkaContainer()

    init {
        kafka.start()
    }

    @Test
    fun testHugAWombat() {
        var event = WombatHuggedEvent(UUID.randomUUID(), WOMBAT_NAME, 1)
        producer.sendWombatHugged(event)
        val message = testListener.getMessage().poll(10, TimeUnit.SECONDS)
        assertThat(message).isNotNull
        assertThat(message!!.wombatName).isEqualTo(WOMBAT_NAME)
    }

    @AfterAll
    fun teardown() {
        kafka.stop()
    }

    override fun getProperties(): MutableMap<String, String> {
        return mutableMapOf("kafka.bootstrap.servers" to kafka.bootstrapServers)
    }
}