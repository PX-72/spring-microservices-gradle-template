plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

val springBootVersion: String by project

dependencies {
    implementation(project(":adapters"))

    // Spring Boot starters
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.data.redis)

    // Kafka
    implementation(libs.spring.kafka)

    // gRPC (for configuration classes)
    implementation("net.devh:grpc-spring-boot-starter:${project.property("grpcSpringBootVersion")}")
    implementation(libs.grpc.netty.shaded)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)

    // Observability
    implementation(libs.micrometer.tracing.bridge.otel)
    implementation(libs.opentelemetry.exporter.otlp)
    implementation(libs.micrometer.registry.prometheus)
    implementation(libs.logstash.logback.encoder)
    implementation(libs.micrometer.core)

    // Database migrations
    implementation(libs.flyway.core)
    implementation(libs.flyway.postgresql)

    // Testing
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(platform(libs.testcontainers.bom))
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.junit.jupiter)
    testImplementation(libs.testcontainers.postgresql)
    testImplementation(libs.testcontainers.kafka)
    testImplementation(libs.spring.kafka.test)
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        mavenBom("org.testcontainers:testcontainers-bom:${project.property("testcontainersVersion")}")
    }
}

// Unit tests (exclude integration tests)
tasks.test {
    useJUnitPlatform()
    include("**/*Test.class")
    exclude("**/*IT.class")
}

// Integration tests
val integrationTest by tasks.registering(Test::class) {
    description = "Runs integration tests."
    group = "verification"
    useJUnitPlatform()
    include("**/*IT.class")
    shouldRunAfter(tasks.test)
}

tasks.check {
    dependsOn(integrationTest)
}
