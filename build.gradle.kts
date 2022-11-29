import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("plugin.jpa") version "1.7.21"
    kotlin("kapt") version "1.7.21"
}

allprojects {
    group = "com.ddd"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        mavenLocal()
    }
}

java.sourceCompatibility = JavaVersion.VERSION_17

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-noarg")
        implementation("org.jetbrains.kotlin:kotlin-allopen")
        implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")

        implementation("io.github.microutils:kotlin-logging:1.7.9")
        implementation("io.mockk:mockk:1.11.0")

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:5.3.2")
        testImplementation("io.kotest:kotest-assertions-core-jvm:5.3.2")
        testImplementation("io.kotest:kotest-property-jvm:5.3.2")

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
