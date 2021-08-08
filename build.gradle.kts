import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("org.springframework.boot") version "2.5.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
    kotlin("plugin.jpa") version "1.5.21"
}


group = "com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
val snippetsDir by extra { file("build/generated-snippets") }


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("junit:junit:4.12")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    outputs.dir(snippetsDir)
}

tasks.asciidoctor {
    attributes["snippets"] = snippetsDir
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
}

tasks.register("copyHTML", Copy::class) {
    dependsOn(tasks.findByName("asciidoctor"))
    from(file("build/docs/asciidoc"))
    into(file("src/main/resources/static/docs"))
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    dependsOn(tasks.getByName("copyHTML"))
}