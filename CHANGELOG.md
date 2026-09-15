# Changelog

## 0.3.0 - 2026-09-15

- Bind `channelId` once and expose all 46 operations through grouped coroutine and Java blocking facades.
- Retain generated Retrofit APIs as a deprecated low-level migration path until 1.0.
- Generate the public facades from the contract operation manifest.

## 0.2.0 - 2026-09-15

- Move the public Kotlin package from `ai.lingya.agents.sdk` to `cloud.lingya.agents.sdk` so the source namespace matches the verified `lingya.cloud` Maven namespace.
- Keep the Maven coordinates at `cloud.lingya:lingya-agents-sdk`.

## 0.1.0 - 2026-09-14

- Add generated coroutine APIs and models for all 46 public Agent channel operations.
- Add HMAC-SHA256-V1 request signing and user-scoped clients.
- Add a streaming SSE API with forward-compatible unknown event handling.
- Align generated Agent configuration models with contract 0.1.1 and the deployed service response.
- Add opt-in live verification for signed paging, gateway SSE, and a cleaned-up real chat lifecycle.
- Assert live coverage of all 46 published method/path pairs, including explicit domain errors for state-dependent resources.
- Align the upload example and generated contract with contract 0.1.2's registered `ai-chat-attachments` module.
