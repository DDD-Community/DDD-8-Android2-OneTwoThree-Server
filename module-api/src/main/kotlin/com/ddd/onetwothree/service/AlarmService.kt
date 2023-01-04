package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.CreateAlarmRequest
import com.ddd.onetwothree.controller.response.AlarmResponse
import com.ddd.onetwothree.repository.AlarmRepository
import com.ddd.onetwothree.repository.PushRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AlarmService(
    private val memberDomainService: MemberDomainService,
    private val alarmRepository: AlarmRepository,
    private val pushRepository: PushRepository
) {

    fun findAll(memberId: Long): List<AlarmResponse> {
        val member = memberDomainService.findById(memberId)
        return alarmRepository.findAllByMember(member)
            .map {
                val pushList = pushRepository.findByAlarmId(it.id!!)
                AlarmResponse.of(it, pushList)
            }
    }

    @Transactional
    fun create(memberId: Long, req: CreateAlarmRequest): AlarmResponse {
        val member = memberDomainService.findById(memberId)
        return req.toAlarm(member).let {
            alarmRepository.save(it)
        }.let {
            val pushList = pushRepository.saveAll(it.generatePushList())
            AlarmResponse.of(it, pushList)
        }
    }

    @Transactional
    fun delete(alarmId: Long) {
        pushRepository.deleteAllByAlarmId(alarmId)
        alarmRepository.deleteById(alarmId)
    }

}
