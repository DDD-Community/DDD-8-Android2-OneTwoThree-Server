package com.ddd.onetwothree.entity

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MemberTest {

    @Test
    fun `changeNickname - 정상동작 확인`() {
        // given
        val member = Member(id = 1L, firebaseToken = "sample_firebase_token", nickname = "before")

        // when & then
        member.changeNickname("after").let {
            it.id shouldBe member.id
            it.nickname shouldBe "after"
            it.firebaseToken shouldBe member.firebaseToken
        }
    }

}