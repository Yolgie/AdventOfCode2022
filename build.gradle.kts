plugins {
    kotlin("jvm") version "2.0.21"
    application
}

val reflectionsVersion = "0.10.2"
val jsoupVersion = "1.18.1"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.reflections:reflections:$reflectionsVersion")
    implementation("org.jsoup:jsoup:$jsoupVersion")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "MainKt"
}

tasks {
    wrapper {
        gradleVersion = "8.11"
    }
}
