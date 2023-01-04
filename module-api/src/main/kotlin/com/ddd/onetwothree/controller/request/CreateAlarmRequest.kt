package com.ddd.onetwothree.controller.request

import com.ddd.onetwothree.entity.Alarm
import com.ddd.onetwothree.entity.Member
import java.time.DayOfWeek
import java.time.LocalTime

data class CreateAlarmRequest(
    val dayOfWeeks: List<DayOfWeek>,
    val excludeHoliday: Boolean,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val count: Long
) {
    fun toAlarm(member: Member): Alarm {
        return Alarm(
            member = member,
            dayOfWeeks = dayOfWeeks.joinToString(",") { it.name },
            excludeHoliday = excludeHoliday,
            startTime = startTime,
            endTime = endTime,
            count = count,
        )
    }

}