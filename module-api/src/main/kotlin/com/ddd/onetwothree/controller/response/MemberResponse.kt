package com.ddd.onetwothree.controller.response

import com.ddd.onetwothree.entity.Member

data class MemberResponse(
    val memberId: Long,
    val nickname: String
) {
    companion object {
        fun of(member: Member): MemberResponse {
            return MemberResponse(memberId = member.id!!, nickname = member.nickname)
        }
    }
}