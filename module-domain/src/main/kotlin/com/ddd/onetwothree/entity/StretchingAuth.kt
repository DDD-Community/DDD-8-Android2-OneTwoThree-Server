package com.ddd.onetwothree.entity

import com.ddd.onetwothree.type.StretchingType
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
class StretchingAuth(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stretching_auth_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var member: Member,

    @Enumerated(EnumType.STRING)
    var type: StretchingType,

    var year: Int = LocalDateTime.now().year,
    var month: Int = LocalDateTime.now().monthValue,
    var day: Int = LocalDateTime.now().dayOfMonth,
    var authTime: LocalTime = LocalTime.now()

) : Base() {
    companion object {
        fun of(member: Member, type: StretchingType): StretchingAuth {
            return StretchingAuth(member = member, type = type)
        }
    }

}