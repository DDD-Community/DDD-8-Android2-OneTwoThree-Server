package com.ddd.onetwothree.repository

import com.ddd.onetwothree.entity.Member
import org.springframework.data.repository.Repository

interface MemberRepository : Repository<Member, Long> {
}