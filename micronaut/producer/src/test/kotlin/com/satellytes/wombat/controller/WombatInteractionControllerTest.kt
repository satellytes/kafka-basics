package com.satellytes.wombat.controller

import com.satellytes.wombat.controller.WombatClient
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MicronautTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import javax.inject.Inject


@MicronautTest
class WombatInteractionControllerTest {

     @Inject
    lateinit var client: WombatClient

    @Test
    fun testHugAWombat() {
        val response = client.giveAHug("Mark")
        assertThat(response.code).isEqualTo(HttpStatus.OK.code)

    }

    @Test
    fun testPadAWombat() {
        val response = client.giveAPad("Schorsch")
        assertThat(response.code).isEqualTo(HttpStatus.OK.code)

    }


}