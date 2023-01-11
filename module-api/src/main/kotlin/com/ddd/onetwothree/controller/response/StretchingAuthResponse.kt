package com.ddd.onetwothree.controller.response

import com.ddd.onetwothree.entity.StretchingAuth
import com.ddd.onetwothree.type.StretchingType
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime

data class StretchingAuthResponse(
    val authCount: Int,
    val auths: List<StretchingAuthDetailResponse>
) {
    companion object {
        fun of(auths: List<StretchingAuth>): StretchingAuthResponse {
            return StretchingAuthResponse(
                authCount = auths.size,
                auths = auths.map { StretchingAuthDetailResponse(it.authTime, it.type) }
            )
        }
    }
}

data class StretchingAuthDetailResponse(
    @JsonFormat(pattern = "HH:mm:ss")
    val time: LocalTime,
    val stretchingType: StretchingType
)