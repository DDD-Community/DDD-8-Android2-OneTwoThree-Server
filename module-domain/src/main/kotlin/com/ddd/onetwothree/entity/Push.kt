package com.ddd.onetwothree.entity

import jakarta.persistence.*
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
    var time: LocalTime,

    @Column(name = "exclude_holiday_yn")
    var excludeHoliday: Boolean

) {
}