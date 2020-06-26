package com.satellytes.kafkaconsumer

import com.satellytes.kafkaconsumer.model.WombatHuggedEvent
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import org.slf4j.LoggerFactory

@KafkaListener
class WombatConsumer {
    private val logger = LoggerFactory.getLogger(WombatConsumer::class.java)

    @Topic("wombat-hug-queue")
    fun receiveHugs(event: WombatHuggedEvent)
    {
        logger.info("a absolute cute wombat named ${event.wombatName} just received a big hug at ${java.time.format.DateTimeFormatter.ISO_DATE_TIME.format(java.time.Instant.ofEpochSecond(event.timestamp))}")
        logger.info("do something with the information")
    }

}