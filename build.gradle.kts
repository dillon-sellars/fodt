import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("com.github.ben-manes.versions") version "0.51.0"
    kotlin("plugin.serialization") version "1.9.24"
    id("org.jmailen.kotlinter") version "4.3.0"
}

group = "me.nhoize"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("commons-codec:commons-codec:1.17.0")
    implementation("io.github.artemmey:compose-jb-routing:0.9.13")
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.9.0")
    testImplementation("io.kotest:kotest-assertions-core:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
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

// tasks.withType<KotlinCompile>().configureEach {
//    kotlinOptions.freeCompilerArgs += listOf(
//        "-Xopt-in=kotlin.RequiresOptIn"
//    )
// }
