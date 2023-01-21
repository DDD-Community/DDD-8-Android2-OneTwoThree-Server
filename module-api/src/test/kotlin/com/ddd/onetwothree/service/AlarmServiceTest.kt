package com.ddd.onetwothree.service

import com.ddd.onetwothree.controller.request.CreateAlarmRequest
import com.ddd.onetwothree.entity.Alarm
import com.ddd.onetwothree.entity.Member
import com.ddd.onetwothree.entity.Push
import com.ddd.onetwothree.exception.ErrorCode
import com.ddd.onetwothree.exception.NotFoundResourceException
import com.ddd.onetwothree.helper.generateLocalTimeInterval
import com.ddd.onetwothree.repository.AlarmRepository
import com.ddd.onetwothree.repository.PushRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalTime

class AlarmServiceTest {

    private val memberDomainService: MemberDomainService = mock()
    private val alarmRepository: AlarmRepository = mock()
    private val pushRepository: PushRepository = mock()
    private val alarmService = AlarmService(memberDomainService, alarmRepository, pushRepository)

//    @Test
//    fun `create - 정상동작 확인`() {
//        // given
//        val member = Member(nickname = "ch4njun", firebaseToken = "sample_firebase_token")
//        val alarm = Alarm(member = member)
//        val req = CreateAlarmRequest(
//            memberId = 1L,
//            dayOfWeeks = listOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY),
//            excludeHoliday = true,
//            startTime = LocalTime.of(9, 0),
//            endTime = LocalTime.of(18, 0),
//            count = 5
//        )
//        whenever(memberDomainService.findById(any())).thenReturn(member.apply { this.id = 1L })
//        whenever(alarmRepository.save(any())).thenReturn(alarm.apply { this.id = 1L })
//        whenever(pushRepository.saveAll(any()))
//            .thenReturn(
//                Push.ofList(req.toPushListCreateRequest(alarm.apply { this.id = 1L }))
//                    .onEachIndexed { idx, push ->
//                        push.id = idx.toLong() + 1
//                    }
//            )
//
//        // when & then
//        alarmService.create(req).let {
//            it.shouldNotBeNull()
//            it.dayOfWeeks shouldBe req.dayOfWeeks
//            it.excludeHoliday shouldBe req.excludeHoliday
//            it.pushTimeList.forEach { time ->
//                time shouldBeIn generateLocalTimeInterval(req.startTime, req.endTime, req.count)
//            }
//        }
//    }
//
//    @Test
//    fun `create - member 없을시 에러 확인`() {
//        // given
//        val req = CreateAlarmRequest(
//            memberId = 1L,
//            dayOfWeeks = listOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY),
//            excludeHoliday = true,
//            startTime = LocalTime.of(9, 0),
//            endTime = LocalTime.of(18, 0),
//            count = 5
//        )
//        whenever(memberDomainService.findById(any())).thenThrow(NotFoundResourceException(Member::class))
//
//        // when & then
//        shouldThrow<NotFoundResourceException> {
//            alarmService.create(req)
//        }.let {
//            it.errorCode shouldBe ErrorCode.RESOURCE_NOT_FOUND
//            it.message shouldBe "Member 리소스를 찾을 수 없습니다."
//        }
//    }

}