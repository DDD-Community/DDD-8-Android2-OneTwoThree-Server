package com.ddd.onetwothree.repository

import com.ddd.onetwothree.entity.Alarm
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.repository.Repository

interface AlarmRepository : Repository<Alarm, Long> {
    @Modifying
    fun save(alarm: Alarm): Alarm

}