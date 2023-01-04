package com.ddd.onetwothree.interceptor

import com.ddd.onetwothree.annotation.RequiredUserInfo
import com.ddd.onetwothree.exception.NotFoundAuthenticationHeaderException
import com.ddd.onetwothree.support.UserContext
import com.ddd.onetwothree.support.UserContextHolder
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class UserContextInterceptor: HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (hasRequiredUserInfo(handler)) {
            val memberId = request.getHeader("onetwothree-member-id")
                ?: throw NotFoundAuthenticationHeaderException()
            UserContextHolder.setContext(UserContext(memberId = memberId.toLong()))
        }
        return true
    }

    private fun hasRequiredUserInfo(handler: Any): Boolean {
        if (handler is HandlerMethod) {
            return handler.hasMethodAnnotation(RequiredUserInfo::class.java)
                    || handler.method.declaringClass.isAnnotationPresent(RequiredUserInfo::class.java)
        }
        return false
    }
}