pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KoinKmpExport"
include(":app")
include(":koinexport-core")
include(":koinexport-processor")
include(":sample")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")