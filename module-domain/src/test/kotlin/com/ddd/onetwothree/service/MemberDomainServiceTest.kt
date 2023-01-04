package com.ddd.onetwothree.service

import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.exception.FirebaseTokenDuplicateException
import com.ddd.onetwothree.exception.NotFoundResourceException
import com.ddd.onetwothree.repository.MemberRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class MemberDomainServiceTest {

    private val memberRepository: MemberRepository = mock()
    private val memberDomainService = MemberDomainService(memberRepository)

    @Test
    fun `find - 정상동작 확인`() {
        // given
        val member = Member(id = 1L, firebaseToken = "sample_firebase_token", nickname = "ch4njun")
        whenever(memberRepository.findById(any())).thenReturn(member)

        // when & then
        shouldNotThrow<NotFoundResourceException> {
            memberDomainService.find(1L)
        }
    }

    @Test
    fun `find - 없을때 에러 확인`() {
        // given
        whenever(memberRepository.findById(any())).thenReturn(null)

        // when & then
        shouldThrow<NotFoundResourceException> {
            memberDomainService.find(1L)
        }
    }

    @Test
    fun `duplicateCheckFirebaseToken - 중복체크 확인`() {
        // given
        val firebaseToken = "sample_firebase_token"
        val member = Member(id = 1L, firebaseToken = firebaseToken, nickname = "ch4njun")
        whenever(memberRepository.findByFirebaseToken(firebaseToken)).thenReturn(member)

        // when & then
        shouldThrow<FirebaseTokenDuplicateException> {
            memberDomainService.duplicateCheckFirebaseToken(firebaseToken)
        }
    }

    @Test
    fun `duplicateCheckFirebaseToken - 중복아닌 경우 확인`() {
        // given
        val firebaseToken = "sample_firebase_token"
        whenever(memberRepository.findByFirebaseToken(firebaseToken)).thenReturn(null)

        // when & then
        shouldNotThrow<FirebaseTokenDuplicateException> {
            memberDomainService.duplicateCheckFirebaseToken(firebaseToken)
        }
    }

}