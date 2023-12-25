plugins {
    kotlin("jvm")
}

dependencies {
    implementation(projects.koinexportCore)
    implementation(libs.kotlin.ksp)
    implementation(libs.bundles.kotlin.poet)
    implementation(libs.koin.core)
}

kotlin {
    jvmToolchain(11)
}