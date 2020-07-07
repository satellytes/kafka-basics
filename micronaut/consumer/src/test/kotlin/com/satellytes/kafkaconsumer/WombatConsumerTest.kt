package com.satellytes.kafkaconsumer

import com.satellytes.kafkaconsumer.model.WombatHuggedEvent
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.PropertySource
import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.support.TestPropertyProvider
import org.junit.After
import org.junit.ClassRule
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WombatConsumerTest : TestPropertyProvider {

    private val WOMBAT_NAME = "Trudy"

    @Inject
    private lateinit var client : KafkaTestClientProducer

    @Inject
    private lateinit var foodService: IFoodService

    private val kafka = KafkaContainer()

    init{
        kafka.start()
    }

    @Test
    fun testListener()
    {
        println("Send Event...")
        client.sendTestEvent(WombatHuggedEvent(UUID.randomUUID(), WOMBAT_NAME, 1))
        val countDownLatch = CountDownLatch(10)
        countDownLatch.await(10L, TimeUnit.SECONDS)
        Mockito.verify(foodService).giveFood(WOMBAT_NAME)

    }

    @AfterAll
    fun teardown()
    {
        kafka.stop()
    }

    @MockBean(FoodService::class)
    fun foodService(): IFoodService {
        return mock(IFoodService::class.java)
    }

    override fun getProperties(): MutableMap<String, String> {
        return mutableMapOf("kafka.bootstrap.servers" to kafka.bootstrapServers)
    }


}