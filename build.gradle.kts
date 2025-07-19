plugins {
    application
    jacoco
    id("java")
    id("checkstyle")
    id("io.freefair.lombok") version "8.13.1"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "hexlet.code.Main"
}

dependencies {
    implementation(libs.guava)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    implementation(libs.picocli)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.dataformat.yaml)
}

checkstyle {
    configFile = File(rootDir, "./config/checkstyle/checkstyle.xml")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}

