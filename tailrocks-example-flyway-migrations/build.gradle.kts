plugins {
    `java-library`
}

version = Versions.tailrocksExample

dependencies {
    // Flyway
    implementation("org.flywaydb:flyway-core:${Versions.flyway}")
}
