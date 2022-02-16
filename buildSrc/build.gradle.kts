plugins {
    `kotlin-dsl`
    id("com.github.ben-manes.versions") version "0.42.0"
    id("com.diffplug.spotless") version "6.2.2"
}

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.3.0")
}
