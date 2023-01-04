package com.ddd.onetwothree.service

import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.exception.FirebaseTokenDuplicateException
import com.ddd.onetwothree.exception.NotFoundResourceException
import com.ddd.onetwothree.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberDomainService(
    private val memberRepository: MemberRepository
) {

    fun findById(memberId: Long): Member {
        return memberRepository.findById(memberId)
            ?: throw NotFoundResourceException(Member::class)
    }

    fun findByFirebaseToken(firebaseToken: String): Member {
        return memberRepository.findByFirebaseToken(firebaseToken)
            ?: throw NotFoundResourceException(Member::class)
    }

    fun duplicateCheckFirebaseToken(firebaseToken: String) {
        if (memberRepository.findByFirebaseToken(firebaseToken) != null) {
            throw FirebaseTokenDuplicateException()
        }
    }

}