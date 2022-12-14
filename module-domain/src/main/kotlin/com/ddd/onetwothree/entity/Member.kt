package com.ddd.onetwothree.entity

import jakarta.persistence.*

@Entity
class Member(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long? = null,

    var firebaseToken: String,
    var nickname: String

) {
}