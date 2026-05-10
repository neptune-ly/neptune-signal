plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    `maven-publish`
}

group = "ly.neptune.signal"
version = "0.2.0"

kotlin {
    androidTarget {
        publishLibraryVariants("debug", "release")
    }
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.compose.runtime:runtime:1.10.3")
            implementation("org.jetbrains.compose.foundation:foundation:1.10.3")
            implementation("org.jetbrains.compose.material3:material3:1.9.0")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "ly.neptune.signal"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("Neptune. Signal KMP Compose")
            description.set("Kotlin Multiplatform Compose implementation of the Neptune. Signal design language.")
            url.set("https://github.com/neptune-ly/neptune-signal")
            licenses {
                license {
                    name.set("License pending")
                    url.set("https://github.com/neptune-ly/neptune-signal/blob/main/LICENSE")
                }
            }
            developers {
                developer {
                    organization.set("Neptune.")
                    organizationUrl.set("https://neptune.ly")
                }
            }
            scm {
                connection.set("scm:git:https://github.com/neptune-ly/neptune-signal.git")
                developerConnection.set("scm:git:https://github.com/neptune-ly/neptune-signal.git")
                url.set("https://github.com/neptune-ly/neptune-signal")
            }
        }
    }
}
