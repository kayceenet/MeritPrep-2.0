# MeritPrep 2.0

MeritPrep is an Android-first learning and examination-preparation app for Nigerian students working towards JAMB UTME and WAEC WASSCE.

## Current development build

The current alpha is a working Android app built with Kotlin, Jetpack Compose and an on-device SQLite database.

### Implemented in the current codebase

- Home, Learn, Practice, Labs, Profile and Biology revision screens.
- Seven subject entry points, with starter lesson content for Biology, Chemistry, Physics, Mathematics, English Language, Economics and Government.
- A 24-unit Biology syllabus map, with an authored beginner-oriented lesson draft for every mapped unit.
- An 18-topic JAMB Chemistry syllabus map based on the Chemistry PDF served through JAMB IBASS. All 18 mapped topics now have beginner-oriented lesson drafts: separation and purification; chemical combination and the mole concept; kinetic theory and gas laws; atomic structure and bonding; nuclear chemistry; solubility; environmental pollution; acids, bases and salts; oxidation and reduction; electrolysis; energy changes; rates of chemical reaction; chemical equilibrium; non-metals and their compounds; metals and their compounds; organic compounds; chemistry and industry; and astronomical chemistry. All 18 topics have five original, topic-mapped Chemistry practice questions, for 90 questions in total with explanations, local attempt tracking and incorrect-answer capture in the mistake notebook. These are not labelled as official past questions. A separate Nigeria-specific WAEC Chemistry working coverage map has been added in `docs/WAEC_CHEMISTRY_COVERAGE_MAP.md`. The Chemistry alpha now includes a timed 30-question WAEC-style objective assessment, interactive titration and selected cation-test simulations, a qualitative analysis reference for seven anions and seven gases, a redox titration calculator for iron(II) using the 1:5 permanganate reaction, and six original structured theory tasks. The syllabus source and all draft academic content still require verification against current WAEC-issued materials and independent Chemistry-teacher review before approval.
- A searchable offline Biology glossary with 57 syllabus-linked terms, plain-language definitions, examples and direct lesson links. The spaced-revision queue now also includes 25 Physics recall cards across the first eight developed Physics topics.
- Visual teaching aids for cell structure, photosynthesis, plant transport, food-chain energy flow, monohybrid inheritance, mammalian double circulation, nephron function, flowering-plant reproduction and natural selection; the cell diagram also supports organelle selection and explanations.
- 240 original Biology multiple-choice practice questions across the 24 mapped units, with explanations and syllabus-topic attribution.
- Topic-focused Biology practice sessions and a full Biology question-bank mode, with shuffled questions and answer options.
- A Biology placement diagnostic that samples one original question from each of the 24 syllabus topics, reports topic-level starting evidence, records attempts locally and sends incorrect or unanswered items to the mistake notebook. It is a study-orientation tool, not a grade predictor.
- A timed JAMB-style Biology CBT mock: 40 random questions, a 30-minute practice timer, question navigation, answer flags, answer review and locally persisted session state, including the current question position.
- A separate WAEC-style Biology objective mock: 50 random questions and a 60-minute MeritPrep practice timer, using the same original question bank and session controls. These are practice settings, not claims about official paper timing. Both mocks provide per-topic performance breakdowns and answer explanations.
- Ten original WAEC-style Biology structured-response tasks, with model responses, self-check marking points and locally saved learner responses.
- 198 Biology revision cards with an on-device Again / Hard / Good / Easy spaced-retrieval scheduler.
- An offline mistake notebook that captures incorrect and unanswered practice/CBT responses, explains the correction and lets learners mark concepts as reviewed.
- A seven-day Biology study planner with configurable daily study targets, lesson/practice/flashcard activities, local completion tracking and shortcuts into the relevant screens.
- Biology practical-learning prototypes for food tests with prediction feedback, osmosis, microscope magnification, quadrat-based ecological sampling, a dichotomous classification key and a biological-drawing self-check.
- Physics virtual-lab models for Ohm's law, uniformly accelerated motion, perpendicular vector addition, the principle of moments and simple-machine efficiency; the calculation logic for motion, vectors, moments and machine performance is unit-tested. Chemistry concentration, titration and cation-analysis simulations remain available.
- A 39-topic JAMB Physics syllabus map based on the Physics document served through JAMB IBASS. The first eight topics (measurements and units, scalars and vectors, motion, gravitational field, equilibrium of forces, work/energy/power, friction and simple machines) have beginner-focused lesson drafts and 40 original topic-mapped practice questions. The remaining 31 entries are syllabus outlines only. Physics practice records attempts locally and captures incorrect answers in the mistake notebook. All Physics content is draft and requires independent subject-teacher review. See `docs/PHYSICS_CURRICULUM_AND_COVERAGE_MAP.md`.
- A dedicated offline scientific calculator workspace linked from Home, with tested tools for percentage, arithmetic mean, Ohm's law current, speed, density, heat energy (Q = mcΔT), molar concentration, dilution, moles from mass, Boyle's law, Charles's law, pH from hydrogen-ion concentration and Faraday-law electrolysis mass. Each result displays its formula, working and units, with input validation. Gas-law temperatures are entered in kelvin; electrolysis assumes constant current and 100% current efficiency.
- Offline lesson read-aloud using the Android device's installed text-to-speech voice, with stop control and lifecycle cleanup. This is a reconstructed accessibility feature based on static APK feature traces; the original speech implementation was not recovered.
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
- Chemistry has a full 18-topic JAMB map with beginner-oriented draft lessons and 90 original practice questions. The content is not academically approved; independent subject-teacher review and WAEC-specific alignment remain outstanding. Physics now has a 39-topic JAMB map, eight lesson drafts and 40 original practice questions; the remaining 31 topics are outlines. Mathematics, English Language, Economics and Government still have starter content, not complete syllabus coverage.

