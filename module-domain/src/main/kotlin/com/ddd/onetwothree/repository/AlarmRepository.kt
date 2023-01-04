package com.ddd.onetwothree.repository

import com.ddd.onetwothree.entity.Alarm
import com.ddd.onetwothree.entity.Member
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.repository.Repository

interface AlarmRepository : Repository<Alarm, Long> {
    fun findAllByMember(member: Member): List<Alarm>
    @Modifying
    fun save(alarm: Alarm): Alarm
    @Modifying
    fun deleteById(alarmId: Long)

}