package com.ddd.onetwothree.controller

import com.ddd.onetwothree.client.fcm.service.FcmNotificationService
import com.ddd.onetwothree.controller.request.FcmPushRequest
import com.ddd.onetwothree.controller.response.ResponseEntity
import com.ddd.onetwothree.service.PushService
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/fcm")
class FcmController(
    private val pushService: PushService,
    private val fcmNotificationService: FcmNotificationService,
) {
    private val logger = KotlinLogging.logger { }

    @PostMapping("/push")
    fun pushMessage(@RequestBody req: FcmPushRequest): ResponseEntity<String> {
        return fcmNotificationService.pushMessage(req.token, req.message).let {
            ResponseEntity.ok(it)
        }
    }

    @Async
    @Scheduled(cron = "0 */1 * * * *")
    fun schedulePush() {
        val now = LocalDateTime.now()
        logger.info { "Run Push Schedule : $now" }
        pushService.push(now = now)
    }

}