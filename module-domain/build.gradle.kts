
dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("com.google.firebase:firebase-admin:7.1.1")

    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("com.h2database:h2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }
}
