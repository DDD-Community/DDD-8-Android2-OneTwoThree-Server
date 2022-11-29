package com.ddd.onetwothree.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }

}