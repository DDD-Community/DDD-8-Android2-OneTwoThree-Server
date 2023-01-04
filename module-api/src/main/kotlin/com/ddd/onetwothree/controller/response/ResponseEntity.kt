package com.ddd.onetwothree.controller.response

class ResponseEntity<T>(
    val data: T
) {
    companion object {
        fun <T> ok(data: T): ResponseEntity<T> {
            return ResponseEntity(data)
        }

        fun ok(): ResponseEntity<Unit> {
            return ResponseEntity(Unit)
        }
    }
}