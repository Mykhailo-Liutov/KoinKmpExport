import java.net.URI

plugins {
    kotlin("jvm")
    `maven-publish`
    signing
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

publishing {
    publications.create<MavenPublication>("Maven"){
        groupId = "io.github.mykhailo-liutov"
        version = "1.0"

        from(components["java"])

        pom {
            name = "KoinKmpExport"
            description = "Generated exports for Koin dependencies to be used in iOS."
            url = "https://github.com/Mykhailo-Liutov/KoinKmpExport"

            licenses {
                license {
                    name = "MIT License"
                }
            }
            scm {
                connection = "scm:git@github.com:Mykhailo-Liutov/KoinKmpExport.git"
                url = "https://github.com/Mykhailo-Liutov/KoinKmpExport"
            }
            developers {
                developer {
                    id = "mykhailo.liutov"
                    name = "Mykhailo Liutov"
                    email = "m.liutov.dev@gmail.com"
                }
            }
        }
    }

    repositories {
        maven {
            name = "SonatypeSnapshot"
            url = URI.create("https://s01.oss.sonatype.org/content/repositories/snapshots/")

            credentials {
                username = properties["ossrhUsername"] as? String
                password = properties["ossrhPassword"] as? String
            }
        }
    }
}

signing {
    sign(publishing.publications)
}