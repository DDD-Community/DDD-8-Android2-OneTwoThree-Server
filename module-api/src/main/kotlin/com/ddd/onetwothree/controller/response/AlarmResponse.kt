package com.ddd.onetwothree.controller.response

import com.ddd.onetwothree.entity.Alarm
import com.ddd.onetwothree.entity.Push
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.DayOfWeek
import java.time.LocalTime

data class AlarmResponse(
    val alarmId: Long,
    val dayOfWeeks: List<DayOfWeek>,
    val excludeHoliday: Boolean,
    val count: Long,
    @JsonFormat(pattern = "HH:mm:ss")
    val pushTimeList: Set<LocalTime>
) {
    companion object {
        fun of(alarm: Alarm, pushList: List<Push>): AlarmResponse {
            return AlarmResponse(
                alarmId = alarm.id!!,
                dayOfWeeks = alarm.dayOfWeeks.split(",").map { DayOfWeek.valueOf(it) },
                excludeHoliday = alarm.excludeHoliday,
                count = alarm.count,
                pushTimeList = pushList.groupBy { it.time }.keys
            )
        }
    }
}
