import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.mavenPublish)
}

group = "io.github.akimaleo"
version = "2.0.5"

val isMacOS = System.getProperty("os.name").contains("Mac", ignoreCase = true)

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
            api(libs.kotlin.stdlib)
            api(libs.compose.runtime)
            api(libs.compose.foundation)
            api(libs.compose.material3)
            api(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.material.icons.extended)
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

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    coordinates(group.toString(), "kmp-loading-animation", version.toString())

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

tasks.withType<Sign>().configureEach {
    onlyIf { project.findProperty("signingInMemoryKey") != null }
}
