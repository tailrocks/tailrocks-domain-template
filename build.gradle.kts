plugins {
    java
    idea
    `maven-publish`
    id("com.diffplug.spotless")
}

val javaVersion = 16

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

allprojects {
    apply(plugin = "idea")

    apply(plugin = "dependency-updates-conventions")
    apply(plugin = "spotless-conventions")

    // FIXME replace com.tailrocks.domain with your company prefix, for example com.mycompany.domain
    group = "com.tailrocks.domain"

    idea {
        module {
            isDownloadJavadoc = false
            isDownloadSources = false
        }
    }
}

subprojects {
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion))
        }

        withJavadocJar()
        withSourcesJar()
    }
}
