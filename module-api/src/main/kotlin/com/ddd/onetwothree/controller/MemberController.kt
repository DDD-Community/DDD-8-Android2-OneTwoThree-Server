package com.ddd.onetwothree.controller

import com.ddd.onetwothree.annotation.RequiredUserInfo
import com.ddd.onetwothree.controller.request.ChangeNicknameRequest
import com.ddd.onetwothree.controller.request.RegisterRequest
import com.ddd.onetwothree.controller.response.MemberResponse
import com.ddd.onetwothree.controller.response.RegisterResponse
import com.ddd.onetwothree.service.MemberService
import com.ddd.onetwothree.controller.response.ResponseEntity
import com.ddd.onetwothree.support.UserContextHolder
import org.springframework.web.bind.annotation.*

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

    @GetMapping
    fun retrieveMember(@RequestParam firebaseToken: String): ResponseEntity<MemberResponse> {
        return memberService.find(firebaseToken)
            .let { ResponseEntity.ok(it) }
    }

    @PutMapping("/nickname")
    @RequiredUserInfo
    fun changeNickname(@RequestBody req: ChangeNicknameRequest): ResponseEntity<Unit> {
        val memberId = UserContextHolder.getContext().memberId
        return memberService.changeNickname(memberId, req.nickname)
            .let { ResponseEntity.ok() }
    }

}
