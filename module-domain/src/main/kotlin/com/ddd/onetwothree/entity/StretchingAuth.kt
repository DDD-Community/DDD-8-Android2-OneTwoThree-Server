package com.ddd.onetwothree.entity

import com.ddd.onetwothree.type.StretchingType
import jakarta.persistence.*
import java.time.LocalTime

@Entity
class StretchingAuth(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stretching_auth_id")
    var id: Long? = null,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var member: Member,

    @Enumerated(EnumType.STRING)
    var type: StretchingType,
    var year: Int,
    var month: Int,
    var day: Int,
    var authTime: LocalTime

) : Base() {

}