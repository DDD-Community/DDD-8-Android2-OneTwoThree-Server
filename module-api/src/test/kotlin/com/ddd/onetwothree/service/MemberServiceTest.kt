package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.RegisterRequest
import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.exception.FirebaseTokenDuplicateException
import com.ddd.onetwothree.exception.NotFoundResourceException
import com.ddd.onetwothree.repository.MemberRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MemberServiceTest {

    private val memberDomainService: MemberDomainService = mock()
    private val memberRepository: MemberRepository = mock()
    private val memberService = MemberService(memberDomainService, memberRepository)

    @Test
    fun `register - 정상동작 확인`() {
        // given
        val req = RegisterRequest(nickname = "ch4njun", firebaseToken = "sample_firebase_token")
        whenever(memberRepository.save(any<Member>())).thenReturn(req.toMember().apply { this.id = 1L })

        // when & then
        memberService.register(req).let {
            it.shouldNotBeNull()
            it.memberId shouldBe 1L
        }
    }

    @Test
    fun `register - 중복시 에러 확인`() {
        // given
        val req = RegisterRequest(nickname = "ch4njun", firebaseToken = "sample_firebase_token")
        whenever(memberDomainService.duplicateCheckFirebaseToken(any())).thenThrow(FirebaseTokenDuplicateException())

        // when & then
        shouldThrow<FirebaseTokenDuplicateException> {
            memberService.register(req)
        }
    }

    @Test
    fun `find - 정상동작 확인`() {
        // given
        val firebaseToken = "sample_firebase_token"
        val member = Member(id = 1L, firebaseToken = firebaseToken, nickname = "ch4njun")
        whenever(memberRepository.findByFirebaseToken(firebaseToken)).thenReturn(member)

        // when & then
        memberService.find(firebaseToken).let {
            it.shouldNotBeNull()
            it.memberId shouldBe member.id
            it.nickname shouldBe member.nickname
        }
    }

    @Test
    fun `find - 없는 사용자일 경우 에러 확인`() {
        // given
        val firebaseToken = "unknown_token"
        whenever(memberRepository.findByFirebaseToken(firebaseToken)).thenReturn(null)

        // when & then
        shouldThrow<NotFoundResourceException> {
            memberService.find(firebaseToken)
        }
    }

}