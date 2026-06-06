pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(
        RepositoriesMode.FAIL_ON_PROJECT_REPOS
    )

    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://mobile.maven.couchbase.com/maven2/dev/")
        }
    }
}

rootProject.name = "Pokedecs"
include(":app")
include(":feature")
include(":core")
include(":data")
include(":domain")
