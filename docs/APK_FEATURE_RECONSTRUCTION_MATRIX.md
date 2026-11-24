# Original APK feature reconstruction matrix

This document keeps the static APK audit separate from verified implementation. It is a migration tracker, not proof that the original app's source code, database, assets or backend have been recovered.

## Evidence boundary

The uploaded MeritPrep APK was inspected statically. Package metadata, embedded identifiers, library traces, database table names and feature strings suggested the capabilities below. The APK was not dynamically exercised, fully decompiled into a buildable source project, or connected to its original backend. Feature traces are evidence of intended or embedded functionality, not confirmation that every feature worked in production.

## Feature-by-feature status

| Original APK trace | MeritPrep 2.0 status | Evidence and remaining work |
|---|---|---|
| Lesson classroom / learning modules and chapters | Reconstructed | Jetpack Compose lesson catalogue and lesson screens exist. Biology has 24 beginner-oriented lesson drafts; the other six subjects have starter content only. Original lesson source and media were not recovered. |
| Question banks / past questions | Partly reconstructed | 240 original Biology objective items and WAEC-style theory tasks are in the new repository. They are not official past questions. Verified past-paper provenance, rights and broader subject banks remain outstanding. |
| JAMB CBT / active sessions | Reconstructed for Biology practice | Timed Biology JAMB-style and WAEC-style objective mocks include shuffled items/options, navigation, flags, scoring, explanations and local session recovery. A configurable multi-subject JAMB combination and official blueprint are not yet implemented. |
| Profiles and analytics | Local learner evidence | Profile screen reports local lesson and objective-practice activity, topic evidence and unresolved mistakes. No account profile, remote analytics or validated grade/readiness prediction is implemented. |
| Calculators | Not yet implemented as a dedicated toolkit | Some lab simulations contain calculations (for example, Ohm's law and solution concentration). A standalone scientific calculator and subject-specific calculator suite remain to be built and tested. |
| Virtual laboratories and simulations | Reconstructed prototypes | Biology food tests, osmosis, microscope magnification, quadrat sampling, classification and drawing activities plus introductory Physics/Chemistry simulations exist. They are new models, not recovered original lab assets. Each needs subject review and fuller experimental observation/data workflows. |
| Spaced review cards | Reconstructed | Biology revision cards and an on-device Again/Hard/Good/Easy scheduler exist. The original review-card data and scheduling implementation were not recovered. |
| Placement assessment | Reconstructed | A low-stakes Biology baseline diagnostic samples one original question per mapped topic, reports topic evidence and feeds errors into the mistake notebook. It is not a validated exam-grade predictor. |
| Offline learning | Implemented locally | Core lesson content, questions, notes, bookmarks, progress, revision and simulations are stored or available on-device. Content-pack download/update and export/import remain future work. |
| Cloud sync / Firebase-related traces | Not implemented | The APK contained Firebase-related library indicators, but no backend project, security rules, API contract or credentials were recovered. The current alpha intentionally remains local-first and requests no network permission. Sync must wait for a privacy/security design and tested backend. |
| Text-to-speech | Reconstructed | Lessons can be read aloud using the Android device's installed TTS voice. This is a new local implementation; the original speech code/assets were not recovered. |
| Database entities / learner records | Reconstructed selectively | The new SQLite schema stores lesson completion, attempts, CBT state, mistakes, notes, revision schedules and study plans. Original schema and user records have not been migrated. |
| Admin/content publishing | Not implemented | Content provenance and publication rules are documented and unit-tested. A role-based teacher/admin CMS, reviewer interface, audit history and signed content packs remain future work. |

## Migration rules

1. Preserve the separate kayceenet/MeritPrep-2.0 repository. Do not modify kayceenet/Merit.
2. Recover original assets only from files that can be extracted, identified, checked for rights and tested. Otherwise rebuild the feature and label it reconstructed.
3. Do not claim original Firebase services, data, question papers, diagrams, labs or lesson content have been recovered without verifiable source evidence.
4. Do not label original practice questions as official past questions.
5. Keep offline learning usable without accounts or network access.
6. Before adding accounts or sync, complete a threat model, privacy notice, server-side authorisation, secure transport, data export/deletion and independent security review.
7. Mark a subject complete only after its current syllabus objectives have mapped lessons, practice, revision and relevant practical/theory assessment, followed by independent subject review.

## Next implementation priorities

1. Complete the Biology placement diagnostic build and add navigation/UI regression coverage.
2. Build a dedicated calculator toolkit for Mathematics, Physics and Chemistry.
3. Add reviewed question-bank provenance and a subject-by-subject content coverage matrix.
4. Expand WAEC practical workflows to record observations, data, drawings, graphs and interpretation.
5. Develop Chemistry as the next complete subject after Biology, then Physics, Mathematics, English Language, Economics and Government.
6. Plan the teacher/admin CMS and content-pack delivery before any cloud learner synchronisation.
