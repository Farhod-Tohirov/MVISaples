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

rootProject.name = "MVISaples"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":mavericks")
include(":data")
include(":ui")
include(":mvikotlin")
include(":orbit")
include(":redux-kotlin")
include(":flow-mvi")
