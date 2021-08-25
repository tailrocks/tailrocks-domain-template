plugins {
    java
}

val javaVersion = 16

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

allprojects {
    apply(plugin = "idea-conventions")
    apply(plugin = "dependency-updates-conventions")
    apply(plugin = "spotless-conventions")

    // FIXME replace com.tailrocks.domain with your company prefix, for example com.mycompany.domain
    group = "com.tailrocks.domain"
}
