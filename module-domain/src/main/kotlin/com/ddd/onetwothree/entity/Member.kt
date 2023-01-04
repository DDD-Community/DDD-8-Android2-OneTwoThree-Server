package com.ddd.onetwothree.entity

import jakarta.persistence.*

@Entity
class Member(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long? = null,

    var firebaseToken: String,
    var nickname: String

): Base() {
    fun changeNickname(nickname: String): Member {
        this.nickname = nickname
        return this
    }
}