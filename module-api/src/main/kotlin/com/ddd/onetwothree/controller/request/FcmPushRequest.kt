package com.ddd.onetwothree.controller.request

data class FcmPushRequest(
    val token: String,
    val message: String
) {
}