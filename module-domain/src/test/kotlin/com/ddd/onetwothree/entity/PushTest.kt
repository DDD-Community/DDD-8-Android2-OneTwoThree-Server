package com.ddd.onetwothree.entity

import com.ddd.onetwothree.dto.PushListCreateRequest
import com.ddd.onetwothree.helper.generateLocalTimeInterval
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalTime

class PushTest {

    private val member = Member(id = 1L, firebaseToken = "sample_firebase_token", nickname = "ch4njun")
    private val alarm = Alarm(id = 1L, member = member)

    @Test
    fun `ofList - 정상동작 확인`() {
        // given
        val startTime = LocalTime.of(9, 0)
        val endTime = LocalTime.of(18, 0)
        val pushTimes = generateLocalTimeInterval(startTime, endTime, 5)
        val dayOfWeeks = listOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)
        val req = PushListCreateRequest(alarm = alarm, dayOfWeeks = dayOfWeeks, excludeHoliday = false, pushTimes = pushTimes)

        // when & then
        Push.ofList(req).let {
            it.shouldNotBeNull()
            it.size shouldBe pushTimes.size * 3
            it.forEach { push ->
                push.alarmId shouldBe alarm.id
                push.firebaseToken shouldBe member.firebaseToken
                push.excludeHoliday shouldBe false
                push.dayOfWeek shouldBeIn dayOfWeeks
            }
            it.groupBy { g -> g.dayOfWeek }.forEach { (_, v) ->
                v.map { p -> p.time } shouldBe pushTimes
            }
        }
    }

    @Test
    fun `ofList - 공휴일 제외 체크시 주말 제외되는지 확인`() {
        // given
        val startTime = LocalTime.of(9, 0)
        val endTime = LocalTime.of(18, 0)
        val pushTimes = generateLocalTimeInterval(startTime, endTime, 5)
        val dayOfWeeks = listOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)
        val req = PushListCreateRequest(alarm = alarm, dayOfWeeks = dayOfWeeks, excludeHoliday = true, pushTimes = pushTimes)

        // when & then
        Push.ofList(req).let {
            it.shouldNotBeNull()
            it.size shouldBe pushTimes.size * 2
            it.forEach { push ->
                push.alarmId shouldBe alarm.id
                push.firebaseToken shouldBe member.firebaseToken
                push.excludeHoliday shouldBe true
                push.dayOfWeek shouldBeIn (dayOfWeeks - DayOfWeek.SATURDAY)
            }
            it.groupBy { g -> g.dayOfWeek }.forEach { (_, v) ->
                v.map { p -> p.time } shouldBe pushTimes
            }
        }
    }
}