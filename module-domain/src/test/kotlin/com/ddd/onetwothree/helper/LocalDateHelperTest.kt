package com.ddd.onetwothree.helper

import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalTime

internal class LocalDateHelperTest {

    @Test
    fun `generateLocalTimeInterval - 정상동작 확인`() {
        // given
        val start = LocalTime.of(9, 0)
        val end = LocalTime.of(18, 0)
        val count = 5L

        // when & then
        generateLocalTimeInterval(start, end, count).let {
            it.shouldNotBeNull()
            it.size shouldBe count
            it shouldBeEqualToComparingFields listOf(
                LocalTime.of(10, 30),
                LocalTime.of(12, 0),
                LocalTime.of(13, 30),
                LocalTime.of(15, 0),
                LocalTime.of(16, 30)
            )
        }
    }

}