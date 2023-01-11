package com.ddd.onetwothree.client.holiday.service

import com.ddd.onetwothree.helper.isWeekend
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class HolidayService(
    private val holidayRedisService: HolidayRedisService
) {

    fun isHoliday(now: LocalDateTime): Boolean {
        return now.isWeekend() || holidayRedisService.isHoliday(now)
    }

}