package com.ddd.onetwothree.service

import com.ddd.onetwothree.client.fcm.service.FcmNotificationService
import com.ddd.onetwothree.repository.PushRepository
import org.springframework.stereotype.Service

@Service
class PushService(
    private val pushRepository: PushRepository,
    private val fcmNotificationService: FcmNotificationService
) {

    fun push(hour: Int, minute: Int) {

    }

}