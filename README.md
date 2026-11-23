# MeritPrep 2.0

MeritPrep is an Android-first learning and examination-preparation app for Nigerian students working towards JAMB UTME and WAEC WASSCE.

## Current development build

The current alpha is a working Android app built with Kotlin, Jetpack Compose and an on-device SQLite database.

### Implemented in the current codebase

- Home, Learn, Practice, Labs, Profile and Biology revision screens.
- Seven subject entry points, with starter lesson content for Biology, Chemistry, Physics, Mathematics, English Language, Economics and Government.
- A 24-unit Biology syllabus map, with an authored beginner-oriented lesson draft for every mapped unit.
- A searchable offline Biology glossary with 57 syllabus-linked terms, plain-language definitions, examples and direct lesson links.
- Visual teaching aids for cell structure, photosynthesis, plant transport, food-chain energy flow, monohybrid inheritance, mammalian double circulation, nephron function, flowering-plant reproduction and natural selection; the cell diagram also supports organelle selection and explanations.
- 240 original Biology multiple-choice practice questions across the 24 mapped units, with explanations and syllabus-topic attribution.
- Topic-focused Biology practice sessions and a full Biology question-bank mode, with shuffled questions and answer options.
- A timed JAMB-style Biology CBT mock: 40 random questions, a 30-minute practice timer, question navigation, answer flags, answer review and locally persisted session state, including the current question position.
- A separate WAEC-style Biology objective mock: 50 random questions and a 60-minute MeritPrep practice timer, using the same original question bank and session controls. These are practice settings, not claims about official paper timing.
- Ten original WAEC-style Biology structured-response tasks, with model responses, self-check marking points and locally saved learner responses.
- 198 Biology revision cards with an on-device Again / Hard / Good / Easy spaced-retrieval scheduler.
- An offline mistake notebook that captures incorrect and unanswered practice/CBT responses, explains the correction and lets learners mark concepts as reviewed.
- A seven-day Biology study planner with configurable daily study targets, lesson/practice/flashcard activities, local completion tracking and shortcuts into the relevant screens.
- Biology practical-learning prototypes for food tests with prediction feedback, osmosis, microscope magnification, quadrat-based ecological sampling, a dichotomous classification key and a biological-drawing self-check.
- Introductory Physics and Chemistry simulations for Ohm's law and solution concentration.
- A learner profile with topic-level practice evidence and an objective-practice mastery indicator. The current threshold requires at least 10 attempts, 8 distinct questions, 2 study days and 80% correct.
- An adaptive seven-day study plan that prioritises weaker, unattempted and less recently studied topics using recorded learner evidence.
- Per-topic personal lesson notes and a saved-lessons list, plus local recording of lesson completion, question attempts, revision schedules, theory responses, mistake-notebook entries and active CBT session answers.
- Automated unit tests and a GitHub Actions debug-APK build.

## Important academic limitations

This is an actively developed alpha, not a completed or officially endorsed examination product.

- Biology lessons, question keys, flashcards, glossary entries, structured-response guides, diagrams and practical tasks still require independent review by qualified subject teachers.
- The 240 Biology objective questions and ten structured-response tasks are original practice content, not official JAMB or WAEC past questions.
- Theory self-check rubrics are learning aids, not official WAEC marking schemes or examiner marking.
- The Biology CBT mock is a MeritPrep practice configuration, not an official JAMB paper or a full multi-subject JAMB simulation.
- The syllabus map is a working mapping. It must be checked against the current official JAMB IBASS and WAEC materials before each examination cycle.
- The mastery indicator currently uses objective-question evidence only; it does not yet include WAEC theory, practical performance or a validated prediction model. A complete WAEC practical examination system, cross-device synchronisation, teacher CMS and production security review remain in development.
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
- `docs/USER_GUIDE.md`
- `docs/PRIVACY_AND_DATA_HANDLING.md`
- `docs/ACADEMIC_CONTENT_STANDARD.md`
- `docs/BIOLOGY_CURRICULUM_AND_TEACHING_STANDARD.md`
- `docs/PRODUCT_ROADMAP.md`
- `docs/ACADEMIC_REVIEW_CHECKLIST.md`
- `docs/CBT_PRACTICE_SPEC.md`
- `docs/MISTAKE_NOTEBOOK_SPEC.md`
- `docs/STUDY_PLANNER_SPEC.md`
- `docs/LOCAL_DATABASE_SCHEMA.md`
