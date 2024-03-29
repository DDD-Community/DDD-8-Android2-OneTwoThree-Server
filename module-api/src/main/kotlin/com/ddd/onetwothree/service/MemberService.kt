package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.RegisterRequest
import com.ddd.onetwothree.controller.response.MemberResponse
import com.ddd.onetwothree.controller.response.RegisterResponse
import com.ddd.onetwothree.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberDomainService: MemberDomainService,
    private val memberRepository: MemberRepository
) {

    fun register(req: RegisterRequest): RegisterResponse {
        memberDomainService.duplicateCheckFirebaseToken(req.firebaseToken)
        return memberRepository.save(req.toMember())
            .let { RegisterResponse.of(it) }
    }

    fun find(firebaseToken: String): MemberResponse {
        return memberDomainService.findByFirebaseToken(firebaseToken).let {
            MemberResponse.of(it)
        }
    }

    fun changeNickname(memberId: Long, nickname: String) {
        memberDomainService.findById(memberId).let {
            memberRepository.save(it.changeNickname(nickname))
        }
    }

    fun delete(memberId: Long) {
        memberRepository.deleteById(memberId)
    }

}