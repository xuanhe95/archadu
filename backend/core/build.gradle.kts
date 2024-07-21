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


//	// https://mvnrepository.com/artifact/org.hihn/musicbrainzws2-java
//	implementation("org.hihn:musicbrainzws2-java:3.0.15")
//
//	// https://mvnrepository.com/artifact/fm.last/musicbrainz-data
//	implementation("fm.last:musicbrainz-data:3.0.0")
//
//	implementation("info.schnatterer.musicbrainzws2-java:musicbrainzws2-java:3.0.2")
//

	// slf4j
	// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
	implementation("org.slf4j:slf4j-api:2.0.13")

	// slf4j - log4j binding
	// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl
	testImplementation("org.apache.logging.log4j:log4j-slf4j-impl:2.23.1")

	// log4j2 核心
	// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
	implementation("org.apache.logging.log4j:log4j-core:2.23.1")

	// log4j2 API
	// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
	implementation("org.apache.logging.log4j:log4j-api:2.23.1")




	// musicbrainz-api-jersey-client
	implementation ("io.aesy.musicbrainz:musicbrainz-api-jersey-client:1.0.0")
// https://mvnrepository.com/artifact/fm.last/coverartarchive-api
	implementation ("fm.last:coverartarchive-api:2.1.1")

// https://mvnrepository.com/artifact/io.aesy.musicbrainz/musicbrainz-api-entities
	implementation ("io.aesy.musicbrainz:musicbrainz-api-entities:1.0.0")
// https://mvnrepository.com/artifact/io.aesy.musicbrainz/musicbrainz-api
	implementation("io.aesy.musicbrainz:musicbrainz-api:1.0.0")

// JAX-RS 客户端 API

	// Jersey 客户端库
	implementation("org.glassfish.jersey.core:jersey-client:3.1.0")
	implementation("org.glassfish.jersey.inject:jersey-hk2:3.1.0")

	// JAXB API
	implementation ("javax.xml.bind:jaxb-api:2.3.1")

	// JAXB Runtime
	implementation ("org.glassfish.jaxb:jaxb-runtime:2.3.1")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
