package com.ddd.onetwothree.controller

import com.ddd.onetwothree.annotation.RequiredUserInfo
import com.ddd.onetwothree.controller.request.StretchingAuthRequest
import com.ddd.onetwothree.controller.response.ResponseEntity
import com.ddd.onetwothree.controller.response.StretchingAuthCountResponse
import com.ddd.onetwothree.controller.response.StretchingAuthResponse
import com.ddd.onetwothree.service.StretchingAuthService
import com.ddd.onetwothree.support.UserContextHolder
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/{year}/{month}")
    @RequiredUserInfo
    fun retrieveDuring(@PathVariable month: Int, @PathVariable year: Int): ResponseEntity<Map<String, Int>> {
        val memberId = UserContextHolder.getContext().memberId
        return stretchingAuthService.retrieveDuring(memberId, year, month)
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{year}/{month}/{day}")
    @RequiredUserInfo
    fun retrieveDetail(
        @PathVariable year: Int,
        @PathVariable month: Int,
        @PathVariable day: Int
    ): ResponseEntity<StretchingAuthResponse> {
        val memberId = UserContextHolder.getContext().memberId
        return stretchingAuthService.retrieveDetail(memberId, year, month, day)
            .let { ResponseEntity.ok(it) }
    }

}
