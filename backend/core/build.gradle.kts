plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "org.archadu"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Sa-Token 权限认证，在线文档：https://sa-token.cc
	implementation ("cn.dev33:sa-token-spring-boot3-starter:1.38.0")

	// Sa-Token-OAuth2.0 模块
	implementation ("cn.dev33:sa-token-oauth2:1.38.0")


	// https://mvnrepository.com/artifact/org.springframework/spring-web
	implementation("org.springframework:spring-web:6.1.8")


	// https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
	implementation("org.hibernate.orm:hibernate-core:6.5.2.Final")

	// https://mvnrepository.com/artifact/org.postgresql/postgresql
	implementation("org.postgresql:postgresql:42.7.3")

//	// https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
//	implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")

// https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")


//	// https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
//	implementation("org.springframework.data:spring-data-jpa:3.3.0")

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.0")

	// https://mvnrepository.com/artifact/jakarta.validation/jakarta.validation-api
	implementation("jakarta.validation:jakarta.validation-api:3.1.0")




}

tasks.withType<Test> {
	useJUnitPlatform()
}
