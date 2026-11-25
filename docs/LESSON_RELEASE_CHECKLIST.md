# MeritPrep 2.0 — Per-Run / Per-Lesson Compliance Checklist
Version 1.0 — Complete for every lesson, topic batch or release

**Rule:** The developer must consult `AGENTS.md` and `ACADEMIC_LESSON_MASTERY_STANDARD.md` before work. Complete this checklist for the work touched in the current run. Do not mark PASS without evidence. Use N/A only with a reason. A FAIL in a critical item blocks the relevant completion status.

## A. Run identification
- [ ] Repository confirmed: `kayceenet/MeritPrep-2.0` (not the original Merit repository).
- [ ] Branch and starting commit recorded.
- [ ] Current source, tests, topic IDs and dependencies inspected.
- [ ] Academic standard, this checklist and official syllabus source register read.
- [ ] Relevant subject JAMB/WAEC crosswalks read; secondary WAEC mirrors clearly marked provisional.
- [ ] Run objective and scope stated.
- [ ] Exact JAMB/WAEC syllabus statements affected identified.
- [ ] Existing functionality and regression risks identified.

## B. Syllabus and academic alignment
- [ ] Current official JAMB syllabus source/version checked.
- [ ] Current official WAEC syllabus source/version checked; if only a secondary reproduction is available, primary verification remains an explicit release blocker.
- [ ] Exact syllabus wording recorded; no invented or assumed requirements.
- [ ] Every affected statement mapped to the correct subject, topic and lesson ID.
- [ ] JAMB/WAEC differences and overlaps documented.
- [ ] Prerequisites identified and gaps bridged.
- [ ] Learning outcomes are observable and measurable.
- [ ] Definitions, principles, classifications, mechanisms and conditions are accurate.
- [ ] Scope, exceptions, assumptions and limitations are explained.
- [ ] Connections to earlier and later concepts are taught.
- [ ] No essential syllabus subtopic is silently omitted.

## C. Beginner-to-advanced teaching
- [ ] Lesson starts without assuming prior knowledge.
- [ ] Key vocabulary is introduced and explained in accessible language.
- [ ] Foundational concepts are taught before dependent concepts.
- [ ] Plain-language explanation leads into formal scientific treatment.
- [ ] Examples progress from familiar/simple to advanced/unfamiliar.
- [ ] Equations, symbols, units, dimensions and sign conventions are explained where relevant.
- [ ] Worked solutions show reasoning, not only final answers.
- [ ] Learner receives guided practice before independent practice.
- [ ] Common misconceptions and likely errors are explicitly corrected.
- [ ] Appropriate cross-topic applications and transfer tasks are included.
- [ ] Lesson is sufficiently deep for the syllabus demand; no superficial summary in place of teaching.

## D. Visual learning and animation
- [ ] Each concept that benefits from a visual has an appropriate visual representation.
- [ ] Diagram/animation is scientifically correct and clearly labelled.
- [ ] Scale, direction, sequence, structure and causal relationships are not misleading.
- [ ] Learner can pause, replay, step through or inspect complex processes where useful.
- [ ] Visuals are integrated at the point of teaching in the correct lesson.
- [ ] Visual information is explained in text and is not colour-dependent.
- [ ] No decorative animation is counted as meaningful instruction.
- [ ] Original or licensed assets are used with attribution/rights recorded.

## E. Simulation and interactive activity
- [ ] An interactive activity is included when it materially improves understanding.
- [ ] The activity is embedded in the relevant lesson, not hidden only in a separate area.
- [ ] Learner is asked to predict before observing where appropriate.
- [ ] Inputs/controls have meaningful, scientifically coherent effects.
- [ ] Inputs, outputs, ranges and units are clear.
- [ ] Input domains exclude physically invalid ranges or explain the boundary case.
- [ ] Numerical results, diagrams, animation and graphs agree.
- [ ] Learner is asked to observe, explain and apply the result.
- [ ] Boundary cases and invalid inputs are handled safely.
- [ ] Equations, assumptions, idealisations and limitations are documented.
- [ ] Simulated values and illustrative indices are clearly labelled as such.
- [ ] No simulation output is represented as real physical/experimental measurement.
- [ ] Activity works offline where practical and performs acceptably on target Android devices.

