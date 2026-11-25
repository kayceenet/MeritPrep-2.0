# MeritPrep 2.0 classroom experience redesign

**Reference repository:** `kayceenet/Merit`  
**Implementation repository:** `kayceenet/MeritPrep-2.0`  
**Content rule:** Only interaction patterns, information architecture and presentation approaches are used as references. No course explanations, worked examples, question wording, laboratory write-ups or other teaching content are copied from Merit.

## Reference patterns adopted

The original app's lesson classroom uses a one-pillar-at-a-time learning workspace. It separates the concept lesson, examples, scenario/application, practical laboratory, topic project and exercise rather than presenting every learning element in one uninterrupted column. It also integrates section animations, diagrams, scientific formatting, domain-specific lab workspaces and guided examination practice inside the lesson.

MeritPrep 2.0 now uses the same structural principle with six independently navigable lesson stages:

| Merit classroom pattern | MeritPrep 2.0 stage | Purpose |
|---|---|---|
| Concept | Learn | Lesson objectives, prerequisite-aware explanations and scientific sections |
| Examples | Examples | Original worked examples, diagrams, concept animations and interactive models |
| Scenario | Scenario | Contextual exploration and approved external resources |
| Lab | Lab | Guided investigation, practical procedure, data recording, graphing and evaluation |
| Project | Project | Learner's applied explanation, evidence and reflection |
| Exercise | Exercise | Topic-mapped objective practice with feedback and a mastery threshold |

Only the selected stage is displayed. The lesson header keeps the subject, topic, bookmark and progress visible. Previous/continue controls make the sequence explicit without forcing a learner to scroll through every activity.

## Native visual and interactive work

The Examples stage hosts:
- original lesson diagrams already in MeritPrep 2.0;
- topic-mapped animated models for wave propagation, refraction, current flow and dynamic equilibrium;
- existing Biology, Chemistry and Physics interactive lesson activities;
- worked numerical examples for selected Biology, Chemistry and Physics topics;
- a topic-specific equation panel where a governing relationship is central.

The Scenario stage retains the lesson-linked media catalogue as an optional extension. External resources do not replace the offline lesson or its native activities.

The Lab stage hosts the guided investigation workflow and WAEC-specific practical extensions. The guided workflow includes briefing, apparatus, safety, variables, prediction, method, model run, learner-recorded readings, data table, graph, gradient, conclusion and evaluation. The app labels illustrative outputs as simulated and explains each model's boundaries.

## Scientific notation and equations

`ScientificNotation.kt` adds an offline formatter for the common LaTeX subset used in school science:
- Greek symbols and scientific operators;
- superscripts and subscripts;
- common arrows and equilibrium notation;
- fractions and square roots;
- `\\mathrm{}`, `\\text{}`, and `\\ce{}` wrappers;
- equation cards with readable mathematical typography.

This is a purpose-built subset formatter, not a complete TeX engine. It does not claim to render arbitrary LaTeX packages, aligned environments, complex matrices or full typesetting. Unsupported expressions must remain legible and should be added deliberately with tests rather than silently mangled.

## Originality and academic separation

All newly written examples, equations, activity instructions and scenarios in MeritPrep 2.0 are authored independently. The original Merit repository is not modified. Its lesson prose and course content are not imported into this project.

## Verification status

The new classroom and notation work is committed to the MeritPrep 2.0 main branch. Android CI is running against the latest commits. Do not treat the redesign as release-ready until unit tests and the debug APK build pass on the same final commit and the lesson screens receive a visual review on a real Android device.

## Remaining quality work

- Review every lesson's animation/diagram selection against its actual topic; do not count generic animation as coverage.
- Expand original worked examples and visualisations to all topics where they materially improve understanding.
- Review equations, symbols, units, assumptions and worked calculations with subject specialists.
- Persist learner lab portfolios and add repeat trials, regression, uncertainty and downloadable data where appropriate.
- Inspect screen density, text wrapping, scrolling, accessibility and low-end-device performance on physical Android devices.


## App-wide visual and scientific presentation update

The same reference-led design pass now extends to the entry screens:
- a structured home dashboard with a learning-workspace hero, grouped quick actions and subject discovery;
- reusable subject cards with clear hierarchy and restrained surface/border treatment;
- a coordinated light/dark palette and consistent rounded surface shapes.

Scientific notation is being applied beyond lesson paragraphs. Topic assessment prompts/options/explanations, Biology theory responses, Chemistry and Physics practice, WAEC theory guidance, qualitative analysis and practical calculation explanations now use the shared formatter where chemical symbols, powers, units or equations may appear.

Display equations support vertically stacked fractions in addition to inline superscripts/subscripts, Greek symbols, operators and roots. The formatter remains a deliberately limited offline LaTeX subset rather than a full TeX engine.

**Verification:** The redesigned home/theme and stacked-equation implementation passed unit tests and the Android debug APK workflow on commit `5cdd9786447fee6be0cd67c2e17b83b2b921a93a`. Further formatter integrations in practical/theory screens are committed after that SHA and remain subject to the latest CI run.
