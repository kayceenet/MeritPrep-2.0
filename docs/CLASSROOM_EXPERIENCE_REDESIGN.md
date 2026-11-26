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


## Apparatus visuals and persistent lab notebooks

The guided investigation briefing now includes original, labelled schematic layouts for common Biology, Chemistry and Physics apparatus: microscopes, plant/light arrangements, quadrats, titration glassware, filtration, electrolysis, springs, dynamics trolleys, liquid-pressure columns, circuits, ripple tanks and gas syringes. These are explicitly marked as schematic and not to scale; the written method and safety controls remain authoritative.

The guided lab notebook now saves the learner's selected stage, prediction, method checklist, current model setting and recorded observations to the app's local notes database. Learners can explicitly save changes and resume a topic investigation later. This is local persistence, not cloud synchronisation.


## Home and subject discovery

The home screen is now a learning dashboard rather than a list of navigation buttons. It groups the main learning actions, gives a direct path into the virtual labs and presents subjects as a clear next step. Subject hubs now show topic and objective counts, group the available exam/practice tools, and display the lesson sequence as distinct topic cards. This follows the reference app's dashboard/catalog structure without importing its lesson prose or course content.


## Section-level visuals, scenario fallback and pillar progress

Concept visuals are now resolved from each lesson section heading, so the relevant diagram or animation appears alongside the explanation instead of being isolated in a separate media catalogue. Examples remain available in their own pillar for deliberate revision.

Every lesson has a Scenario stage. Topics with a specific original case study receive a multiple-choice decision and explanation; other topics receive a saved application challenge in which the learner identifies a real system, predicts a variable change and specifies evidence.

The six lesson pillars now record completion locally. Completed pillars are marked in the navigation and the progress indicator reports the number completed. The Exercise pillar records completion only after the topic assessment reaches its 80% threshold. Learners can navigate between pillars freely; the current implementation tracks completion but does not lock later pillars.


## Science simulations and mathematical typesetting — 25 November 2026

The Biology visualisation pillar now includes 17 additional native, offline interactive experiences for classification, adaptation, plant transpiration, respiration, kidney filtration, musculoskeletal levers, flowering-plant reproduction, reflex arcs, ecological tolerance, Nigerian habitats and biomes, soil properties, pollution pathways, variation, fermentation and comparative anatomy. Existing cell, photosynthesis, osmosis, diffusion, mitosis, inheritance, natural-selection and population-sampling activities remain in place. The new activities state their model assumptions and limitations rather than presenting illustrative outputs as experimental measurements.

A curriculum-level regression test now checks that every Biology lesson mapping has an established or supplemental visual/interactive experience.

The scientific notation renderer now recognises display-math delimiters (`\\[...\\]` and `$$...$$`) and separates prose from display equations. Display equations use structured equation cards with stacked fractions, while inline formula text supports common Greek symbols, operators, powers, subscripts, roots, chemical notation and mathematical accents. Core relationship panels have been expanded across Chemistry and Physics and are also visible in the concept pillar, not only in the visualisation gallery.


## Guided investigation coverage across the three sciences

The guided investigation profile catalogue now has a topic route for every mapped Biology, Chemistry and Physics syllabus entry. Biology includes microscopy, classification, plant and animal physiology, ecology, heredity, biotechnology and evolution investigations. Chemistry includes titration, redox, qualitative gas tests, metal displacement, equilibrium, ionic formula modelling and spectroscopy. Physics includes mechanics, heat, gas laws, optics, electricity, capacitors, magnetism, induction and semiconductor investigations.

Each profile supplies a question, apparatus or model materials, independent/dependent/control variables, safety notes, a method, a response model and an explicit limitation. The route-coverage tests check all mapped topics; they do not replace expert review of every method, scientific model or examination syllabus statement.


## Guided classroom progression

The six lesson pillars now follow a sequential mastery path: Concepts & Theory → Worked Examples → Applications & Notes → Virtual Lab → Applied Project → Mastery Quiz. A learner can open the first pillar immediately; each subsequent pillar unlocks only after the previous pillar is marked complete. Completed pillars remain available for revision, the notebook stores pillar progress locally, and a returning learner is taken to the next incomplete pillar. The Exercise pillar still requires the configured 80% threshold before the lesson can be marked mastered.


## Dashboard navigation and discovery

