package com.ddd.onetwothree.service

import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.repository.MemberRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class MemberDomainServiceTest {

    private val memberRepository: MemberRepository = mock()
    private val memberDomainService = MemberDomainService(memberRepository)

    @Test
    fun `duplicateCheckFirebaseToken - 중복체크 확인`() {
        // given
        val firebaseToken = "sample_firebase_token"
        val member = Member(id = 1L, firebaseToken = firebaseToken, nickname = "ch4njun")
        whenever(memberRepository.findByFirebaseToken(firebaseToken)).thenReturn(member)

        // when & then
        shouldThrow<RuntimeException> {
            memberDomainService.duplicateCheckFirebaseToken(firebaseToken)
        }
    }

    @Test
    fun `duplicateCheckFirebaseToken - 중복아닌 경우 확인`() {
        // given
        val firebaseToken = "sample_firebase_token"
        whenever(memberRepository.findByFirebaseToken(firebaseToken)).thenReturn(null)

        // when & then
        shouldNotThrow<RuntimeException> {
            memberDomainService.duplicateCheckFirebaseToken(firebaseToken)
        }
    }

}