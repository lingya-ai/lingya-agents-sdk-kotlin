plugins {
    java
}

dependencies {
    implementation("cloud.lingya:lingya-agents-sdk:0.4.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
