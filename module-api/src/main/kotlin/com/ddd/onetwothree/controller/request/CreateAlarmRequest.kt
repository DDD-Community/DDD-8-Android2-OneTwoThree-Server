package com.ddd.onetwothree.controller.request

import com.ddd.onetwothree.dto.PushListCreateRequest
import com.ddd.onetwothree.entity.Alarm
import com.ddd.onetwothree.helper.generateLocalTimeInterval
import java.time.DayOfWeek
import java.time.LocalTime

data class CreateAlarmRequest(
    val memberId: Long,
    val dayOfWeeks: List<DayOfWeek>,
    val excludeHoliday: Boolean,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val count: Long
) {

    fun toPushListCreateRequest(alarm: Alarm): PushListCreateRequest {
        return PushListCreateRequest(
            alarm = alarm,
            dayOfWeeks = dayOfWeeks,
            excludeHoliday = excludeHoliday,
            pushTimes = generateLocalTimeInterval(startTime, endTime, count)
        )
    }

}