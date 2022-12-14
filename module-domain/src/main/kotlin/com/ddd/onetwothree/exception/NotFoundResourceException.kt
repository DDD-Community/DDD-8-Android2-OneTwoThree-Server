package com.ddd.onetwothree.exception

import kotlin.reflect.KClass

class NotFoundResourceException(resourceType: KClass<*>): BusinessException(
    errorCode = ErrorCode.RESOURCE_NOT_FOUND,
    message = "${resourceType.simpleName} 리소스를 찾을 수 없습니다."
)
