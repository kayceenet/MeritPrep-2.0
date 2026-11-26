# MeritPrep 2.0 — Application Structure and Interaction Specification

**Status:** Target architecture and implementation contract  
**Platform:** Android-first; Kotlin, Jetpack Compose  
**Parent specification:** [Master Product Specification](MASTER_PRODUCT_SPEC.md)  
**Repository:** `kayceenet/MeritPrep-2.0`

This document records the application structure supplied during the November 2026 design review. It is the reference for the app-wide navigation model, classroom layout, screen composition and interaction behaviour. It describes the intended product architecture; it does not claim that every screen or interaction is already implemented.

## 1. Architecture principles

1. Preserve the existing Kotlin/Jetpack Compose architecture and working features. Extend and refactor incrementally; do not replace functioning systems without a verified reason.
2. Treat the application as one connected learning environment, not a collection of unrelated tools.
3. Use responsive layouts, with a 720.dp width breakpoint as the initial design reference. Verify the breakpoint and layouts on actual phones, tablets, landscape and large-font configurations.
4. Keep navigation chrome stable while the learner reads or works. Keep lesson content independently scrollable.
5. Make controls functional and stateful. No decorative buttons, simulated persistence or success messages unsupported by actual operations.
6. Support offline-first learning. Local content, progress, notes and core simulations must remain available without a network connection where technically feasible.
7. Do not impose arbitrary product limits on lesson depth, syllabus coverage, question-bank size, examples, practice, simulations, supported learning activities or content packs. Scale through pagination, lazy rendering, streaming, modular content packs and resource-aware loading rather than arbitrary truncation.
8. Retain necessary safeguards: scientific and academic review, copyright and licence compliance, privacy, security, data integrity, accessibility, device compatibility and safe handling of external resources.
9. Distinguish target design from implementation status. A screen, simulator, integration or workflow is complete only after code inspection and relevant tests demonstrate that it works.
10. Preserve the separate original `kayceenet/Merit` repository. All MeritPrep 2.0 work belongs in `kayceenet/MeritPrep-2.0`.

## 2. App-wide shell and navigation

### 2.1 Phone layout (< 720.dp)

The phone shell uses a vertically arranged content area and a docked bottom navigation bar. The four primary destinations flank a raised central gradient launcher orb:

- Materials
- Syllabus
- Exam Lab
- Profile

The central launcher opens `QuickStudyLauncherSheet` with shortcuts to:
- Timed CBT
- 180-question UTME
- Recall Cards
- Notebook
- Paper 2
- SS3 Placement

The phone shell must account for system bars, keyboard visibility, scrolling, small screens and accessibility font scaling. The launcher must not obscure essential content or system navigation.

### 2.2 Tablet layout (>= 720.dp)

Use a left navigation rail with five destinations:

- Materials
- Syllabus
- Exam Lab
- Notebook
- Profile

The rail remains anchored while the selected screen occupies the remaining content area. The layout must adapt gracefully at the breakpoint rather than abruptly clipping or compressing content.

### 2.3 Lesson classroom overlay

Opening a lesson replaces the normal app navigation chrome with an edge-to-edge `LessonClassroomScreen`. The classroom handles status-bar and navigation-bar insets itself. Back navigation returns to the previous app destination and restores its state.

## 3. Lesson classroom

The classroom is organised into three structural zones:

1. Fixed top chrome.
2. Responsive six-pillar mode/section switcher.
3. Scrollable active-pillar body, with a persistent bottom classroom footer.

The active pillar's body scrolls independently. Navigation controls must remain reachable and must not cover the final content.

### 3.1 Fixed top chrome

The classroom top bar contains:

- Back button.
- Lesson title.
- Syllabus mapping/progress tag, for example `X/6 sections mastered`.
- Conditional scientific calculator shortcut.
- Save Note action.
- Top-right menu and display settings.

A 4.dp mastery progress strip sits directly beneath the top bar.

A conditional lock banner explains why a pillar is unavailable, or what verification is required before the learner can proceed. Lock messages must explain the actual prerequisite and must not be used to impose arbitrary restrictions.

### 3.2 Six-pillar navigation

The six pillars are:

1. **Concepts & Theory**
2. **Worked Examples**
3. **Applications & Notes**
4. **Virtual Lab**
5. **Applied Project**
6. **Mastery Quiz**

Completion, active, available and locked states must be visually distinguishable and accessible to assistive technology.

#### Tablet behaviour

