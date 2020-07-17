package com.satellytes.kafkaproducer

import com.satellytes.kafkaproducer.model.WombatHuggedEvent
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface WombatProducer {

    @Topic("wombat-hug-queue")
    fun sendWombatHugged(event: WombatHuggedEvent)

}