import java.net.URI

plugins {
    kotlin("jvm")
    `maven-publish`
    signing
    alias(libs.plugins.kotlin.dokka)
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
    publications.create<MavenPublication>("Maven"){
        artifact(javadocJar)

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