On tablets, show a permanent 268.dp left sidebar labelled **SECTIONS**, listing all six pillars vertically with active, completion and lock indicators. The active pillar body occupies the remaining classroom width.

#### Phone behaviour

At the top of a lesson (scroll position <= 56.dp), show a 3-by-2 grid of action tiles:

- Concepts
- Examples
- Context
- Virtual Lab
- Project
- Quiz

When the learner scrolls beyond 56.dp, smoothly collapse the grid into a compact horizontal pill strip using an animated visibility transition. A manual Compact / Modes chip lets the learner toggle the presentation. Scrolling close to the top automatically restores the expanded grid.

The selected pillar, completion state and lock state must remain consistent across expanded and compact presentations. Do not reset a learner's place in a pillar when switching modes.

### 3.3 Pillar body conventions

Each pillar begins with a title row and text-to-speech (TTS) controls:

- Listen / Stop.
- Pause / Resume when playback is active.
- Speech-speed selection from 0.6x to 1.5x when active.

TTS must use the device's available speech service, expose accessible control labels, stop cleanly when the learner leaves the screen and handle missing language voices gracefully.

#### Pillar 1 — Concepts & Theory

Present content in a deliberate beginner-to-advanced teaching sequence:

1. Prerequisites card: foundational knowledge required and a short dependency explanation.
2. Learning outcomes card: measurable outcomes for the lesson.
3. Key terms card: term, core meaning, definition and examination usage, separated clearly.
4. Topic cards, numbered by topic, each containing:
   - Topic number/badge and section-level Listen / Stop.
   - Heading and plain-language scientific explanation using the shared scientific text renderer.
   - A topic-matched concept animation or interactive visual model.
   - A clear explanation of why and how the process works.
   - Equation, mathematical expression or chemical formula where relevant, with a one-tap Save to Notebook action.
   - Inline quick checks and interactive micro-drills with immediate explanatory feedback.
   - JAMB / WAEC / NECO examination focus.
   - Examiner tip.
   - Short checkpoint recap.

Teach prerequisite ideas before depending on them. Introduce formal terminology after the learner has a plain-language mental model. Explain the limits of analogies and distinguish established facts from simplified models.

#### Pillar 2 — Worked Examples

Worked-example cards contain:

- Exam/source classification. Identify original MeritPrep practice material accurately; do not label it an official past question without verified provenance and rights.
- TTS control.
- Title and problem statement.
- An initial paper-first attempt prompt.
- Progressive solution reveal, with numbered steps and Reveal All / Hide Solution.
- Clearly presented final answer.
- Common-trap warning explaining likely errors.

A guided-practice card includes difficulty and suggested time, the prompt, progressive Hint 1/3 reveal and a toggle for the full solution strategy and answer. Hints should scaffold reasoning rather than disclose the answer immediately.

#### Pillar 3 — Applications & Notes

Include:

- Real-life scenario cards with domain, title, narrative and explicit examination connection. Use Nigerian contexts when relevant and scientifically appropriate.
- Common misconceptions, each showing the mistaken belief, why learners may adopt it, the correct understanding and a practical avoidance rule.
- Summary and notes / cheat sheet with overview, symbols and constants, examination traps and memory anchors.
- Save to Notebook.
- Spaced-review schedule with Day 1, Day 3, Day 7, Day 14 and Day 30 as initial review prompts. Scheduling must be driven by the revision system and learner responses, not treated as a fixed ceiling.
- Verified external study and simulation portals as clickable HTTPS resource cards, with provenance, terms and attribution.

#### Pillar 4 — Virtual Lab

The `PracticalLabWorkspace` has three modes.

**Bench**
- State an aim and hypothesis.
- Ask the learner to predict an outcome before running the experiment.
- Provide a live interactive subject simulator.
- Support subject-appropriate variables, sliders, toggles, apparatus and visual output.
- Record observations and results.
- Guide interpretation, graphing, calculations and conclusions.
- Explain assumptions, simplifications, uncertainty and limitations.

Use appropriate lab modules for Biology, Chemistry, Physics, Mathematics, English and Social Sciences where a meaningful interactive learning model is appropriate. Do not force a laboratory metaphor onto subjects where another interactive format is better.

**Gamified**
- Seeded, reproducible challenge rounds with variation between replays.
- Learner submission and feedback.
- Visible progress and clear replay controls.
- Deduplicated XP/reward events so replaying or resubmitting cannot create duplicate rewards.
- Rewards must support learning and must not distort academic scores or mastery evidence.

