package com.ddd.onetwothree.controller

import com.ddd.onetwothree.client.fcm.service.FcmNotificationService
import com.ddd.onetwothree.controller.request.FcmPushRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fcm")
class FcmTestController(
    private val fcmNotificationService: FcmNotificationService
) {

    @PostMapping("/push")
    fun pushMessage(@RequestBody req: FcmPushRequest) {
        fcmNotificationService.pushMessage(req.token, req.message)
    }

}