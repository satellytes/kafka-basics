package com.satellytes.kafkaproducer.controller

import com.satellytes.kafkaproducer.WombatProducer
import io.micronaut.context.annotation.Primary
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import org.apache.kafka.clients.producer.KafkaProducer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject


@MicronautTest
class WombatInteractionControllerTest {

    @Inject
    lateinit var client: WombatClient

    @Test
    fun testHugAWombat() {
        val response = client.giveAHug("Mark")
        assertThat(response.code).isEqualTo(HttpStatus.OK.code)
    }

    @Primary
    @MockBean(KafkaProducer::class)
    fun mockKafkaProducer() : WombatProducer
    {
        return Mockito.mock(WombatProducer::class.java)
    }
}