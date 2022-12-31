package com.ddd.onetwothree.controller

import com.ddd.onetwothree.controller.request.CreateAlarmRequest
import com.ddd.onetwothree.controller.response.AlarmResponse
import com.ddd.onetwothree.controller.response.ResponseEntity
import com.ddd.onetwothree.service.AlarmService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/alarm")
class AlarmController(
    private val alarmService: AlarmService
) {

    @PostMapping
    fun createAlarm(@RequestBody req: CreateAlarmRequest): ResponseEntity<AlarmResponse> {
        return alarmService.create(req).let {
            ResponseEntity.ok(it)
        }
    }

}