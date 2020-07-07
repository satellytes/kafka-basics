package com.satellytes.kafkaconsumer

import org.slf4j.LoggerFactory
import javax.inject.Singleton

interface IFoodService {
    fun giveFood(name : String)
}

@Singleton
class FoodService : IFoodService {
    private val logger = LoggerFactory.getLogger(FoodService::class.java)

    override fun giveFood(name : String)
    {
        logger.info("Wombat $name just got some grass to eat and have a break now")
    }
}