package com.ddd.onetwothree.config

import com.ddd.onetwothree.interceptor.UserContextInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfig(
    private val userContextInterceptor: UserContextInterceptor
): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userContextInterceptor)
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val xmlConverters = converters.filterIsInstance<MappingJackson2XmlHttpMessageConverter>()
        converters.forEach {
            if (it is MappingJackson2XmlHttpMessageConverter) {
                converters.remove(it)
            }
        }
        converters += xmlConverters
    }
}