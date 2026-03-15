import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    `maven-publish`
}

group = "com.github.akimaleo"
version = "2.0.3"

kotlin {
    android {
        namespace = "com.kawa.loading.kmp"
        compileSdk { version = release(libs.versions.android.compileSdk.get().toInt()) }
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    jvm()

    val xcfName = "foundation:kmpLoading"
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcfName
            isStatic = true
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
            implementation(libs.kotlin.stdlib)
            // Compose dependencies
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
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

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("KMP Loading Animations")
            description.set("Kotlin Multiplatform loading indicator animations for Compose")
            url.set("https://github.com/akimaleo/kmp-loading-animations")

            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }

            developers {
                developer {
                    id.set("akimaleo")
                    name.set("akimaleo")
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
}
