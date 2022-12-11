package com.ddd.onetwothree.repository

import com.ddd.onetwothree.entity.Push
import org.springframework.data.repository.Repository

interface PushRepository : Repository<Push, Long> {
}