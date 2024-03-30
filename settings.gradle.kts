pluginManagement {
    repositories {
        maven(url = "https://github.com/jjoe64/GraphView")
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
        maven(url = "https://github.com/jjoe64/GraphView")
        google()
        mavenCentral()
    }
}

rootProject.name = "ReleGraphApp"
include(":app")