**External**
- Curated, verified HTTPS simulations and virtual laboratories, including suitable resources from PhET, GeoGebra, Desmos, LabXchange, HHMI BioInteractive, universities and public-science institutions.
- Each resource must have a mapped learning objective, subject/topic, provider, source URL, access requirements, licence/terms, permitted-use note and guided learner activity.
- Prefer linking to the provider when embedding, downloading, modifying or redistribution rights are absent or unclear.
- Provide an offline MeritPrep alternative or meaningful fallback for essential learning objectives.

Scientific animations, animated GIF sequences, interactive diagrams, simulations and virtual labs are core course requirements. Find and assess suitable free resources systematically. Free access does not automatically grant permission to copy, embed, modify or redistribute. Never scrape resources, strip attribution or bypass provider access controls. Essential learning must remain understandable through an approved offline explanation, diagram, key-frame sequence or original model.

#### Pillar 5 — Applied Project

A `TopicMasteryProjectCard` includes:

- Applied-project brief with format badge, title and a realistic Nigerian or otherwise relevant scenario.
- Numbered workflow stages with explicit deliverables.
- Three or more project verification checks, each with answer options and immediate explanatory feedback.
- Visible passed-check count.
- Rubric standards/checklist.
- Model solution gated until the required checks pass.

Project verification must test the project's intended learning outcomes. The learner must be told why a model solution is locked and what evidence is needed to unlock it. Provide accessibility alternatives and do not make progress depend on hidden or arbitrary requirements.

#### Pillar 6 — Mastery Quiz

The initial classroom quiz design uses a five-question randomized batch and an 80% pass target:

- Attempt and pass-threshold banner.
- Non-repeating randomized question batch, with diagrams and hints where appropriate.
- Submit action.
- Graded score and answer explanations.
- Weak-topic diagnostics.
- Retry with a fresh set or proceed to the next lesson when eligible.
- Clear unlock/progression feedback.

The five-question batch and 80% threshold are initial assessment design defaults, not global caps or universal definitions of mastery. Assessment length, pass thresholds and progression rules must be configurable by subject, learning objective, exam blueprint and assessment purpose. A learner must be able to continue practising beyond a single batch. Randomisation must avoid repeats within an attempt and use the available eligible item pool; question-bank growth must not be artificially capped.

### 3.4 Persistent classroom footer

Keep a persistent three-control footer with accessible touch targets (48.dp target reference):

- Previous.
- Complete / Verify Checks.
- Next / Locked.

On the Quiz pillar, hide the footer controls or preserve their alignment with an intentional spacer, as appropriate to the final interaction design. Completion and progression must be based on actual lesson requirements and recorded evidence. Do not mark mastery from mere screen visitation.

## 4. Main application screens

### 4.1 Materials — `HomeDashboardScreen`

Vertical content order:

1. Header.
2. Underlined sub-tabs: Your tracks, Study sets, Exam prep.
3. Search and filter bar.
4. Resume Current Lesson hero card.
5. Two-column subject-card grid with clear subject identity.
6. Active Recall banner when cards are due.
7. Daily Learning Plan checklist card.

The dashboard should surface the learner's next useful action without hiding access to the full subject catalogue or learning resources.

### 4.2 Syllabus — `CatalogScreen`

Vertical content order:

1. Header.
2. Horizontal subject-pill selector.
3. Track metadata row.
4. Six action tiles in a 3-by-2 grid.
5. Score/progress hero card with progress ring.
6. Syllabus overview.
7. Collapsible module accordions with sequential lesson rows, mastery and availability indicators.

Every subject's syllabus must be mapped against current authoritative examination materials. Show incomplete coverage honestly. Do not present an unmapped or draft lesson as a completed syllabus requirement.

### 4.3 Exam Lab — `ExamSimulatorScreen` and `PostExamSummaryScreen`

**Setup**
- Mode selection: Single CBT, 180-Q UTME Combo and Paper 2 Theory.
- Resumable-session banner when a recoverable attempt exists.
- Subject/elective selection.
- Examination board and assessment-length selectors.
- Launch action with a clear session summary.

**Running CBT**
- Persistent countdown timer.
- Pause, exit and calculator controls.
- Four-subject switcher tabs for the UTME combination mode.
- Question-number navigator.
- One-question-at-a-time card.
- Question diagrams where required.
- Option elimination and flag-for-review controls.
- Previous / Next navigation.
- Recovery after interruption, with answers and remaining time handled according to a documented session policy.

