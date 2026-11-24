# MeritPrep 2.0 — Master Product Specification

**Product:** MeritPrep 2.0  
**Platform:** Android-first, Kotlin and Jetpack Compose  
**Primary audience:** Nigerian candidates preparing for JAMB UTME and WAEC WASSCE  
**Delivery principle:** One coherent learning environment that takes a learner from first principles to independent examination performance.

## 1. Product intent

MeritPrep is not intended to be a question-bank wrapper or a conventional CBT timer. It is a complete academic preparation environment in which a learner can:

1. build prerequisite knowledge from zero;
2. understand concepts through clear explanations, worked examples and visual models;
3. explore processes through interactive simulations and virtual practical work;
4. practise with carefully written questions and feedback;
5. prepare for the different demands of JAMB and WAEC;
6. identify errors and repair misconceptions;
7. retrieve knowledge over time through spaced revision; and
8. use evidence from learning and assessment to plan the next study activity.

The core learning cycle is **Learn → Visualise → Explore → Practise → Assess → Correct → Retrieve → Reflect**.

## 2. Exam tracks and subject scope

The product must support two distinct examination tracks:

- **JAMB UTME:** subject combinations, objective questions, timed CBT, navigation, flagging, review, submission, scoring and topic-level feedback.
- **WAEC WASSCE:** objective practice, structured/theory responses, practical and alternative-to-practical preparation, diagrams, observations, tables, graphs, calculations and marking guidance.

The initial subject catalogue is:
- Biology
- Chemistry
- Physics
- Mathematics
- English Language
- Economics
- Government

The catalogue must not be confused with syllabus completion. Each subject requires its own current syllabus map, objectives, lesson sequence, assessment blueprint and content review. Biology is the first developed subject; the other six must be brought to equivalent coverage in a staged subject-by-subject programme.

## 3. Learning experience requirements

### 3.1 Beginner-to-mastery lessons

Every full lesson should:
- state the syllabus mapping and measurable learning outcomes;
- identify prerequisites and provide a short diagnostic check;
- define new terms before relying on them;
- explain concepts in plain language before introducing formal terminology;
- use accurate analogies and state where each analogy stops being useful;
- include Nigerian or familiar everyday examples when scientifically appropriate;
- show worked examples step by step, with units and reasoning;
- include clear labelled diagrams, process visuals or interactive models where they materially improve understanding;
- address common misconceptions explicitly;
- include guided practice, independent retrieval and formative checks;
- show how the concept appears in JAMB and WAEC question styles;
- finish with an exit check and suggested spaced review; and
- distinguish lesson completion from demonstrated mastery.

### 3.2 Visual teaching and accessibility

Use diagrams, animations and interactive visualisations to explain structure, sequence, cause-and-effect and abstract processes. Visuals must be scientifically accurate, labelled legibly, accessible on small Android screens and usable offline where feasible. Do not add decorative animations that obscure the learning objective. Provide text explanations for essential visual information.

### 3.3 Practical and simulation learning

The platform should provide safe, guided virtual practical activities across Biology, Chemistry and Physics. Each activity should state its aim, materials or simulated apparatus, variables, method, safety considerations, observations, expected data, interpretation and limitations. Learners should manipulate relevant variables and receive feedback on predictions and conclusions. Simulations are teaching models and must disclose simplifications.

WAEC practical preparation must include specimen observation and comparison, biological drawings, classification keys, food tests, microscopy, osmosis/diffusion investigations, experimental design, tables, graphs, calculations and interpretation. Chemistry and Physics practical work must be developed against their relevant syllabuses rather than inferred from Biology.

## 4. Assessment requirements

### 4.0 Placement and baseline diagnostics

A learner should be able to take a low-stakes starting-point check before following a study plan. The diagnostic should sample across the selected subject's syllabus topics, expose topic-level evidence and recommend where to begin. It must record unanswered items separately, avoid presenting a small diagnostic as a validated grade prediction, and feed errors into the learner's revision workflow. Diagnostic blueprints and sampling rules must be versioned as syllabus coverage expands.

### 4.1 Question-bank integrity

Each assessment item must have a stable ID, subject, topic/objective mapping, format, difficulty/cognitive demand, answer key or rubric, explanation, provenance, rights status, author, reviewer, version and publication status. Original practice questions must be labelled as original. An item may be labelled as an official past question only when its exam, year, paper, question number, source and reuse rights have been verified.

Question quality checks must confirm an unambiguous stem, one defensible correct answer where applicable, plausible distractors, valid answer key, appropriate difficulty and an explanation that teaches the concept.

### 4.2 JAMB CBT

