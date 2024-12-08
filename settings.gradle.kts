pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "MovieApp"

// App module
include(":app")

// Core modules
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:ui")

// Feature modules
include(":features:home")
include(":features:search")
include(":features:details")
include(":features:favorites")