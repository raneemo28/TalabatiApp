# طلباتي (Talabati)

A native Android application, built with Kotlin and Gradle (Kotlin DSL).

> This repo is currently an early-stage project scaffold — the structure below reflects what's tracked so far (Gradle setup, package skeleton). Fill in the sections marked with `_TODO_` once the app's features take shape.

## Tech Stack

- Kotlin
- Gradle (Kotlin DSL — `build.gradle.kts`, `settings.gradle.kts`)
- Android SDK
- Package: `com.alissar.myapplication`

## Project Structure

```
TalabatiApp/
├── app/                                # App module
├── java/com/alissar/myapplication/     # Application source
├── res/                                # Android resources (layouts, drawables, strings)
├── gradle/                             # Gradle wrapper files
├── build.gradle.kts                    # Top-level build config
├── settings.gradle.kts                 # Gradle project settings
├── gradlew / gradlew.bat               # Gradle wrapper scripts
└── local.properties                    # Local SDK path (not committed)
```

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (recent stable version)
- Android SDK (installed via Android Studio)
- JDK 17+

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/raneemo28/TalabatiApp.git
   cd TalabatiApp
   ```
2. Open the project in Android Studio.
3. Let Gradle sync complete (or run `./gradlew build` from the command line).
4. Run the app on an emulator or connected device via **Run ▶ Run 'app'**.

## About the App

_TODO — what does Talabati do? (e.g. what "orders/requests" flow it manages, who it's for, key screens/features)._

## License

_TODO — add a license if you intend to open-source this._
