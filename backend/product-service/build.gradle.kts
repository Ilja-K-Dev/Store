plugins {
	java
	id("org.springframework.boot") version "3.5.14"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation(project(":shared-kernel"))

	implementation("org.springframework.boot:spring-boot-starter-actuator")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("org.springframework.boot:spring-boot-starter-validation")
	
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.springframework.kafka:spring-kafka")
	
	implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")

	implementation("org.flywaydb:flyway-core")

	implementation("org.flywaydb:flyway-database-postgresql")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.16")

	runtimeOnly("org.postgresql:postgresql")
    
	compileOnly("org.projectlombok:lombok:1.18.34")

    annotationProcessor("org.projectlombok:lombok:1.18.34")

	/*Testing*/
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	testImplementation("org.springframework.kafka:spring-kafka-test")

	testImplementation("org.testcontainers:junit-jupiter")

	testImplementation("org.testcontainers:postgresql")

	testImplementation("org.testcontainers:kafka")
    
    testCompileOnly("org.projectlombok:lombok:1.18.34")

    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
