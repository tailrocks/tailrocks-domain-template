pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

// FIXME rename with project name, company-name plus domain and remove -example suffix, for example tailrocks-domain
rootProject.name = "tailrocks-domain-example"

// FIXME rename modules name and don't forget rename the folders as well
include(
    ":tailrocks-example-flyway-migrations",
    ":tailrocks-example-jooq"
)
