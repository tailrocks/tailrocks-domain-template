plugins {
    id("maven-publish-conventions")
}

version = Versions.tailrocksExample

dependencies {
    // Flyway
    implementation("org.flywaydb:flyway-core:${Versions.flyway}")
}
