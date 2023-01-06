package com.ddd.onetwothree.repository

import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.entity.StretchingAuth
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.repository.Repository

interface StretchingAuthRepository: Repository<StretchingAuth, Long> {

    @Modifying
    fun save(stretchingAuth: StretchingAuth): StretchingAuth

    fun findAllByMemberAndYearAndMonthAndDay(member: Member, year: Int, month: Int, day: Int): List<StretchingAuth>

}