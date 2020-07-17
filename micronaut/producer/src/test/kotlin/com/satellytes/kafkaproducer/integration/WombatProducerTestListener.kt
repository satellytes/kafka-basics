package com.satellytes.kafkaproducer.integration

import com.satellytes.kafkaproducer.model.WombatHuggedEvent
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque
import javax.inject.Singleton

@KafkaListener
class WombatProducerTestListener {

    private var messageQueue = LinkedBlockingDeque<WombatHuggedEvent>()

    @Topic("wombat-hug-queue")
    fun receiveMessage(event : WombatHuggedEvent)
    {
        messageQueue.add(event)

    }

    fun getMessage() : BlockingQueue<WombatHuggedEvent> {
        return messageQueue
    }

}