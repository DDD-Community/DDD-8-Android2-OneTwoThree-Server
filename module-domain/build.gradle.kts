
dependencies {
    implementation(project(":module-common"))

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-data-jdbc")
    api("com.google.firebase:firebase-admin:9.1.1")

    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("com.h2database:h2")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.MappedSuperclass")
        annotation("jakarta.persistence.Embeddable")
    }

}
