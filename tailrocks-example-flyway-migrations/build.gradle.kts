plugins {
    id("publishing-conventions")
}

version = Versions.tailrocksExample

dependencies {
    // Flyway
    implementation("org.flywaydb:flyway-core:${Versions.flyway}")
}