## F. Virtual practical / laboratory (complete where relevant)
- [ ] Practical syllabus requirement and learning outcome are identified.
- [ ] Briefing and experimental question are clear.
- [ ] Apparatus/materials are represented, labelled and explained.
- [ ] Correct setup and use of apparatus are taught.
- [ ] Hazards, precautions, PPE and disposal/supervision are addressed.
- [ ] Independent, dependent and controlled variables are identified.
- [ ] Method is ordered, reproducible and scientifically valid.
- [ ] Prediction is recorded before experiment execution.
- [ ] Learner performs meaningful steps/decisions rather than only viewing a result.
- [ ] Observation and readings are recorded by the learner.
- [ ] Repeats are available where appropriate.
- [ ] Data table headings, units and precision are correct.
- [ ] Appropriate graph is selected/plotted with axes and scale.
- [ ] Gradient/intercept/shape is interpreted where relevant.
- [ ] Calculations show formula, substitution, units and sensible precision.
- [ ] Conclusion answers the experimental question using evidence.
- [ ] Evaluation includes errors, limitations and improvements.
- [ ] Assessment checks practical method and data reasoning separately.
- [ ] Simulation limitations and distinction from a physical lab are explicit.
- [ ] N/A items have a scientific/pedagogical justification.

## G. Examination preparation
- [ ] Every affected syllabus statement has suitable JAMB objective practice.
- [ ] Items cover recall, understanding, application and reasoning as appropriate.
- [ ] WAEC theory/structured tasks are provided where relevant.
- [ ] WAEC practical/data questions are provided where relevant.
- [ ] Questions are original or properly licensed.
- [ ] Each question maps to a syllabus statement and skill.
- [ ] Correct answer and explanation have been independently checked.
- [ ] Distractors are plausible, unambiguous and explained.
- [ ] Theory marking points and acceptable equivalents are supplied.
- [ ] Practical data, units and expected calculations are scientifically coherent.
- [ ] Questions are not duplicates or superficial rewordings.
- [ ] Difficulty and cognitive demand are appropriate.
- [ ] Timed practice and mixed-topic transfer are available where appropriate.

## H. Mastery, feedback and remediation
- [ ] Learner progress is tracked by skill, not only completion.
- [ ] Essential domains include recall, explanation, application and practical/data skill where relevant.
- [ ] Pass threshold is explicit and configurable.
- [ ] Critical safety/method errors cannot be masked by a high total score.
- [ ] Feedback explains why an answer or method is right/wrong.
- [ ] Weak skills trigger targeted remediation.
- [ ] Remediation uses a different explanation/example and fresh equivalent assessment.
- [ ] Spaced retrieval/cumulative review is scheduled or supported.
- [ ] Mastery is not awarded merely for opening a lesson or viewing a simulation.

## I. Scientific and mathematical review
- [ ] All facts, terminology and mechanisms are accurate.
- [ ] Formulae, derivations, calculations and unit conversions are checked.
- [ ] Graph conventions, gradients and significant figures are correct.
- [ ] Model outputs tested against hand-calculated/known examples.
- [ ] Animation timing, geometry and parameter changes checked against the same underlying model.
- [ ] Boundary and limiting cases tested.
- [ ] Data are plausible and clearly identified as simulated or real.
- [ ] Simplifications and uncertainties are not hidden.
- [ ] Subject-matter review evidence recorded.

## J. Accessibility, usability and offline operation
- [ ] Layout works on small Android screens.
- [ ] Text is legible and hierarchy is clear.
- [ ] Controls have clear labels, ranges and feedback.
- [ ] Important meaning is not conveyed by colour alone.
- [ ] Essential content is available offline where practical.
- [ ] External links are supplementary, not required to complete core learning.
- [ ] Loading, empty, error and completion states are handled.
- [ ] Progress/data persistence does not corrupt existing learner records.
- [ ] Accessibility and performance limitations are recorded.

