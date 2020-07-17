package com.satellytes.kafkaproducer

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.satellytes")
		.start()
}

