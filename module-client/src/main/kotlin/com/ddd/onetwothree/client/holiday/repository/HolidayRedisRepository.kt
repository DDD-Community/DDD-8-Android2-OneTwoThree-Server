package com.ddd.onetwothree.client.holiday.repository

import com.ddd.onetwothree.client.holiday.response.HolidayResponse
import mu.KotlinLogging
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class HolidayRedisRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val logger = KotlinLogging.logger { }
    private val opsHash by lazy {
        redisTemplate.opsForHash<String, String>()
    }

    fun exists(now: LocalDateTime): Boolean {
        return opsHash.hasKey("${now.year}-${now.monthValue}", now.dayOfMonth.toString())
            .also {
                if (!it) {
                    opsHash.put("${now.year}-${now.monthValue}", now.dayOfMonth.toString(), false.toString())
                }
            }
    }

    fun setAll(list: List<HolidayResponse>) {
        list.forEach {
            opsHash.put("${it.date.year}-${it.date.monthValue}", it.date.dayOfMonth.toString(), true.toString())
            logger.info { "수집된 공휴일 : ${it.date} (${it.name})" }
        }
    }

    fun isHoliday(now: LocalDateTime): Boolean {
        return opsHash.get("${now.year}-${now.monthValue}", now.dayOfMonth.toString()).toBoolean()
    }

}
