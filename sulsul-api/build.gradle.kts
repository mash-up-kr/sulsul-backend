dependencies {
    implementation(project(":sulsul-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-security")

    // mongodb
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // swagger
    // springdoc-openapi
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
}

tasks {
    bootJar {
        enabled = true
        archiveFileName.set("application.jar")
    }

    jar {
        enabled = false
    }
}
