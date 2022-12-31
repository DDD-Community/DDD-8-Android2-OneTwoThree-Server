package com.ddd.onetwothree.controller.response

import com.ddd.onetwothree.controller.request.CreateAlarmRequest
import com.ddd.onetwothree.entity.Push
import java.time.DayOfWeek
import java.time.LocalTime

data class AlarmResponse(
    val dayOfWeeks: List<DayOfWeek>,
    val excludeHoliday: Boolean,
    val pushTimeList: Set<LocalTime>
) {
    companion object {
        fun of(req: CreateAlarmRequest, pushList: List<Push>): AlarmResponse {
            return AlarmResponse(
                dayOfWeeks = req.dayOfWeeks,
                excludeHoliday = req.excludeHoliday,
                pushTimeList = pushList.groupBy { it.time }.keys
            )
        }
    }
}
