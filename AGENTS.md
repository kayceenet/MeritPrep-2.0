# Mandatory MeritPrep 2.0 instructions

At the start of EVERY run, read this file, `docs/EXAM_SYLLABUS_KNOWLEDGE_BASE_ORCHESTRATION.md`, `docs/ACADEMIC_LESSON_MASTERY_STANDARD.md`, `docs/LESSON_RELEASE_CHECKLIST.md` and `docs/OFFICIAL_SYLLABUS_SOURCE_REGISTER.md`. Before editing a subject, read its current JAMB and WAEC crosswalk documents in `docs/`. These are mandatory acceptance requirements, not optional guidance.

## Scope
- Work only in `kayceenet/MeritPrep-2.0`; never modify the separate original `kayceenet/Merit` repository.
- Preserve the Kotlin/Jetpack Compose architecture, existing navigation, database, progress, assessments, question banks and wider subject scope.
- Inspect actual source, topic IDs, APIs and tests before editing. Never guess mappings or claim unverified coverage.
- “Continue” means continue hands-on implementation, not provide only a plan.

## Academic requirements
- Map every current official JAMB and WAEC syllabus statement individually to teaching, visualisation, activity, practical competence where relevant, and assessment evidence. A secondary WAEC syllabus mirror is a provisional working source only; verify against a direct WAEC source before academic sign-off.
- Teach from zero assumed knowledge to independent examination-level application: prerequisites, plain explanation, formal scientific treatment, worked examples, diagrams/animations, misconception correction, retrieval, transfer and exam practice.
- Every micro-lesson and teachable subtopic must implement the mandatory six-pillar NetAcad-inspired framework in section 17 of the knowledge-base orchestration document: Concepts, Examples, Context, Virtual Lab (or justified subject-appropriate interactive equivalent), Project and Quiz. These must form one connected, adaptive tutorial with response-specific feedback, remediation/extension and evidence-based mastery; never use six empty/decorative headings.
- Every examinable idea taught, demonstrated or applied in Concepts, Examples or Context must have its own explicitly mapped concept check at the relevant stage. Checks must test actual understanding/method/transfer, give response-specific feedback and remediation, and record evidence; the closing Quiz does not replace these checks. Follow section 17.10 of the knowledge-base framework.
- Integrate relevant activities inside the correct lessons. Links are supplementary, never substitutes for original learning experiences.
- Build original offline-capable animations, simulations, diagrams and virtual labs where appropriate free/licensed resources are unavailable. Respect copyright and licence terms.
- Virtual labs must include briefing, apparatus, safety, variables, method, prediction, experiment, observations/readings, repeats where relevant, data tables, graphs, calculations, interpretation, evidence-based conclusion, evaluation and assessment.
- Clearly label simulated data and illustrative indices. Never represent simulation outputs as real measurements. Document equations, units, assumptions, boundaries and limitations.
- Provide JAMB objective and WAEC theory/practical assessment appropriate to each syllabus statement, with explanations, marking logic and remediation.
- Scientific, mathematical, pedagogical and examination accuracy take priority over speed or superficial quantity.

## Mandatory procedure each run
1. Read the academic standard and release checklist.
2. Inspect repository state, current commit, relevant implementation, official syllabus mapping, tests and real topic IDs.
3. Identify specific syllabus gaps; avoid duplicate activities and incorrect routing.
4. Implement a coherent learning slice, not a decorative control or isolated content stub.
5. Add/update tests for calculations, boundary cases, topic mappings and data handling.
6. Review accessibility, offline operation, state correctness, scientific limitations, performance and regressions.
7. Run relevant tests and Android build. Inspect logs, fix failures and rerun.
8. Complete checklist with PASS/FAIL/NOT APPLICABLE and evidence. Do not hide unresolved failures.
9. Commit to this repository and verify the exact commit and CI result. Report APK artifacts only when verified.
10. Report what was actually completed, what remains, and the next target. Never claim 100% syllabus coverage prematurely.

Do not claim physical-device testing unless performed. Do not invent sources, syllabus requirements, data, paths or artifact links. If an operation times out, inspect repository state before retrying to avoid duplicate commits. Every run must consult the standard and checklist, even for apparently small changes.


## Reference-repository boundary

`kayceenet/Merit` is a read-only reference for user-interface structure and interaction architecture only. You may study and reproduce structural patterns such as lesson pillars, navigation, animation placement, illustration cards, practical-lab workspaces, activity flows, feedback patterns and visual hierarchy. **Never copy, import, paraphrase or adapt its course-content write-ups, explanations, worked examples, question wording, answers, scenarios, experiment instructions, marking points or other teaching prose.** All MeritPrep 2.0 academic content must be independently authored, scientifically checked and mapped to its own JAMB/WAEC syllabus evidence. Do not modify the reference repository.
