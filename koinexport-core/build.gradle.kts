import java.net.URI

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
    signing
    alias(libs.plugins.kotlin.dokka)
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    jvm()
}

val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)

val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

//region Fix Gradle warning about signing tasks using publishing task outputs without explicit dependencies
// https://github.com/gradle/gradle/issues/26091
tasks.withType<AbstractPublishToMaven>().configureEach {
    val signingTasks = tasks.withType<Sign>()
    mustRunAfter(signingTasks)
}

publishing {
    publications.withType<MavenPublication> {
        artifact(javadocJar)

        groupId = "io.github.mykhailo-liutov"
        version = "1.1"

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