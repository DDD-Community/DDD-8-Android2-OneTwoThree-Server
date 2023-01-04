package com.ddd.onetwothree.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val code: String,
    val message: String
) {
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "-404", "해당 리소스를 찾을 수 없습니다."),
    FIREBASE_TOKEN_DUPLICATE(HttpStatus.BAD_REQUEST, "-400", "중복된 사용자 정보입니다.")

}