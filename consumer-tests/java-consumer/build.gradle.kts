plugins {
    java
}

dependencies {
    implementation("cloud.lingya:lingya-agents-sdk:1.0.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
