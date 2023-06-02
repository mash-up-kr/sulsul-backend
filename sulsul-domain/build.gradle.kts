dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

    // RDS 필요하면 사용
    // implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // runtimeOnly("com.h2database:h2")
    // runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