## Build locally

Open this repository in Android Studio with JDK 17 and Android SDK 35, or run:

```bash
gradle --no-daemon testDebugUnitTest
gradle --no-daemon assembleDebug
```

The debug APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

## Complete product requirements

See `docs/MASTER_PRODUCT_SPEC.md` for the consolidated requirements recovered from the previous project discussions: the full JAMB/WAEC learning journey, all seven subjects, beginner-to-exam teaching standard, simulations and virtual practicals, assessment integrity, offline-first design, content management, academic review, privacy, security and staged acceptance gates.

The codebase now includes a tested academic content publication policy (`ContentGovernance.kt`) that blocks publication when provenance, rights, independent review or reviewer/date metadata is missing. This is a domain-level safeguard; persistence, editor screens and the teacher/admin CMS are still to be implemented.

## Academic integrity and content governance

Every published lesson, diagram, question, answer key and practical rubric must have a stable identifier, syllabus mapping, source and rights record, content version, named author, independent reviewer and approval status. Never label generated or unverified questions as official past questions. Lesson completion is not the same as demonstrated mastery.

See:
- `docs/USER_GUIDE.md`
- `docs/PRIVACY_AND_DATA_HANDLING.md`
- `docs/ACADEMIC_CONTENT_STANDARD.md`
- `docs/BIOLOGY_CURRICULUM_AND_TEACHING_STANDARD.md`
- `docs/PRODUCT_ROADMAP.md`
- `docs/MASTER_PRODUCT_SPEC.md`
- `docs/APK_FEATURE_RECONSTRUCTION_MATRIX.md`
- `docs/ACADEMIC_REVIEW_CHECKLIST.md`
- `docs/WAEC_CHEMISTRY_COVERAGE_MAP.md`
- `docs/CBT_PRACTICE_SPEC.md`
- `docs/MISTAKE_NOTEBOOK_SPEC.md`
- `docs/STUDY_PLANNER_SPEC.md`
- `docs/LOCAL_DATABASE_SCHEMA.md`
