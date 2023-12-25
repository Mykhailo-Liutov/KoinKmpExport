// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    with(libs.plugins) {
        listOf(
            android.application,
            android.library,
            kotlin.android,
            kotlin.dokka,
            kotlin.multiplatform,
        ).forEach {
            alias(it) apply false
        }
    }
}