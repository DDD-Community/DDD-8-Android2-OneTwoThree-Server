package com.ddd.onetwothree.client.fcm.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FcmNotificationService(
    private val fcm: FirebaseMessaging
) {

    fun pushMessage(token: String, msg: String) {
        val notification = Notification.builder()
            .setTitle(DEFAULT_TITLE)
            .setBody(msg)
            .build()
        val message = Message.builder()
            .setToken(token)
            .setNotification(notification)
            .build()
        fcm.send(message)
    }

    companion object {
        const val DEFAULT_TITLE: String = "하나! 둘! 셋!"
    }
}