package com.ddd.onetwothree.service

import com.ddd.onetwothree.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberDomainService(
    private val memberRepository: MemberRepository
) {

    fun duplicateCheckFirebaseToken(firebaseToken: String) {
        if (memberRepository.findByFirebaseToken(firebaseToken) != null) {
            throw RuntimeException()
        }
    }

}