The home dashboard now follows the reference application's structural pattern: a clear continue-learning destination, search across lesson titles/objectives/explanations, subject filters, progress and accuracy metrics, due-review count, and grouped quick actions for practice, virtual labs, revision, saved lessons, study planning and science media. The dashboard derives its progress from the implemented lesson set rather than counting syllabus-outline placeholders as completed lessons. No course explanations or lesson write-ups were taken from the reference repository.


## External science-media library

The Explore pillar links to 47 curated external resources from PhET Interactive Simulations, HHMI BioInteractive and LabXchange: 30 interactive simulations, 7 virtual-lab resources and 10 animations. Each resource has a topic link, a before-you-start prompt, an after-you-explore task, attribution guidance and an offline alternative. These remain external resource links, not bundled media; their current licences and commercial-use restrictions must be checked before any embedding, caching, modification or redistribution.


## Responsive classroom shell

The lesson workspace now uses a six-pillar navigation component with two presentations: a compact, auto-collapsing mode selector on phones and a fixed 268 dp section sidebar on wide layouts. Pillar selection resets the shared lesson-body scroll position. The app shell also has a compact docked bottom navigation on phones, a navigation rail on wide screens, and a quick-study launcher sheet for high-frequency destinations. The classroom header keeps back navigation, scientific calculators and lesson bookmarking available without competing with the lesson content.


## Detailed classroom architecture alignment — 26 November 2026

The implementation now follows the six-pillar classroom model:

1. **Concepts & Theory** — first-principles introduction, learning outcomes, sequenced topic explanations, section-matched visuals, topic equation panel and an immediate-feedback quick concept check.
2. **Worked Examples** — original worked example and topic-specific simulation/visualisation activities.
3. **Applications & Notes** — original scenario decision or saved application reflection, plus relevant verified external learning media.
4. **Virtual Lab** — guided investigation notebook, topic-specific apparatus schematic, practical dispatcher and subject/WAEC supplemental practical activities.
5. **Applied Project** — learner project workspace and a three-question graded scientific-reasoning verification. Completion is gated until all three checks are answered correctly.
6. **Mastery Quiz** — a fresh five-question topic set where available, instant explanation after submission and an 80% completion threshold.

The classroom shell retains a fixed title/progress header, responsive tablet sidebar and phone mode selector that collapses on scroll. A fixed bottom navigation row now provides Previous, Complete/Verify and Next controls on non-quiz pillars. The quiz retains its own assessment/lesson-completion controls.

This is a structural alignment milestone, not a claim that every syllabus topic already has all six pillars at the same academic depth. Topic-specific project briefs, full misconception banks, examiner guidance, verified portal coverage, per-topic audio controls, adaptive spaced review and deeper project rubrics remain staged work. The generic project verification checks scientific reasoning process and must not be represented as a topic-specific assessment of mastery.


## Home dashboard and learner workspace alignment — 26 November 2026

- The Materials home dashboard now presents a two-column subject grid with a distinct restrained gradient for each subject, a true resume/next-topic hero, due active-recall banner, quick actions and a daily study-session plan.
- The dashboard records the most recently opened unfinished topic and offers it as the resume destination. The classroom stores the active pillar and completed-pillar list locally so the learner can return to the same point in a lesson.
- The visual system now uses a cohesive deep-teal, slate-blue and warm-neutral palette in light and dark modes.
- A unified Notebook screen is available from the tablet navigation rail and phone quick-study launcher. It separates saved notes, bookmarked lessons and outstanding mistakes; saved notes can be searched, opened in context and deleted with confirmation.


## Exam Lab and practical-workspace structure — 26 November 2026

- Added an original 180-question Science UTME combination: 60 Use of English, 40 Biology, 40 Chemistry and 40 Physics. It is explicitly labelled a fixed science combination, not a selectable arbitrary four-subject combination. The bank is deterministic across app restarts so an active session can restore its question IDs; each new sitting randomises question order within the four subject blocks and shuffles answer options.
- Added subject switcher chips, a compact 10-column question navigator, pause/resume, finish-session confirmation and a scientific-calculator shortcut. Results include per-subject and per-topic breakdowns.
- Lesson Virtual Lab now has three modes: Bench (guided notebook, apparatus, topic practical and WAEC supplemental work), Challenge (replayable three-question challenge where sufficient topic questions exist, otherwise a clearly labelled shorter check), and Explore (rights-checked external resources).
- The learner profile now includes a native Canvas comparison of practice accuracy and demonstrated topic mastery, selectable subject filters, and evidence-based strong/weak topic lists. It deliberately does not predict university admission readiness without verified cut-off data and a selected target course/university.
