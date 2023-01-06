package com.ddd.onetwothree.controller

import com.ddd.onetwothree.annotation.RequiredUserInfo
import com.ddd.onetwothree.controller.response.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class StretchingAuthController {

    @PostMapping
    @RequiredUserInfo
    fun stretchingAuth(): ResponseEntity<Unit> {

        return ResponseEntity.ok()
    }

}