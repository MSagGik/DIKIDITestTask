enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "DIKIDITestTask"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

includeBuild("build-logic")

include(":app")
include(":feature-home")
include(":feature-catalog")
include(":feature-promotions")
include(":feature-myentries")
include(":feature-more")
include(":common-ui")
include(":common-utils")
include(":data-network")
include(":root-navigate")
include(":data-location")
include(":data-sp")
