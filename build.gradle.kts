import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("com.github.ben-manes.versions") version "0.51.0"
    kotlin("plugin.serialization") version "2.0.0"
    id("org.jmailen.kotlinter") version "4.3.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

group = "ot"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0")
    implementation("commons-codec:commons-codec:1.17.0")
    implementation("io.github.artemmey:compose-jb-routing:0.9.13")
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}

tasks.test {
    useJUnitPlatform()
}

val toolchainAction: (JavaToolchainSpec).() -> Unit = {
    languageVersion = JavaLanguageVersion.of(21)
    vendor = JvmVendorSpec.AZUL
}

java {
    toolchain(toolchainAction)
}

kotlin {
    jvmToolchain(toolchainAction)
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "fodt"
            packageVersion = project.version.toString()
            macOS {
                iconFile.set(project.file("src/main/resources/icon.icns"))
            }
            windows {
                menuGroup = "fodt"
                iconFile.set(project.file("src/main/resources/icon.ico"))
            }
            linux {
                iconFile.set(project.file("src/main/resources/icon.png"))
            }
        }
    }
}
