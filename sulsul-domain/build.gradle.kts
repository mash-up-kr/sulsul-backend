dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    // 	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
