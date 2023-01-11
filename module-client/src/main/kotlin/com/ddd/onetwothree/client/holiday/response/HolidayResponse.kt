package com.ddd.onetwothree.client.holiday.response

import java.time.LocalDate

data class HolidayResponse(
    val date: LocalDate,
    val name: String
)