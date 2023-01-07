dependencies {
    implementation(project(":module-common"))

    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.springframework.boot:spring-boot-starter-data-redis")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

}