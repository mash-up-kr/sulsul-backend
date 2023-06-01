dependencies {
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    // 	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
