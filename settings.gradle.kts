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

<<<<<<< HEAD
rootProject.name = "NumberGuessGame"
include(":app")
 
=======
rootProject.name = "My Application"
include(":app")
include(":w04")
include(":w05")
include(":w06")
>>>>>>> f62de2ccc23b3665de9fc7e8b1a5724b787aa99a
