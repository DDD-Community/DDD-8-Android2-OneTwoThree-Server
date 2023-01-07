package com.ddd.onetwothree.service

import com.ddd.onetwothree.client.fcm.service.FcmNotificationService
import com.ddd.onetwothree.client.holiday.service.HolidayService
import com.ddd.onetwothree.repository.PushRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PushService(
    private val pushRepository: PushRepository,
    private val fcmNotificationService: FcmNotificationService,
    private val holidayService: HolidayService
) {

    fun push(now: LocalDateTime) {
        holidayService.isHoliday(now)
    }

}