# Contributing

Run `./gradlew clean check` before opening a pull request. The contents of `generated/` must only be changed through `./gradlew generateSdk` using the pinned contract.

Generated code and the committed contract snapshot must remain reproducible. Runtime changes require focused signing, HTTP, and SSE tests.
