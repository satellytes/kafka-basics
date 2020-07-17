package com.satellytes.kafkaproducer

import com.satellytes.kafkaproducer.model.WombatHuggedEvent
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import java.util.*

@Controller("/wombat")
class WombatInteractionController(var wombatKafkaProducer: WombatProducer) {
    @Post("hug/{wombatName}")
    fun hugAWombat(@PathVariable wombatName: String): HttpStatus {
        println("give a big hug to a wombat named: $wombatName")
        wombatKafkaProducer.sendWombatHugged(WombatHuggedEvent(UUID.randomUUID(), wombatName, System.currentTimeMillis() / 1000L))
        return HttpStatus.OK
    }
}