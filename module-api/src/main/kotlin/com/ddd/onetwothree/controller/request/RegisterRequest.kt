package com.ddd.onetwothree.controller.request

import com.ddd.onetwothree.entity.Member

data class RegisterRequest(
    val nickname: String,
    val firebaseToken: String
) {
    fun toMember(): Member {
        return Member(
            nickname = this.nickname,
            firebaseToken = this.firebaseToken
        )
    }
}