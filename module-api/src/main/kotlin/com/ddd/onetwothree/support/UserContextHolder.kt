package com.ddd.onetwothree.support

import com.ddd.onetwothree.exception.NotFoundResourceException

object UserContextHolder {
    private val userContext = ThreadLocal<UserContext>()

    fun setContext(user: UserContext) = userContext.set(user)
    fun clearContext() = userContext.remove()
    fun getContext() = userContext.get()
        ?: throw NotFoundResourceException(UserContext::class)
}