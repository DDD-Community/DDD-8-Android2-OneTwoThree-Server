package com.ddd.onetwothree.repository

import com.ddd.onetwothree.entity.Member
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.repository.Repository

interface MemberRepository : Repository<Member, Long> {

    @Modifying
    fun save(member: Member): Member

    @Modifying
    fun deleteById(memberId: Long)
    fun findById(memberId: Long): Member?
    fun findByFirebaseToken(firebaseToken: String): Member?

}