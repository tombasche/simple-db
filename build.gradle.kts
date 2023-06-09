import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    val kotlinVersion = "1.8.10"
    kotlin("jvm") version kotlinVersion
    id("com.github.jk1.dependency-license-report") version "2.1"
}

group = "com.wolt"
version = "0.0.1"

repositories {
    mavenCentral()
}

application {
    mainClass.set("MainKt")
}

dependencies {
    val kotestVersion = "5.6.2"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-extensions-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.mockk:mockk:1.13.4")
}

sourceSets {
    main {
        kotlin {
            srcDir("$buildDir/generated-dtos/src/main/kotlin")
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "18"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("failed", "passed", "started")
        showExceptions = true
        showCauses = true
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}