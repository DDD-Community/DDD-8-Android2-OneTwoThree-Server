package com.ddd.onetwothree.client.fcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FcmConfig(
    @Value("\${fcm.credential-path}") private val credentialPath: String,
    @Value("\${fcm.scope}") private val scope: String,
) {
    private val logger = KotlinLogging.logger { }

    @Bean
    fun generateFirebaseApp(): FirebaseApp {
        ClassPathResource(credentialPath).inputStream.use {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(it).createScoped(listOf(scope)))
                .build()

            return if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options).also {
                    logger.info { "FirebaseApp initialization complete!" }
                }
            } else throw RuntimeException("FirebaseApp initialization failed!")
        }
    }

    @Bean
    fun generateFirebaseMessaging(app: FirebaseApp): FirebaseMessaging {
        return FirebaseMessaging.getInstance(app)
    }

}