## K. Engineering and regression
- [ ] Existing architecture and conventions are preserved.
- [ ] Actual APIs and data models were inspected before use.
- [ ] Topic-to-activity routing matches actual lesson IDs.
- [ ] No duplicate or unreachable mapping branches remain.
- [ ] Unit tests added/updated for calculations and boundary cases.
- [ ] Tests added/updated for syllabus mapping and lesson routing.
- [ ] UI/integration tests run where feasible.
- [ ] Existing navigation, database, assessments and progress remain intact.
- [ ] Build succeeds for the exact commit.
- [ ] CI workflow succeeds for the exact commit.
- [ ] Failure logs were inspected and resolved, not ignored.
- [ ] APK artifact exists and is unexpired before it is reported.
- [ ] Physical-device testing is claimed only if actually performed.

## L. Completion decision
- [ ] **AUDITED:** official syllabus mapping is recorded.
- [ ] **IMPLEMENTED:** all applicable teaching and learning experiences exist.
- [ ] **ASSESSED:** relevant JAMB/WAEC objective, theory and practical/data assessments exist.
- [ ] **MASTERY-READY:** skill tracking, mastery gate and remediation work.
- [ ] **VERIFIED:** academic review and technical tests pass with evidence.

A topic may not be labelled complete if any applicable critical item above is FAIL or unverified. A subject may not be labelled 100% complete until every current official syllabus statement has evidence and no critical gap remains.

## M. Run evidence record (must be filled in)
- Date:
- Run objective:
- Starting commit:
- Resulting commit:
- Subject/topic IDs:
- Official JAMB syllabus source/version:
- Official WAEC syllabus source/version:
- Syllabus statements addressed:
- Files/components changed:
- Interactive/visual/lab experiences added or improved:
- Assessments/tests added or improved:
- Unit test result:
- Android build result:
- CI workflow URL and exact commit:
- APK artifact URL/status (if generated):
- Academic reviewer/evidence:
- Checklist failures or N/A reasons:
- Remaining gaps:
- Next implementation target:

## N. Final run decision
- [ ] PASS — applicable requirements satisfied with evidence.
- [ ] PARTIAL — useful work completed, but named gaps remain; do not claim topic completion.
- [ ] BLOCKED — unresolved critical issue prevents safe/accurate release.

**Required reporting rule:** state what was actually implemented, what was tested, exact CI status, known limitations and remaining work. Never report an unverified build, fabricated coverage percentage or unsupported 100% mastery claim.


## Latest verification record — 25 November 2026

