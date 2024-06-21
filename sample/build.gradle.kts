import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    with(libs.plugins) {
        alias(kotlin.multiplatform)
        alias(android.library)
        alias(kotlin.ksp)
    }
}

kotlin {
    jvmToolchain(11)
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "sample"
            isStatic = true
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.koinexportCore)
                implementation(libs.koin.core)
            }
        }
    }
}

dependencies {
    add("kspIosSimulatorArm64", projects.koinexportProcessor)
    add("kspIosX64", projects.koinexportProcessor)
    add("kspIosArm64", projects.koinexportProcessor)
}

ksp {
    arg("packageName", "io.github.mykhailoliutov.koinexport.sample")
    arg("exportsFileName", "AppDependency")
    arg("mode", "properties")
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}

android {
    namespace = "io.github.mykhailoliutov.koinexport.sample"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}
