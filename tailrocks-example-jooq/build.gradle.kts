plugins {
    id("publishing-conventions")
    id("org.flywaydb.flyway") version Versions.flyway
    id("nu.studer.jooq") version Versions.gradleJooqPlugin
}

buildscript {
    configurations["classpath"].resolutionStrategy.eachDependency {
        if (requested.group == "org.jooq") {
            useVersion(Versions.jooq)
        }
    }
}

version = Versions.tailrocksExample

dependencies {
    // FIXME rename with correct module name, for example tailrocks-payment-flyway-migrations
    // subprojects
    api(project(":tailrocks-example-flyway-migrations"))

    // jOOQ
    api("org.jooq:jooq:${Versions.jooq}")

    // PostgreSQL
    jooqGenerator("org.postgresql:postgresql:${Versions.postgresql}")
    api("org.postgresql:postgresql:${Versions.postgresql}")

    // FIXME replace with library which you prefer for Nullable and NonNull annotations
    // Spotbugs
    api("com.github.spotbugs:spotbugs-annotations:${Versions.spotbugs}")
}

val datasourceUsername = System.getenv("DATASOURCE_USERNAME") ?: "postgres"
val datasourcePassword = System.getenv("DATASOURCE_PASSWORD") ?: "root"
// FIXME replace table name tailrocks_example_dev with correct project name, for example tailrocks_payment_dev
val datasourceUrl = System.getenv("DATASOURCE_URL") ?: "jdbc:postgresql://127.0.0.1:19001/tailrocks_example_dev"
val datasourceDriver = "org.postgresql.Driver"

flyway {
    driver = datasourceDriver
    url = datasourceUrl
    user = datasourceUsername
    password = datasourcePassword
    locations = arrayOf("classpath:db/migration")
}

jooq {
    version.set(Versions.jooq)
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)

    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)

            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.INFO
                jdbc.apply {
                    driver = datasourceDriver
                    url = datasourceUrl
                    user = datasourceUsername
                    password = datasourcePassword
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        isOutputSchemaToDefault = true
                        excludes = listOf(
                            "flyway_schema_history",
                            "pg_stat_statements"
                        ).joinToString("|")
                        recordVersionFields = "version"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                        isGlobalSchemaReferences = false
                        isNonnullAnnotation = true
                        isNullableAnnotation = true
                        // FIXME replace with you preferred NonNull annotation if you use something
                        nonnullAnnotationType = "edu.umd.cs.findbugs.annotations.NonNull"
                        // FIXME replace with you preferred Nullable annotation
                        nullableAnnotationType = "edu.umd.cs.findbugs.annotations.Nullable"
                    }
                    target.apply {
                        // FIXME replace module name with correct one, for example com.tailrocks.payment.jooq
                        packageName = "com.tailrocks.example.jooq"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

tasks {
    flywayMigrate {
        dependsOn("processResources")
    }
    named<nu.studer.gradle.jooq.JooqGenerate>("generateJooq") {
        allInputsDeclared.set(true)
        dependsOn("flywayMigrate")
    }
    processResources {
        finalizedBy("generateJooq")
    }
    compileJava {
        dependsOn("generateJooq")
    }
}