The 180-question UTME combo is a named target mode, not a reason to cap other valid assessment lengths. Official timing, subject combination, scoring and examination rules must be sourced from the current official specification and versioned. Any practice configuration must be labelled as a MeritPrep practice setting.

**Post-exam summary**
- Circular score gauge.
- Incorrect, skipped and correct metrics.
- Four-subject breakdown out of 400 in the relevant UTME combo mode, only when the applicable scoring model is verified.
- Start-new-round and drill-missed-question actions.
- Weak-topic diagnostics.
- Filterable per-question review with learner answer, correct answer and explanation.

Do not imply official score prediction or readiness unless a validated model supports it.

### 4.4 Notebook — `NotebookScreen`

- Header.
- Search.
- Bookmark filters.
- New Note action.
- Scrollable saved formula, note and review-card list.
- Bookmark toggle and delete action.
- Links back to the relevant lesson/topic when the source is known.

Notes and formulas must persist locally and survive app restarts and supported schema migrations.

### 4.5 Profile — `AnalyticsProfileScreen`

- Learner summary metrics.
- Native chart showing subject-level quiz accuracy versus chapter mastery, with selectable subject filters.
- Strong and weak area diagnostics.
- UTME university course/cutoff readiness information with source and year clearly stated; do not imply admission guarantees.
- Score-trend chart.
- Theme and accessibility settings.
- Academic goal editor.
- Past-attempt history.

Charts must communicate sample sizes and the meaning of each metric. Lesson completion, objective-question accuracy, theory work and practical performance remain distinct evidence types.

## 5. Quick Study launcher

The central phone launcher and equivalent tablet entry points should expose:

- Timed CBT.
- 180-question UTME.
- Recall Cards.
- Notebook.
- Paper 2.
- SS3 Placement.

Each shortcut must open the actual workflow with the learner's selected subject/session state preserved where appropriate. The same capabilities must remain reachable through accessible navigation and not depend solely on a decorative orb.

## 6. Shared interaction, state and data requirements

- Use a single, well-defined source of truth for lesson progress, pillar completion, assessment attempts and saved learning materials.
- Persist meaningful learner actions locally. Recover active sessions after process death or app restart according to the relevant session rules.
- Use stable IDs for subjects, syllabus objectives, lessons, topics, questions, projects, lab activities, media resources and revision cards.
- Keep business logic, scientific calculations, assessment rules and simulation engines separate from Compose UI and cover them with unit tests.
- Use lazy lists/grids for large catalogues and question banks. Load large media on demand. Do not impose arbitrary item-count ceilings as a substitute for performance engineering.
- Define database migrations and test that existing learner data is preserved.
- Make external-resource failures recoverable and provide a useful offline alternative.
- Support Android back navigation, process recreation, configuration changes, screen readers, large text, adequate contrast and keyboard/controller access where applicable.
- Use meaningful loading, empty, error, unavailable and offline states.
- Log technical failures without exposing learner personal data or secrets.

## 7. Academic content, media rights and learner protection

The interface must expose content status and provenance where relevant. Draft content must not appear as approved. Generated or authored practice questions must not be labelled official past questions without verified exam provenance and reuse rights.

External media must be individually checked for source, licence, attribution, permitted embedding, modification and redistribution. Respect provider terms and copyright. Do not copy third-party GIFs, simulations, video or diagrams into the APK unless the specific rights permit it.

Collect only necessary learner data. Protect notes, attempts and profile information. Any future cloud sync, accounts, analytics or teacher tools require authenticated and authorised APIs, secure transport, data export/deletion, retention rules and independent security review. Never ship privileged service credentials in the Android application.

## 8. Implementation and acceptance plan

Implement in dependency order, preserving the working alpha:

1. **Repository and architecture audit:** inspect the actual navigation, screens, domain models, persistence and tests. Record which parts of this specification already exist.
2. **App shell and navigation:** align phone/tablet navigation and Quick Study entry points without removing existing routes.
3. **Classroom frame:** implement fixed top chrome, progress, six-pillar navigation, responsive collapse and persistent footer.
4. **Pillar integration:** connect existing lesson, example, scenario, note, lab, project and assessment data to the classroom. Add missing components incrementally.
5. **Science media and virtual labs:** audit topic coverage; build original tested simulations where external reuse is not permitted; curate and rights-check external resources; add offline fallbacks.
6. **Exam Lab:** complete resumable CBT, configurable blueprints, subject combinations, theory and practical workflows.
7. **Dashboard, syllabus, notebook and profile:** integrate existing learner data and add missing screen capabilities.
8. **Quality and release:** run unit tests, UI/navigation tests, database migration tests, accessibility checks and a reproducible debug build; inspect the APK and verify critical flows on phone and tablet layouts.

