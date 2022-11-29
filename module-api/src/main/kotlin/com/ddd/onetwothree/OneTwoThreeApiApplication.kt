package com.ddd.onetwothree

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class OneTwoThreeApiApplication

fun main(args: Array<String>) {
    runApplication<OneTwoThreeApiApplication>(*args)
}