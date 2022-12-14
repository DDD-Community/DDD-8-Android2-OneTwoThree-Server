package com.ddd.onetwothree.exception

abstract class BusinessException(
    val errorCode: ErrorCode,
    override val message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause) {
    constructor(errorCode: ErrorCode): this(errorCode = errorCode, message = errorCode.message)
}