### Acceptance criteria

A milestone is accepted only when:

- The relevant source code exists and is integrated into the app.
- All controls in the delivered flow perform their stated actions.
- Data persists and restores correctly.
- Scientific calculations and simulation engines have appropriate tests.
- Syllabus and question provenance are recorded.
- Offline behaviour and external-resource failure states are tested where applicable.
- Existing learner data and working features are preserved.
- Unit tests and relevant UI/instrumentation tests pass.
- The Android build succeeds and the resulting APK is verified.
- Documentation records what is implemented, partially implemented and still outstanding.

## 9. Feature-capacity policy

MeritPrep must not be deliberately restricted by arbitrary AI-generated or development-time caps. The product should be extensible across subjects, syllabus versions, topics, lessons, examples, question banks, flashcards, projects, simulations, media resources and assessment formats.

When performance or device constraints arise, solve them through engineering: lazy loading, pagination, caching, downloadable content packs, background work, database indexing, media compression, resource-aware simulation fidelity and graceful offline operation. Explain unavoidable device or provider constraints clearly. Never silently truncate content or misrepresent a partial catalogue as complete.

The policy does not override academic review, examination integrity, licensing, privacy, security, safety or technical compatibility. Those safeguards protect learners and the credibility of the product; they are not arbitrary feature caps.

## 10. Status tracking

This is a target architecture document. Implementation status must be maintained separately and backed by repository evidence. For each feature, record:

- Status: NOT_STARTED / IN_PROGRESS / IMPLEMENTED / VERIFIED / BLOCKED.
- Source files and relevant commits.
- Unit/UI test evidence.
- Known gaps and dependencies.
- Device/build verification.
- Academic and licensing review status where relevant.

Do not mark an entire pillar or screen VERIFIED because a placeholder, model class or isolated component exists.


## 11. Implementation log

### 2026-11-26 — First classroom implementation pass

The following source changes have been committed to the Android application:

- Lesson pillars are now directly accessible from phone and tablet navigation. Completion indicators remain visible, but learners can inspect and revisit later pillars without completing every earlier pillar first.
- Completed pillars remain available for revision. Completion records are still stored separately from navigation access.
- Project verification remains a requirement for verifying the applied-project outcome. The mastery quiz still requires the configured pass result before the lesson can be marked complete.
- Pillar changes reset the scroll position to the new pillar's heading so learners do not land midway through unrelated content.
- The lesson quiz now offers question-set sizes based on the available topic bank, including an **All** option that uses the full available topic bank. The default remains a short five-question practice set when the topic bank supports it; smaller banks default to their available size. The 80% target remains a configurable pedagogical default rather than a limit on practice.
- A quick lesson-note editor is available from the classroom top bar and saves to the existing on-device notebook database.
- Lesson narration speed choices now include 0.6x, 0.8x, 1.0x, 1.2x and 1.5x.

**Source files changed:** `LearningCatalog.kt`, `ClassroomPillarNavigation.kt`.  
**Test updated:** `LessonStageProgressionTest.kt` now verifies that every pillar is accessible before and after completion evidence.

**Verification status:** GitHub Actions Android CI has been triggered for these commits. Do not consider the implementation build-verified until the latest run for the final code commit completes successfully. UI behaviour still requires device testing on phone and tablet sizes.

### 2026-11-26 — Guided investigation analysis improvements

The guided investigation now analyses recorded observations by experimental setting rather than treating every repeat as a separate independent x-value:

- Repeated readings at the same setting are averaged for the plotted point, while the original observations remain in the notebook table.
- The analysis stage reports the observed direction of change from the ordered mean values and explicitly distinguishes a descriptive pattern from proof of causation.
- The graph now uses the observed minimum and maximum on both axes. This avoids the earlier zero-origin scaling error for profiles whose independent variable or response does not start at zero, and it plots the mean for each setting.
- Where at least five repeats exist at one setting, the notebook flags possible outliers using the existing 1.5 × IQR method. A flagged value is never deleted automatically; learners are told to inspect the original observation and method.

