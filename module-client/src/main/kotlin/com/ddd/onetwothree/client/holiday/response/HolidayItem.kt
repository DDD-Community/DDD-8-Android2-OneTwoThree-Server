package com.ddd.onetwothree.client.holiday.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@JacksonXmlRootElement(localName = "response")
data class HolidayOpenApiResponse(
    @JacksonXmlProperty(localName = "header")
    val header: HolidayHeader,
    @JacksonXmlProperty(localName = "body")
    val body: HolidayBody
)

data class HolidayHeader(
    @JacksonXmlProperty(localName = "resultCode")
    val resultCode: String,
    @JacksonXmlProperty(localName = "resultMsg")
    val resultMsg: String
)

data class HolidayBody(
    @JacksonXmlProperty(localName = "items")
    val items: List<HolidayItem>,
    @JacksonXmlProperty(localName = "numOfRows")
    val numOfRows: Long,
    @JacksonXmlProperty(localName = "pageNo")
    val pageNo: Long,
    @JacksonXmlProperty(localName = "totalCount")
    val totalCount: Long
)

data class HolidayItem(
    @JacksonXmlProperty(localName = "dateKind")
    val dateKind: String,
    @JacksonXmlProperty(localName = "dateName")
    val dateName: String,
    @JacksonXmlProperty(localName = "isHoliday")
    val isHoliday: String,
    @JacksonXmlProperty(localName = "locdate")
    val date: String,
    @JacksonXmlProperty(localName = "seq")
    val seq: String
) {
    fun toResponse(): HolidayResponse {
        return HolidayResponse(date = LocalDate.parse(date, FORMATTER), name = dateName)
    }

    companion object {
        private val FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd")
    }
}