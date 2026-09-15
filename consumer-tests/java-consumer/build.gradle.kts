plugins {
    java
}

dependencies {
    implementation("cloud.lingya:lingya-agents-sdk:0.3.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