**Source changed:** `GuidedInvestigationActivity.kt`.  
**Analysis engine reused:** `BiologyExperimentAnalysis.kt`; existing unit tests cover repeated-reading summaries, graph-point ordering, trend descriptions, and non-destructive outlier flags.

**Verification:** the earlier classroom navigation and note-editor commit passed Android CI. CI for the investigation-analysis changes is running; do not mark this new analysis pass build-verified until the run for its final commit succeeds. Device testing of the graph and notebook persistence remains outstanding.


### 2026-11-26 — Virtual laboratory redesign and topic coverage pass

The virtual laboratory workspace has been reorganised so that a guided investigation is no longer presented as if it were the complete simulation experience.

- The lab now opens on **Interactive Lab** by default. The four distinct work areas are **Interactive Lab**, **Investigation**, **Challenge** and **External Labs**.
- Topic-specific Biology, Chemistry and Physics activities, visual models and practical dispatchers are now routed into the Interactive Lab. The structured predict/plan/run/record/analyse/evaluate notebook is kept in its own Investigation area.
- The Physics topics previously routed to the generic PhysicsMissingTopicExplorer now have dedicated visual laboratories: thermometer and temperature-scale exploration, specific-heat comparison, phase-change particle animation, relative-humidity and dew-point model, converging-lens ray diagram, prism dispersion, solenoid magnetic field, thermal-conduction animation and semiconductor diode I–V characteristic.
- The Biology population topic now opens the quadrat-sampling and ecological-succession activities in the Virtual Lab.
- Atomic structure now has an interactive atom builder. Learners change proton, neutron and electron counts and see element identity, isotope mass, shell arrangement and ionic charge update together. Its simplified Bohr-style representation is explicitly labelled as a teaching model, not a literal electron orbit.
- The PhET Build an Atom resource is mapped to **Atomic Structure**, while molecular-shape resources remain under **Atomic Bonding**. This corrects a previous topic mismatch.

#### External media and virtual-lab catalogue

The external catalogue now contains **128 curated resource entries mapped across all 81 Biology, Chemistry and Physics syllabus topic IDs**:

- Biology: 24 topics
- Chemistry: 18 topics
- Physics: 39 topics

Sources include PhET Interactive Simulations, LabXchange, HHMI BioInteractive, The Lab Games, K2 Physics and the Concord Consortium. Resource entries include a topic-specific summary, a prediction prompt, a post-exploration question, source attribution, licensing/usage restrictions and an offline MeritPrep alternative.

The catalogue deliberately links learners to the original provider pages instead of copying or embedding third-party simulations. This matters because the providers have different terms: standard PhET HTML simulations use CC BY-NC 4.0, LabXchange ordinary hosted-resource use is restricted by its Terms of Service, Concord hosted services have integration restrictions, and The Lab Games/K2 Physics retain their own content rights. The legacy PhET semiconductor activity is marked separately because its Java/CheerpJ compatibility may vary by device. External availability and device compatibility must not be represented as guaranteed.

#### Coverage and verification tests

- Biology coverage tests require every curriculum lesson to have a visual/interactive experience and a guided investigation profile.
- Chemistry coverage tests require every Chemistry course topic to be represented by a topic-aligned interactive or supplemental activity and keep atomic structure distinct from atomic bonding.
- Physics coverage tests require every Physics course topic to have a topic-aligned interactive or supplemental route; the nine formerly generic topics are explicitly required to use the new visual laboratories.
- Science media tests require at least one curated external resource for every Biology, Chemistry and Physics syllabus topic, validate unique resource IDs and required attribution/usage metadata, and check that each resource resolves to its mapped lesson.

**Key source files changed:** LessonVirtualLabWorkspace.kt, AtomicStructureExplorer.kt, BiologyInteractiveLessonActivities.kt, ScienceInteractiveLessonActivities.kt, PhysicsTopicVirtualLabs.kt, PhysicsSupplementalActivities.kt, ScienceMediaCatalog.kt.

**Tests added/updated:** ChemistryInteractiveActivityCoverageTest.kt, PhysicsTopicVirtualLabCoverageTest.kt, ScienceMediaCatalogTest.kt; existing Biology coverage tests retained.

**Verification status:** GitHub Actions for the latest virtual-lab and 128-resource catalogue commit is still running. Earlier failures were traced to a missing resource separator, an unsupported media enum value, and duplicate resource IDs; those issues have been corrected in subsequent commits. Do not mark this pass build-verified until CI for the latest commit completes. Physical device testing, external-link compatibility checks on Android, and academic review of every external resource remain outstanding.
