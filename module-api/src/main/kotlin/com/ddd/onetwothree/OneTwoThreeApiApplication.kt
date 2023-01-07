package com.ddd.onetwothree

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableScheduling
@EnableAsync
class OneTwoThreeApiApplication

fun main(args: Array<String>) {
    runApplication<OneTwoThreeApiApplication>(*args)
}