plugins {
    `kotlin-dsl`
    idea
    id("com.github.ben-manes.versions") version "0.39.0"
}

repositories {
    mavenLocal()
    gradlePluginPortal()
    mavenCentral()
}

idea {
    module {
        isDownloadJavadoc = false
        isDownloadSources = false
    }
}

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.39.0")
}
