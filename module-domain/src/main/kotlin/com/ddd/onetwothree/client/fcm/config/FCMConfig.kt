package com.ddd.onetwothree.client.fcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FCMConfig(
    @Value("\${fcm.credential-path}") private val credentialPath: String,
    @Value("\${fcm.scope}") private val scope: String,
) {
    private val logger = KotlinLogging.logger { }

    @PostConstruct
    fun initFCM() {
        ClassPathResource(credentialPath).inputStream.use {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(it).createScoped(listOf(scope)))
                .build()

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
                logger.info { "FirebaseApp initialization complete!" }
            }
        }
    }

}