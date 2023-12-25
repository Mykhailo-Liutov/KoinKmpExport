plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    jvm()
}