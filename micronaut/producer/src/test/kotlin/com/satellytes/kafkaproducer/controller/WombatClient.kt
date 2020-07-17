package com.satellytes.kafkaproducer.controller

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import javax.inject.Singleton
import javax.validation.constraints.NotBlank

@Singleton
@Client("/wombat")
interface WombatClient {

    @Post("/hug/{name}")
    fun giveAHug(@NotBlank name : String) : HttpStatus
}