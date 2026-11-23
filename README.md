# MeritPrep 2.0

MeritPrep is an Android-first learning and examination-preparation app for Nigerian students working towards JAMB UTME and WAEC WASSCE.

## Current development build

The current alpha is a working Android app built with Kotlin, Jetpack Compose and an on-device SQLite database.

### Implemented in the current codebase

- Home, Learn, Practice, Labs, Profile and Biology revision screens.
- Seven subject entry points, with starter lesson content for Biology, Chemistry, Physics, Mathematics, English Language, Economics and Government.
- A 24-unit Biology syllabus map, with an authored beginner-oriented lesson draft for every mapped unit.
- 240 original Biology multiple-choice practice questions across the 24 mapped units, with explanations and syllabus-topic attribution.
- Topic-focused Biology practice sessions and a full Biology question-bank mode, with shuffled questions and answer options.
- 198 Biology revision cards with an on-device Again / Hard / Good / Easy spaced-retrieval scheduler.
- Biology practical-learning prototypes for food-test interpretation and osmosis.
- Local recording of lesson completion, question attempts and revision schedules.
- Automated unit tests and a GitHub Actions debug-APK build.

## Important academic limitations

This is an actively developed alpha, not a completed or officially endorsed examination product.

- Biology lessons, question keys, flashcards and practical tasks still require independent review by qualified subject teachers.
- The 240 Biology questions are original practice items, not official JAMB or WAEC past questions.
- The syllabus map is a working mapping. It must be checked against the current official JAMB IBASS and WAEC materials before each examination cycle.
- Full timed JAMB CBT, WAEC theory marking, a complete practical examination system, cross-device synchronisation, teacher CMS and production security review remain in development.
- Other subjects currently have starter content, not complete syllabus coverage.

## Build locally

Open this repository in Android Studio with JDK 17 and Android SDK 35, or run:

```bash
gradle --no-daemon testDebugUnitTest
gradle --no-daemon assembleDebug
```

The debug APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

## Academic integrity and content governance

Every published lesson, diagram, question, answer key and practical rubric must have a stable identifier, syllabus mapping, source and rights record, content version, named author, independent reviewer and approval status. Never label generated or unverified questions as official past questions. Lesson completion is not the same as demonstrated mastery.

See:
- `docs/ACADEMIC_CONTENT_STANDARD.md`
- `docs/BIOLOGY_CURRICULUM_AND_TEACHING_STANDARD.md`
- `docs/PRODUCT_ROADMAP.md`
