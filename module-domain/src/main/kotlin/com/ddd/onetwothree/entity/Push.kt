package com.ddd.onetwothree.entity

import com.ddd.onetwothree.dto.PushListCreateRequest
import jakarta.persistence.*
import org.jetbrains.kotlin.konan.util.visibleName
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
class Push(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "push_id")
    var id: Long? = null,

    var alarmId: Long,
    var firebaseToken: String,

    @Enumerated(EnumType.STRING)
    var dayOfWeek: DayOfWeek,

    @Column(name = "push_time")
    var time: LocalTime,

    @Column(name = "exclude_holiday_yn")
    var excludeHoliday: Boolean

) : Base() {
    fun toRecord() = mapOf(
        "alarm_id" to this.alarmId,
        "firebase_token" to this.firebaseToken,
        "day_of_week" to this.dayOfWeek.name,
        "push_time" to this.time,
        "exclude_holiday_yn" to when (this.excludeHoliday) {
            true -> "Y"
            false -> "N"
        },
        "created_at" to this.createdAt,
        "updated_at" to this.updatedAt
    )

    companion object {
        fun ofList(req: PushListCreateRequest): List<Push> {
            return req.dayOfWeeks.flatMap { dayOfWeek ->
                if (req.excludeHoliday.and(dayOfWeek in listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY))) {
                    return@flatMap emptyList()
                }
                req.pushTimes.map { time ->
                    Push(
                        alarmId = req.alarm.id!!,
                        firebaseToken = req.alarm.member.firebaseToken,
                        dayOfWeek = dayOfWeek,
                        excludeHoliday = req.excludeHoliday,
                        time = time
                    )
                }
            }
        }
    }
}