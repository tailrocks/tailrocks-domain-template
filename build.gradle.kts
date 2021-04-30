plugins {
    java
    idea
    `maven-publish`
    id("com.diffplug.spotless") version Versions.gradleSpotlessPlugin
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

allprojects {
    apply(plugin = "idea")
    apply(plugin = "com.diffplug.spotless")

    apply(from = "${project.rootDir}/gradle/dependencyUpdates.gradle.kts")

    idea {
        module {
            isDownloadJavadoc = false
            isDownloadSources = false
        }
    }

    spotless {
        java {
            removeUnusedImports()
            trimTrailingWhitespace()
            endWithNewline()
            targetExclude("**/generated/**")
        }
        kotlinGradle {
            ktlint()
        }
    }

    tasks.withType<JavaCompile> {
        options.release.set(16)
    }
}

// FIXME add module name here
val publishingProjects = setOf(
    "tailrocks-example-flyway-migrations",
    "tailrocks-example-jooq"
)

subprojects {
    apply(plugin = "java")
    if (publishingProjects.contains(project.name)) {
        apply(plugin = "java-library")
        apply(plugin = "maven-publish")
    }

    // FIXME replace com.tailrocks.domain with your company prefix, for example com.mycompany.domain
    group = "com.tailrocks.domain"

    java {
        sourceCompatibility = JavaVersion.VERSION_16
        targetCompatibility = JavaVersion.VERSION_16

        withJavadocJar()
        withSourcesJar()
    }

    if (publishingProjects.contains(project.name)) {
        publishing {
            publications {
                create<MavenPublication>("mavenJava") {
                    from(components["java"])
                    versionMapping {
                        allVariants {
                            fromResolutionResult()
                        }
                    }
                }
            }
        }
    }
}
