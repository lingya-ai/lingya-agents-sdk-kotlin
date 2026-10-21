# Kotlin SDK 发布指南

## 契约同步

契约、运行时和生成输入的唯一源是 `lingya-agents-openapi`。先在契约仓库修改 `openapi/`、`sdk/kotlin/` 中对应的源文件、模板或配置，再运行 `npm ci && npm run generate:sdks`；`npm run sync:sdks` 是兼容入口。该流程统一生成契约快照、传输清单、模型/API、运行时和业务门面，并更新 `SDK_GENERATION.json`，无需在 SDK 仓库单独执行旧生成命令。

生成管理的文件不要直接修改。普通构建使用已提交源码，不触发重新生成。生成检查使用固定契约 tag，验证全部受管理文件及连续生成的一致性；升级时同步生成工作流中的契约引用。详见 `docs/request-lifecycle.md`。

## 版本与检查

在契约仓库 `sdk/kotlin/` 更新 `gradle.properties`，统一重新生成；同步 SDK README 依赖示例和 CHANGELOG。同步更新 `consumer-tests/java-consumer/build.gradle.kts` 与 `consumer-tests/kotlin-consumer/build.gradle.kts` 中的 SDK 版本。运行 `./gradlew clean check`，再运行 `./gradlew publishToMavenLocal` 和 `./gradlew -p consumer-tests clean build`，确认 Java/Kotlin consumer 可编译。

## 发布

提交并推送到 `main`，确认所有 Java 矩阵 CI 和 consumer 检查通过后创建并推送 `vX.Y.Z` tag。GitHub Actions 会运行 `clean check`，使用 Maven Central 凭证与签名密钥发布并创建 GitHub Release；按需批准 `release` 环境。Central Portal 发布成功后，等 Maven Central 索引完成，再确认 POM 与 JAR 可访问。
