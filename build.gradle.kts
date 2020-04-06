import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    val bootVersion = "2.2.6.RELEASE"

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    dependencies {
        // Use this plugin with build-in 'dependency-management' plugin to avoid specifying Spring versions in dependencies.
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$bootVersion")
    }
}

plugins {
    val kotlinVersion = "1.3.71"
    val nodePluginVersion = "1.3.1"

    id ("com.moowork.node") version nodePluginVersion apply false
    id("org.springframework.boot") version "2.2.6.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
    // This two plugins helps to use Spring and JPA annotations/entities with Kotlin without magic.
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

}

subprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    apply {
        plugin("io.spring.dependency-management")
    }
}