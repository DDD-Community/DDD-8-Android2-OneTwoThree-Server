package com.ddd.onetwothree.controller

import com.ddd.onetwothree.annotation.RequiredUserInfo
import com.ddd.onetwothree.controller.request.StretchingAuthRequest
import com.ddd.onetwothree.controller.response.ResponseEntity
import com.ddd.onetwothree.controller.response.StretchingAuthCountResponse
import com.ddd.onetwothree.service.StretchingAuthService
import com.ddd.onetwothree.support.UserContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class StretchingAuthController(
    private val stretchingAuthService: StretchingAuthService
) {

    @PostMapping
    @RequiredUserInfo
    fun stretchingAuth(@RequestBody req: StretchingAuthRequest): ResponseEntity<StretchingAuthCountResponse> {
        val memberId = UserContextHolder.getContext().memberId
        return stretchingAuthService.auth(memberId, req)
            .let { ResponseEntity.ok(it) }
    }

}