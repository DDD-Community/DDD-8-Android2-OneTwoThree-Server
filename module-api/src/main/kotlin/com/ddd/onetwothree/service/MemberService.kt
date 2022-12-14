package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.RegisterRequest
import com.ddd.onetwothree.controller.response.RegisterResponse
import com.ddd.onetwothree.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun register(req: RegisterRequest): RegisterResponse {
        // TODO : FirebaseToken 중복 안되도록 예외처리
        return req.toMember().let {
            memberRepository.save(it)
        }.let {
            RegisterResponse.of(it)
        }
    }

}