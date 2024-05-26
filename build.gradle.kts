import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0"
    id("com.github.ben-manes.versions") version "0.39.0"
    kotlin("plugin.serialization") version "1.5.31"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

group = "me.nhoize"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.auth0:java-jwt:3.18.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("commons-codec:commons-codec:1.15")
    implementation("io.github.artemmey:compose-jb-routing:0.9.13")
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.0.3")
    testImplementation("io.kotest:kotest-assertions-core:5.0.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "kleep"
            packageVersion = "1.0.0"
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xopt-in=kotlin.RequiresOptIn"
    )
}
