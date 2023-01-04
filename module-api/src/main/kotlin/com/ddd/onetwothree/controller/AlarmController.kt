package com.ddd.onetwothree.controller

import com.ddd.onetwothree.annotation.RequiredUserInfo
import com.ddd.onetwothree.controller.request.CreateAlarmRequest
import com.ddd.onetwothree.controller.response.AlarmResponse
import com.ddd.onetwothree.controller.response.ResponseEntity
import com.ddd.onetwothree.service.AlarmService
import com.ddd.onetwothree.support.UserContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/alarm")
class AlarmController(
    private val alarmService: AlarmService
) {

    @PostMapping
    @RequiredUserInfo
    fun createAlarm(@RequestBody req: CreateAlarmRequest): ResponseEntity<Unit> {
        val memberId = UserContextHolder.getContext().memberId
        return alarmService.create(memberId, req)
            .let { ResponseEntity.ok() }
    }

    @DeleteMapping("/{alarmId}")
    fun deleteAlarm(@PathVariable alarmId: Long): ResponseEntity<Unit> {
        return alarmService.delete(alarmId)
            .let { ResponseEntity.ok() }
    }

    @GetMapping
    @RequiredUserInfo
    fun retrieveAlarm(): ResponseEntity<List<AlarmResponse>> {
        val memberId = UserContextHolder.getContext().memberId
        return alarmService.findAll(memberId)
            .let { ResponseEntity(it) }
    }

}