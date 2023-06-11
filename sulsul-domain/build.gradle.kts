dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo.spring30x:4.7.0")

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
