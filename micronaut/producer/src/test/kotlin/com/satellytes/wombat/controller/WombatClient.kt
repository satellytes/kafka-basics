package com.satellytes.wombat.controller

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import javax.inject.Singleton
import javax.validation.constraints.NotBlank

@Singleton
@Client("/wombat")
interface WombatClient {

    @Post("/hug/{name}")
    fun giveAHug(@NotBlank name : String) : HttpStatus

    @Post("/pad/{name}")
    fun giveAPad(@NotBlank name : String) : HttpStatus
}