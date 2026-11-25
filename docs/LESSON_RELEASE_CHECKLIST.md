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
