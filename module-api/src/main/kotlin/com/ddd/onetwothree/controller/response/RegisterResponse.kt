package com.ddd.onetwothree.controller.response

import com.ddd.onetwothree.entity.Member

data class RegisterResponse(
    val memberId: Long
) {
    companion object {
        fun of(member: Member): RegisterResponse {
            return RegisterResponse(member.id!!)
        }
    }
}
