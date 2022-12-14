
dependencies {
    implementation(project(":module-domain"))
    implementation(project(":module-common"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}