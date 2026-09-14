pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.aliyun.com/repository/public")
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/public")
        mavenCentral()
    }
}

rootProject.name = "lingya-agents-sdk-consumer-tests"
include("kotlin-consumer", "java-consumer")
