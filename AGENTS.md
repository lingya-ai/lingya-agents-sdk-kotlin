# Kotlin SDK 发布指南

## 契约同步

先确认 `lingya-agents-openapi` 已发布所需的 `vX.Y.Z`，再同步 `openapi/lingya-agents-v1.yaml` 和 `openapi/endpoints.json`。运行 `./gradlew generateSdk generateBoundApi`；生成接口、模型和绑定 facade 都应与契约一致。

## 版本与检查

更新 `gradle.properties`、README 依赖示例和 CHANGELOG。同步更新 `consumer-tests/java-consumer/build.gradle.kts` 与 `consumer-tests/kotlin-consumer/build.gradle.kts` 中的 SDK 版本。运行 `./gradlew clean check`，再运行 `./gradlew publishToMavenLocal` 和 `./gradlew -p consumer-tests clean build`，确认 Java/Kotlin consumer 可编译。

## 发布

提交并推送到 `main`，确认所有 Java 矩阵 CI 和 consumer 检查通过后创建并推送 `vX.Y.Z` tag。GitHub Actions 会运行 `clean check`，使用 Maven Central 凭证与签名密钥发布并创建 GitHub Release；按需批准 `release` 环境。Central Portal 发布成功后，等 Maven Central 索引完成，再确认 POM 与 JAR 可访问。
