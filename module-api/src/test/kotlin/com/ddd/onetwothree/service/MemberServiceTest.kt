package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.RegisterRequest
import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.repository.MemberRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalTime

internal class MemberServiceTest {

    private val memberRepository: MemberRepository = mock()
    private val memberService = MemberService(memberRepository)

    @Test
    fun `register - 정상동작 확인`() {
        // given
        val req = RegisterRequest(
            nickname = "ch4njun",
            firebaseToken = "sample_firebase_token"
        )
        whenever(memberRepository.save(any<Member>())).thenReturn(req.toMember().apply { this.id = 1L })

        // when & then
        memberService.register(req).let {
            it.shouldNotBeNull()
            it.memberId shouldBe 1L
        }
    }

}