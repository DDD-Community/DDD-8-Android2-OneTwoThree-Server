package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.CreateAlarmRequest
import com.ddd.onetwothree.controller.response.AlarmResponse
import com.ddd.onetwothree.entity.Alarm
import com.ddd.onetwothree.entity.Push
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

    @Transactional
    fun create(req: CreateAlarmRequest): AlarmResponse {
        val member = memberDomainService.find(req.memberId)

        return alarmRepository.save(Alarm(member = member)).let {
            val pushList = Push.ofList(req.toPushListCreateRequest(it))
            pushRepository.saveAll(pushList)
        }.let {
            AlarmResponse.of(req, it)
        }
    }

}
