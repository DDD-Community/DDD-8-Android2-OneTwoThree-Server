package com.ddd.onetwothree.dto

import com.ddd.onetwothree.entity.Alarm
import java.time.DayOfWeek
import java.time.LocalTime

data class PushListCreateRequest(
    val alarm: Alarm,
    val dayOfWeeks: List<DayOfWeek>,
    val excludeHoliday: Boolean,
    val pushTimes: List<LocalTime>
)