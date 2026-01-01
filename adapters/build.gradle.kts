plugins {
    `java-library`
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.protobuf)
}

val springBootVersion: String by project
val grpcVersion: String by project
val protobufVersion: String by project
val grpcSpringBootVersion: String by project

dependencies {
    api(project(":domain"))

    // Spring Boot starters
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.data.redis)

    // Kafka
    implementation(libs.spring.kafka)

    // Database
    runtimeOnly(libs.postgresql)

    // gRPC
    implementation("net.devh:grpc-spring-boot-starter:$grpcSpringBootVersion")
    implementation(libs.grpc.netty.shaded)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)
    implementation(libs.protobuf.java)
    implementation(libs.jakarta.annotation.api)
    implementation(libs.javax.annotation.api)

    // Observability
    implementation(libs.micrometer.core)

    // Testing
    testImplementation(libs.spring.boot.starter.test)
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("grpc")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
