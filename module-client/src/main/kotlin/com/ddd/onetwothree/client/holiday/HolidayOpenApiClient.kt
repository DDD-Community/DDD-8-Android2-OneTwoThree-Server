package com.ddd.onetwothree.client.holiday

import com.ddd.onetwothree.client.holiday.response.HolidayItem
import com.ddd.onetwothree.client.holiday.response.HolidayOpenApiResponse
import com.ddd.onetwothree.client.holiday.response.HolidayResponse
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class HolidayOpenApiClient(
    @Value("\${openapi.baseUrl}") private val baseUrl: String,
    @Value("\${openapi.serviceKey}") private val serviceKey: String,
) {
    private val webClient = WebClient.builder().baseUrl(baseUrl).build()
    private val xmlMapper = XmlMapper.builder().build()

    fun retrieve(year: Int): List<HolidayResponse> {
        val params = LinkedMultiValueMap<String, String>().apply {
            this.add("serviceKey", serviceKey)
            this.add("pageNo", "1")
            this.add("numOfRows", "100")
            this.add("solYear", year.toString())
        }
        return webClient.get()
            .uri { it.queryParams(params).build() }
            .retrieve()
            .bodyToMono<String>()
            .map {
                xmlMapper.readValue<HolidayOpenApiResponse>(it)
                    .body
                    .items
                    .map(HolidayItem::toResponse)
            }
            .block()!!
    }

}