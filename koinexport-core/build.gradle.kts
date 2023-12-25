import java.net.URI

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
    signing
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    jvm()
}

publishing {
    publications.withType<MavenPublication> {
        groupId = "io.github.mykhailo-liutov"
        version = "1.0"

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
        maven {
            name = "sonatype"
            url = URI.create("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

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