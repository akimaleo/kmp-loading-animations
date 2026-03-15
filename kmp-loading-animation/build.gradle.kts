import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    `maven-publish`
    signing
}

group = "io.github.akimaleo"
version = "2.0.4"

// Check if we're running on a Mac (required for iOS targets)
val isMacOS = System.getProperty("os.name").contains("Mac", ignoreCase = true)

// Load local properties for signing (optional, for local testing)
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(localPropertiesFile.inputStream())
    }
}

kotlin {
    android {
        namespace = "com.kawa.loading.kmp"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    jvm()

    // Only configure iOS targets on macOS
    if (isMacOS) {
        val xcfName = "kmpLoading"
        listOf(
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = xcfName
                isStatic = true
            }
        }
    }

    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            // Use api() for transitive dependencies that consumers need
            api(libs.kotlin.stdlib)
            // Compose dependencies
            api(libs.compose.runtime)
            api(libs.compose.foundation)
            api(libs.compose.material3)
            api(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.ui.tooling.preview)
            // Icons
            implementation(libs.material.icons.extended)
            // View models
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }

        androidMain.dependencies {
            implementation(libs.ui.tooling)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

// Configure publishing for Maven Central
publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("KMP Loading Animations")
            description.set("Kotlin Multiplatform loading indicator animations for Compose Multiplatform - Android, iOS, Desktop, and Web")
            url.set("https://github.com/akimaleo/kmp-loading-animations")
            inceptionYear.set("2023")

            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("repo")
                }
            }

            developers {
                developer {
                    id.set("akimaleo")
                    name.set("Akimaleo")
                    url.set("https://github.com/AkimAleo")
                }
            }

            scm {
                url.set("https://github.com/akimaleo/kmp-loading-animations")
                connection.set("scm:git:git://github.com/akimaleo/kmp-loading-animations.git")
                developerConnection.set("scm:git:ssh://git@github.com/akimaleo/kmp-loading-animations.git")
            }
        }
    }

    repositories {
        maven {
            name = "MavenCentral"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("OSSRH_USERNAME") 
                    ?: localProperties.getProperty("ossrh.username", "")
                password = System.getenv("OSSRH_PASSWORD") 
                    ?: localProperties.getProperty("ossrh.password", "")
            }
        }
        maven {
            name = "MavenCentralSnapshots"
            url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = System.getenv("OSSRH_USERNAME") 
                    ?: localProperties.getProperty("ossrh.username", "")
                password = System.getenv("OSSRH_PASSWORD") 
                    ?: localProperties.getProperty("ossrh.password", "")
            }
        }
    }
}

// GPG Signing (required for Maven Central)
signing {
    // Use in-memory key from environment variables (for CI)
    val signingKeyId = System.getenv("SIGNING_KEY_ID")
    val signingKey = System.getenv("SIGNING_KEY")
    val signingPassword = System.getenv("SIGNING_PASSWORD")
    
    if (signingKeyId != null && signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    }
    
    sign(publishing.publications)
}

// Make signing conditional (don't fail if not configured)
tasks.withType<Sign>().configureEach {
    onlyIf { 
        System.getenv("SIGNING_KEY") != null || 
        project.hasProperty("signing.keyId")
    }
}
