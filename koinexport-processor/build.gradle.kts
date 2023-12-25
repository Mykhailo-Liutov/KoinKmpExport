plugins {
    kotlin("jvm")
    `maven-publish`
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

publishing{
    publications.withType<MavenPublication>{
        groupId = "io.github.mykhailo-liutov"
        artifactId = "koinexport-processor"
        version = "1.0"

        pom {
            name = "KoinKmpExport"
            description = "Generated exports for Koin dependencies to be used in iOS."
            url = "https://github.com/Mykhailo-Liutov/KoinKmpExport"
        }
    }
}