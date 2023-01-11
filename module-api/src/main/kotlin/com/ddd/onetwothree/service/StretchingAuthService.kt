package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.StretchingAuthRequest
import com.ddd.onetwothree.controller.response.StretchingAuthCountResponse
import com.ddd.onetwothree.controller.response.StretchingAuthResponse
import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.entity.StretchingAuth
import com.ddd.onetwothree.repository.StretchingAuthRepository
import com.ddd.onetwothree.type.StretchingType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class StretchingAuthService(
    private val memberDomainService: MemberDomainService,
    private val stretchingAuthRepository: StretchingAuthRepository
) {

    @Transactional
    fun auth(memberId: Long, req: StretchingAuthRequest): StretchingAuthCountResponse {
        val member = memberDomainService.findById(memberId)
        this.createStretchingAuth(member, req.stretchingType)

        return with(LocalDateTime.now()) {
            stretchingAuthRepository.findAllByMemberAndYearAndMonthAndDay(
                member = member,
                year = this.year,
                month = this.monthValue,
                day = this.dayOfMonth
            )
        }.let {
            StretchingAuthCountResponse(stretchingAuthCount = it.size)
        }
    }

    fun createStretchingAuth(member: Member, type: StretchingType): StretchingAuth {
        return stretchingAuthRepository.save(StretchingAuth.of(member, type))
    }

    fun retrieveDuring(memberId: Long, year: Int, month: Int): Map<String, Int> {
        val member = memberDomainService.findById(memberId)
        return stretchingAuthRepository.findAllByMemberAndYearAndMonth(member, year, month)
            .groupBy { "${it.year}-${it.month}-${it.day}" }
            .mapValues { it.value.size }
    }

    fun retrieveDetail(memberId: Long, year: Int, month: Int, day: Int): StretchingAuthResponse {
        val member = memberDomainService.findById(memberId)
        return stretchingAuthRepository.findAllByMemberAndYearAndMonthAndDay(member, year, month, day)
            .let { StretchingAuthResponse.of(it) }
    }

}