A complete JAMB workflow must support configurable subject combinations and examination blueprints, timed sessions, randomised question and option order, question palette/navigation, answer selection, review flags, unanswered-item review, submission, scoring, topic breakdown, answer explanations and session recovery after app interruption. Timing and scoring must be versioned and based on the applicable official examination specification. Existing Biology mock timings are clearly labelled as MeritPrep practice settings, not official timings.

### 4.3 WAEC assessment

WAEC preparation must treat objective, theory and practical skills as distinct assessment modes. Theory responses need transparent marking points and acceptance of scientifically equivalent wording. Any automated or self-check scoring must be described as formative unless validated against an approved marking rubric. Practical preparation must assess observation, recording, drawing, calculations, interpretation, experimental design and safety—not merely multiple-choice recall.

## 5. Revision, planning and learner analytics

The product should include:
- topic-linked flashcards and spaced retrieval;
- a mistake notebook containing the learner's response, correct response, explanation and review status;
- personal notes and saved lessons;
- configurable study plans that prioritise weak, unattempted and overdue topics;
- progress across lessons, objective questions, theory and practical work;
- topic-level strengths and gaps, with evidence and sample sizes visible;
- transparent mastery criteria and no unsupported score guarantees.

A learner's activity, completion, objective accuracy, theory performance and practical performance must remain distinguishable. A single percentage must not imply complete exam readiness. Readiness estimates require validation and should not be introduced until adequate evidence and calibration exist.

## 6. Offline-first and platform architecture

Core learning content, glossary, diagrams, question packs, revision cards and local simulations should remain usable without a network connection. The local database is the source of immediate learner progress. Future synchronisation must handle conflicts, retries, versioning, duplicate events and interrupted uploads without losing progress.

Cloud accounts, cross-device synchronisation, remote content delivery and analytics are future platform capabilities, not current alpha features. They require a documented backend, authenticated APIs, server-side authorisation, encrypted transport, data export/deletion, retention rules, backups and independent security review. Never place service credentials or privileged secrets in the APK.

A teacher/admin content-management system is required for creating, editing, reviewing, approving, versioning, publishing, retiring and auditing lessons, questions, rubrics, diagrams and practical activities. It must support role-based permissions and a review trail. Published Android content should be versioned and downloadable as signed content packs.

## 7. Academic governance

Content status must be explicit:
- **DRAFT:** authored, not approved;
- **SUBJECT_REVIEW:** awaiting independent subject review;
- **REVISION_REQUIRED:** reviewer identified corrections;
- **APPROVED:** required review and provenance checks completed;
- **RETIRED:** withdrawn or superseded.

Every content object must retain its syllabus version, content version, source, rights/licence, author, reviewer, review date and decision. Do not describe draft or unverified content as academically approved. Review should be performed by qualified subject specialists and should include Nigerian examination context.

Syllabus mappings must be checked against current official JAMB IBASS and WAEC materials before each examination cycle. Third-party syllabus copies may help locate material but are not sufficient as the final authority where official materials are available.

## 8. Original APK audit and migration boundary

The original uploaded MeritPrep APK was inspected statically. Its package and embedded identifiers indicated an Android/Kotlin/Compose application with local database and Firebase-related libraries, and feature traces for CBT, lessons/classroom, question banks, analytics, profiles, calculators, virtual labs, spaced review, placement assessment, offline/cloud sync and text-to-speech. Database table names suggested user profiles, past questions, exam attempts, active CBT sessions, learning modules/chapters, lesson progress, theory attempts, notes and spaced-review cards.

This was a static inspection only. It did not establish runtime behaviour, complete source code, backend rules, content rights or academic correctness. No original source, backend or complete lab asset set should be claimed as recovered until separately extracted and verified. Rebuild features from documented requirements and verified assets; do not copy third-party assets or assume undocumented behaviour.

## 9. Current implementation baseline (alpha)

The repository currently contains a Kotlin/Compose Android application with:
- seven subject entry points, with complete syllabus coverage not yet available for all subjects;
- a 24-unit Biology syllabus map and 24 beginner-oriented lesson drafts;
- 240 original Biology objective practice questions;
- Biology glossary and process visuals;
- Biology JAMB-style and WAEC-style objective practice mocks;
- original WAEC-style Biology structured-response tasks with self-check guidance;
- Biology revision cards and a spaced-retrieval scheduler;
- a mistake notebook, personal notes, bookmarks and study planner;
- Biology practical-learning prototypes and introductory Physics/Chemistry simulations;
- local SQLite persistence and unit-tested learning/assessment logic;
- GitHub Actions unit tests and debug APK builds.

These are alpha implementations. Biology content is pending independent academic review. Original practice items are not official past questions. The current mastery indicator is based on objective-question evidence only. The app has no production account system, cloud synchronisation, teacher CMS, validated multi-subject JAMB session, complete WAEC practical assessment system or independent security approval.

