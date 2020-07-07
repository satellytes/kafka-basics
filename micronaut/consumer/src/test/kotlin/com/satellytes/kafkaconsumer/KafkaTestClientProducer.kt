package com.satellytes.kafkaconsumer

import com.satellytes.kafkaconsumer.model.WombatHuggedEvent
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic
import javax.inject.Singleton

@Singleton
@KafkaClient
interface KafkaTestClientProducer {
    @Topic("wombat-hug-queue")
    fun sendTestEvent(event: WombatHuggedEvent)
}