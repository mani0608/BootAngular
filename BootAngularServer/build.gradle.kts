import org.springframework.boot.gradle.tasks.bundling.BootWar

/*plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	war
	kotlin("jvm") version "1.3.71"
	kotlin("plugin.spring") version "1.3.71"
}*/

plugins {
	java
	war
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
	id("org.springframework.boot")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	implementation(project(":BootAngularFrontend"))
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName<BootWar>("bootWar") {
	//dependsOn(":BootAngularFrontend:jar")
	enabled = true
}

springBoot {
	mainClassName = "com.example.bootangular.BootAngularApplicationKt"
}