package com.satellytes.kafkaconsumer

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.satellytes.kafkaconsumer")
		.start()
}
