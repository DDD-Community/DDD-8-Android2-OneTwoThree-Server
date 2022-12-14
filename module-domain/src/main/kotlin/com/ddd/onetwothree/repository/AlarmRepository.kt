package com.ddd.onetwothree.repository

import com.ddd.onetwothree.entity.Alarm
import org.springframework.data.repository.Repository

interface AlarmRepository : Repository<Alarm, Long> {
}