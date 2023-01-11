package com.ddd.onetwothree.client.holiday.service

import com.ddd.onetwothree.client.holiday.HolidayOpenApiClient
import com.ddd.onetwothree.client.holiday.repository.HolidayRedisRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class HolidayRedisService(
    private val holidayRedisRepository: HolidayRedisRepository,
    private val holidayOpenApiClient: HolidayOpenApiClient
) {

    fun isHoliday(now: LocalDateTime): Boolean {
        if (!holidayRedisRepository.exists(now)) {
            val holidays = holidayOpenApiClient.retrieve(now.year)
            holidayRedisRepository.setAll(holidays)
        }
        return holidayRedisRepository.isHoliday(now)
    }

}