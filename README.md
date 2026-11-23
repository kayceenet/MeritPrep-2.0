# MeritPrep 2.0

An Android-first learning and examination preparation platform for Nigerian students preparing for JAMB UTME and WAEC WASSCE.

## Current alpha foundation
- Kotlin + Jetpack Compose Android app
- Home, Learn, Practice and Profile navigation
- Seven subject entry points
- Starter topic catalogues
- Demonstration practice question with feedback
- GitHub Actions debug APK build

## Build
Open this repository in Android Studio with JDK 17 and Android SDK 35, or run:

```bash
gradle assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`.

## Academic integrity
Starter topics and the demonstration question are scaffolding, not a complete or officially verified syllabus/question bank. Production content must be mapped to current official JAMB and WAEC syllabuses, sourced, independently reviewed, versioned and approved before publication.

## Roadmap
1. Curriculum and content data layer with syllabus coverage matrix.
2. Offline persistence and download/synchronisation.
3. Full CBT engine and WAEC objective/theory/practical workflows.
4. Mastery, spaced retrieval and study planning.
5. Virtual labs, simulation inventory and accessibility.
6. Secure backend and academic content management system.

See `docs/PRODUCT_ROADMAP.md` for the staged delivery plan.
