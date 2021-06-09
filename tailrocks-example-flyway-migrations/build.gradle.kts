plugins {
    `java-library`
    `maven-publish`
}

version = Versions.tailrocksExample

dependencies {
    // Flyway
    implementation("org.flywaydb:flyway-core:${Versions.flyway}")
}