## 10. Delivery sequence and acceptance gates

### Gate 1 — Stable Android foundation
- Maintain reproducible builds and passing unit tests.
- Test fresh installation and database upgrades.
- Add UI/navigation tests and accessibility checks.
- Keep a versioned release note and verified APK artifact for each accepted build.

### Gate 2 — Academic content governance
- Introduce provenance and review metadata for lessons, questions, diagrams and practical activities.
- Add reviewer workflows and prevent draft items from being represented as approved or official.
- Produce a subject-by-subject syllabus coverage matrix.

### Gate 3 — Complete Biology
- Review all 24 lesson drafts, 240 objective questions, theory tasks, flashcards, diagrams and practical prototypes.
- Expand practical activities into guided investigations with observation/data recording and interpretation.
- Add reviewed content versions and evidence-based learner feedback.

### Gate 4 — Remaining six subjects
- Verify each current syllabus.
- Map every objective to lesson, question, revision and relevant practical content.
- Build each subject from foundational teaching through exam-level application.
- Do not mark a subject complete while any required syllabus objective lacks approved learning and assessment coverage.

### Gate 5 — Full examination systems
- Implement JAMB subject combinations and versioned exam blueprints.
- Complete WAEC objective, theory and practical assessment workflows.
- Validate scoring, marking guidance, session recovery and result analytics.

### Gate 6 — Offline distribution and platform services
- Add downloadable, versioned content packs and safe update/rollback behaviour.
- Design and implement the teacher/admin CMS and authenticated sync service.
- Complete privacy, child-safety, security, backup and operational reviews before production release.

## 11. Non-negotiable quality rules

- Never sacrifice scientific accuracy for simplified wording.
- Never fabricate past questions, citations, syllabus requirements or official marking schemes.
- Never call a prototype a complete practical examination system.
- Never equate activity completion with mastery.
- Never claim a build, test, APK or feature is complete without verification.
- Preserve existing learner data during schema migrations.
- Keep the original `kayceenet/Merit` repository untouched; MeritPrep 2.0 is developed in its separate repository.


## Scientific calculator toolkit

The Android alpha includes an offline calculator workspace for common quantitative tasks across Mathematics, Physics and Chemistry:
- Mathematics: percentage and arithmetic mean.
- Physics: current from Ohm's law and speed.
- Physics/Chemistry: density.
- Chemistry: molar concentration and dilution using C₁V₁ = C₂V₂.

Calculation logic is separated from the Compose interface and covered by unit tests. Inputs are validated for zero denominators, non-positive physical quantities and invalid dilution conditions. Results show the equation, substituted working and output unit. These are focused learning tools, not a replacement for a general-purpose scientific calculator; further formula coverage is planned.


## Chemistry course development status

The Chemistry catalogue maps all 18 topic headings in the Chemistry PDF served by JAMB's Integrated Brochure and Syllabus System (IBASS). The source URL is recorded in `ChemistryCourse.kt`: https://ibass.jamb.gov.ng/static/media/Chemistry.9efd0b98043d56922aaa.pdf

All 18 topics now have beginner-oriented draft lessons with learning objectives, explanatory sections, worked examples or practical applications, misconception guidance and an understanding check:
1. Separation of mixtures and purification
2. Chemical combination and the mole concept
3. Kinetic theory of matter and gas laws
4. Atomic structure, periodicity and chemical bonding
5. Nuclear chemistry
6. Solubility
7. Environmental pollution
8. Acids, bases and salts
9. Oxidation and reduction
10. Electrolysis
11. Energy changes
12. Rates of chemical reaction
13. Chemical equilibrium
14. Non-metals and their compounds
15. Metals and their compounds
16. Organic compounds
17. Chemistry and industry
18. Astronomical chemistry

These are authored draft lessons, not academically approved material. The JAMB mapping must be checked against the live IBASS document at each exam-cycle update. WAEC Chemistry has a separate syllabus and practical/theory requirements; WAEC-specific alignment and independent review by a qualified Chemistry teacher remain outstanding.
 
## Chemistry formative practice

All 18 Chemistry topics have a bank of 90 original multiple-choice practice items (five per topic). Each item includes a topic ID, four options, an answer key, an explanation and explicit MeritPrep-original provenance. The bank is not represented as official JAMB or WAEC past questions.

A dedicated Chemistry practice screen supports topic-focused or mixed practice, answer feedback, local attempt recording and saving incorrect answers to the mistake notebook. The current practice bank is formative; it does not yet provide a full timed Chemistry CBT or a complete WAEC theory/practical assessment.
