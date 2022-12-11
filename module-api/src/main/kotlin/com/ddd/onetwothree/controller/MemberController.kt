package com.ddd.onetwothree.controller

import com.ddd.onetwothree.controller.request.RegisterRequest
import com.ddd.onetwothree.controller.response.RegisterResponse
import com.ddd.onetwothree.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping
    fun registerMember(@RequestBody req: RegisterRequest): ResponseEntity<RegisterResponse> {
        return memberService.register(req)
            .let { ResponseEntity.ok(it) }
    }

}
