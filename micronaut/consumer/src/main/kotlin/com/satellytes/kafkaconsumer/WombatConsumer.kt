package com.satellytes.kafkaconsumer

import com.satellytes.kafkaconsumer.model.WombatHuggedEvent
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@KafkaListener
@Singleton
class WombatConsumer(private val foodService: IFoodService) {
    private val logger = LoggerFactory.getLogger(WombatConsumer::class.java)

    @Topic("wombat-hug-queue")
    fun receiveHugs(event: WombatHuggedEvent)
    {
        logger.info("a absolute cute wombat named ${event.wombatName} just received a big hug")
        foodService.giveFood(event.wombatName)
    }

}