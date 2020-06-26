package com.satellytes.wombat

import com.satellytes.wombat.model.WombatHuggedEvent
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic
import javax.inject.Singleton

@Singleton
@KafkaClient
interface WombatProducer {

    @Topic("wombat-hug-queue")
    fun sendWombatHugged(event: WombatHuggedEvent)
}