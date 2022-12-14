package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.CreateAlarmRequest
import com.ddd.onetwothree.controller.response.AlarmResponse
import com.ddd.onetwothree.entity.Alarm
import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.entity.Push
import com.ddd.onetwothree.exception.NotFoundResourceException
import com.ddd.onetwothree.repository.AlarmRepository
import com.ddd.onetwothree.repository.MemberRepository
import com.ddd.onetwothree.repository.PushRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AlarmService(
    private val memberRepository: MemberRepository,
    private val alarmRepository: AlarmRepository,
    private val pushRepository: PushRepository
) {

    @Transactional
    fun create(req: CreateAlarmRequest): AlarmResponse {
        val member = memberRepository.findById(req.memberId) ?: throw NotFoundResourceException(Member::class)

        return alarmRepository.save(Alarm(member = member)).let {
            val pushList = Push.ofList(req.toPushListCreateRequest(it))
            pushRepository.saveAll(pushList)
        }.let {
            AlarmResponse.of(req, it)
        }
    }

}