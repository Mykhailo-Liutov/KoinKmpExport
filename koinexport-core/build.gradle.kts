plugins {
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    jvm()
}

publishing{
    publications.withType<MavenPublication>{
        groupId = "io.github.mykhailo-liutov"
        artifactId = "koinexport-core"
        version = "1.0"

        pom {
            name = "KoinKmpExport"
            description = "Generated exports for Koin dependencies to be used in iOS."
            url = "https://github.com/Mykhailo-Liutov/KoinKmpExport"
        }
    }
}