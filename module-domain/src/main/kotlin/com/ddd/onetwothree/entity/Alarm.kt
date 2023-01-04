package com.ddd.onetwothree.entity

import com.ddd.onetwothree.helper.generateLocalTimeInterval
import jakarta.persistence.*
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
class Alarm(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "member_id",
        referencedColumnName = "member_id",
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    var member: Member,

    var dayOfWeeks: String,
    var excludeHoliday: Boolean,
    var startTime: LocalTime,
    var endTime: LocalTime,
    var count: Long

): Base() {

    @get:Transient
    private val parsedDayOfWeeks: List<DayOfWeek>
        get() = dayOfWeeks.split(",").map { DayOfWeek.valueOf(it) }

    fun generatePushList(): List<Push> {
        val pushTimes = generateLocalTimeInterval(startTime, endTime, count)
        return parsedDayOfWeeks.flatMap { dayOfWeek ->
            if (excludeHoliday.and(dayOfWeek in listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY))) {
                return@flatMap emptyList()
            }
            pushTimes.map { time ->
                Push(
                    alarmId = id!!,
                    firebaseToken = member.firebaseToken,
                    dayOfWeek = dayOfWeek,
                    excludeHoliday = excludeHoliday,
                    time = time
                )
            }
        }
    }
}