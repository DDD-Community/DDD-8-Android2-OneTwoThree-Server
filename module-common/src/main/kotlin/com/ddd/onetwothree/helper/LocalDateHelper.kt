package com.ddd.onetwothree.helper

import java.time.Duration
import java.time.LocalTime

fun generateLocalTimeInterval(start: LocalTime, end: LocalTime, count: Long): List<LocalTime> {
    val interval = Duration.between(start, end).dividedBy(count + 1)
    return List(count.toInt()) {
        start.plus(interval.multipliedBy(it.plus(1).toLong()))
    }
}