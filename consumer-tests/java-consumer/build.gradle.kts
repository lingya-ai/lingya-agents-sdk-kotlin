plugins {
    java
}

dependencies {
    implementation("ai.lingya:lingya-agents-sdk:0.1.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