- Commit: `d7e34996e2250ddc5c3c43580e9eafafe99f32d4`
- GitHub Actions run: [45661633907](https://github.com/kayceenet/MeritPrep-2.0/actions/runs/45661633907)
- Result: **success** — unit tests, debug APK build and artifact upload completed.
- APK artifact: `MeritPrep-debug-apk` (17,788,171 bytes; expires 9 December 2026).
- SHA-256: `a1adabc55903f696d83d6e4f4652c2d7081badd070f9de5d6bbe1bd232f9e8e9`.
- Scope verified in this run: WAEC activity routes, Biology/Chemistry/Physics lesson extensions, native projectile/satellite/particle animations, guided investigation axis labels and repeat means, 38-resource science media catalogue and PhET licensing checks.
- Not verified by CI: installation or interaction on a physical Android device, full academic sign-off, direct WAEC primary-PDF verification, or statement-level syllabus completion.



---

## Run record — classroom architecture and scientific notation redesign (25 November 2026)

**Scope:** Reorganise the science lesson experience using the original Merit app's classroom interaction patterns. No lesson prose, question text, laboratory write-ups or course content was copied from the reference repository.

| Check | Status | Evidence / limitation |
|---|---|---|
| Repository scope | PASS | Changes are in `kayceenet/MeritPrep-2.0`; original `kayceenet/Merit` was read-only. |
| Reference structure inspected | PASS | Reviewed the reference classroom screen, six lesson pillars, section animation component, practical lab workspace, subject lab dispatchers, scientific formatter and diagram cards. |
| One-pillar-at-a-time classroom | IMPLEMENTED | `LearningCatalog.kt`: Concept, Examples, Scenario, Lab, Project and Exercise stages. |
| Original teaching content | PASS | New examples and scenarios are independently authored; reference course write-ups were not imported. |
| Visual and simulation integration | IMPLEMENTED | Topic visual gallery, existing subject activities, plus native wave, refraction, current-flow and equilibrium animations. |
| Guided practical workflow | IMPLEMENTED | Existing five-stage guided investigation remains in the Lab pillar; session persistence and uncertainty/regression remain future work. |
| Topic practice integration | IMPLEMENTED | Exercise stage selects up to five topic-mapped questions, shuffles options, gives explanations and requires 80% for completion. `bio-living` currently falls back to the lesson check because it has no mapped bank item. |
| Scientific notation | IMPLEMENTED, LIMITED SUBSET | `ScientificNotation.kt` supports common LaTeX operators, Greek symbols, powers, subscripts, fractions and roots. It is not a full TeX typesetter. |
| Scientific notation unit tests | PASS | Formatter tests, stacked equation cards, dashboard/theme, lesson stages, scenarios and animations passed on commit `5cdd9786447fee6be0cd67c2e17b83b2b921a93a`. |
| Latest practical/theory notation integration | PENDING | Scientific formatting was extended to Biology, Chemistry and Physics question/practical/theory screens after the successful `5cdd9786` run. Verify the newest Actions run. |
| Physical-device visual review | NOT DONE | Screen density, font scaling, low-end performance, accessibility and visual polish still require an actual Android device review. |
| Full syllabus academic sign-off | NOT DONE | This UI redesign does not prove statement-level JAMB/WAEC coverage or subject-specialist approval. |

**Latest implementation areas:** `MainActivity.kt`, `MeritPrepTheme.kt`, `LearningCatalog.kt`, `ScientificNotation.kt`, `TopicScienceAnimations.kt`, `TopicScenarioActivities.kt`, `GuidedInvestigationActivity.kt`, question/practical/theory screens, and `docs/CLASSROOM_EXPERIENCE_REDESIGN.md`.

**Release gate:** Do not distribute a replacement APK until the newest commit has passing unit tests, a successful debug APK build and uploaded artifact, followed by a visual review on a physical Android device.


### Follow-on virtual lab enhancement — 25 November 2026

- Added topic-specific apparatus schematics to the guided investigation briefing. Coverage routing is unit-tested in `InvestigationApparatusRoutingTest`.
- Added local persistence for investigation stage, prediction, reviewed method steps, selected model setting and recorded observations. A save control is visible in the lab workspace.
- Added subject-specific practical routing for Chemistry and Physics topics, with acid-base and redox titration routed separately.
- Latest code changes are under CI run `45671674413` (apparatus routing test) and follow-on run `45671584471` (notebook persistence). Do not release until the newest main-branch run completes successfully.


### Final structure pass additions — 25 November 2026

- Concept-stage diagrams/animations are now selected from each lesson section heading and placed directly after the relevant explanation.
- Every lesson's Scenario pillar has either a topic-specific original case study or a saved applied-reflection fallback.
- Six-pillar completion is persisted locally and shown in the stage navigation; assessment completion requires at least 80%.
- Subject hubs now use the redesigned dashboard/catalog hierarchy and group their subject-specific practice and WAEC tools.
- The successful pre-follow-on baseline at `f806f111` includes the redesigned home and subject hubs, and the successful lab baseline at `b8acd40a` includes persistent lab records and apparatus routing. The latest section-visual/scenario/pillar-progress additions are under the newest Actions run; do not release until the latest SHA passes tests and APK build.


### Cross-subject interactive and equation coverage — 25 November 2026

- Added a regression test against the Biology curriculum map: every lesson topic must resolve to an established or supplemental visual/interactive experience.
- Added Chemistry topic coverage test across the full 18-topic course map.
- Added Physics topic coverage test across the full 39-topic course map.
- Added 17 supplemental Biology investigations/explorers, 9 supplemental Physics model workspaces, and explicit Chemistry supplemental coverage checks. Existing subject-specific practical and visual activities remain integrated.
- Added display-math parsing and structured equation cards, expanded symbol support, and extended core equation panels across Chemistry and Physics.
- Verification gate: latest workflow after the code repairs is run `45718825275`; unit tests/build were still running at the time this note was written. Do not treat the newest source as release-ready until that run or a later main-branch run succeeds.
