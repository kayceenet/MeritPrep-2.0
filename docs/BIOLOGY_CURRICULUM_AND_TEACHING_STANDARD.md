# MeritPrep Biology Curriculum and Teaching Standard

**Course version:** 2026.1  
**Status:** syllabus map implemented; lesson authoring and independent academic review are in progress.

## 1. Curriculum authority and scope

The JAMB UTME Biology syllabus is the primary topic-and-objective map for the UTME track. The WAEC Biology syllabus is a parallel assessment map, not a replacement for JAMB. WAEC practical and structured-response requirements must remain visible even where topic names overlap.

Sources consulted:
- JAMB Biology syllabus PDF served through the IBASS-linked syllabus copy: https://jambsyllabus.com/downloads/jamb-biology-syllabus-2026-official.pdf
- JAMB IBASS entry point: https://ibass.jamb.gov.ng/e-syllabus
- WAEC Biology syllabus (includes the common section and the Nigeria-specific Section C): https://schoolingng.com/wp-content/uploads/2026/10/WAEC_Biology_Syllabus.pdf
- WAEC Nigeria examination information: https://waecnigeria.org/

The JAMB PDF carries no explicit examination-year label. Before each exam cycle, the academic editor must re-check the current IBASS syllabus and record the retrieval date and file checksum. The WAEC syllabus PDF is a reproduced copy hosted by a third party; the academic editor must confirm it against WAEC's current official materials before final approval.

## 2. JAMB topic map

The app models 24 syllabus blocks in five sections:

| Section | Units |
|---|---|
| A · Variety of organisms | Living organisms and cell organisation; classification and diversity; adaptation for survival |
| B · Form and functions | Plant and mammalian structure; nutrition; transport; respiration; excretion; support and movement; reproduction; growth; coordination, control and homeostasis |
| C · Ecology | Distribution factors; ecological relationships and energy flow; natural habitats; Nigerian biomes; population ecology and succession; soil; humans, health and environment |
| D · Heredity and variations | Variation in populations; heredity and genetics; biotechnology |
| E · Evolution | Theories and mechanisms; evidence and trends |

Each unit in `BiologyCourse.kt` records its syllabus section, a learner-facing scope, a concise outcome statement, WAEC cross-links and (only when authored) a lesson route. Do not mark a unit complete merely because it appears in the syllabus map.

## 3. WAEC practical strand map

The course must teach and assess:
1. Specimen observation, biological drawing and labelling.
2. Classification and comparison using observable features.
3. Food tests and interpretation of colour changes.
4. Microscopy, cells and temporary preparations.
5. Osmosis, diffusion and simple physiological investigations.
6. Ecological sampling, field measurements, tables and graphs.
7. Experimental design: aim, hypothesis, variables, method, results and conclusion.

The practical system must use verified specimen descriptions and current exam instructions. Never invent an unseen specimen, expected observation, marking point or practical timetable.

## 4. Teaching method: zero knowledge to examination competence

Every complete lesson must use a consistent instructional sequence:

1. **Why this matters:** a familiar real-world situation that gives the concept a purpose.
2. **Prerequisite check:** identify the minimum knowledge needed and repair gaps before advancing.
3. **Plain-language explanation:** define new terms before using them; introduce one idea at a time.
4. **Accurate scientific explanation:** add the accepted terminology, mechanisms and limits.
5. **Visual model:** labelled diagram, process sequence, comparison table or animation where it genuinely helps.
6. **Worked example:** demonstrate reasoning step by step, including units and interpretation where relevant.
7. **Guided practice:** a near-transfer problem with hints.
8. **Independent retrieval:** questions that require recall, application and interpretation, not recognition alone.
9. **Misconception repair:** explain why tempting wrong answers are wrong.
10. **Exam transfer:** JAMB-style objective practice and WAEC-style structured/practical application.
11. **Exit check:** assess the stated objectives; completion is separate from mastery.
12. **Spaced review:** schedule retrieval after the lesson and again after increasing intervals.

### Language and accessibility

- Assume no prior knowledge, but do not talk down to the learner.
- Explain every acronym at first use.
- Use familiar Nigerian contexts where accurate: local crops, food, habitats, public health and everyday observations.
- Avoid local examples that distort the underlying science.
- Define technical vocabulary in a glossary and reuse terms consistently.
- Separate a useful analogy from the actual biological mechanism.
- Include accessible text descriptions for diagrams and do not rely on colour alone.

## 5. Scientific and assessment quality controls

Every published lesson, diagram, question, answer key, experiment and rubric must have:
- stable content ID and version;
- syllabus section, topic and objective mapping;
- author and independent reviewer;
- source/reference and retrieval date;
- rights/licence record for external assets;
- draft, review, approved or retired status;
- review date and change history.

Question records must state whether they are original practice items or verified past questions. Never label generated questions as past questions. A question must have one defensible key (or an explicitly stated multiple-select rule), a clear explanation, plausible distractors and objective alignment.

Biology accuracy checks must specifically guard against:
- confusing respiration with breathing or gas exchange;
- confusing excretion with egestion;
- treating cell wall and cell membrane as interchangeable;
- claiming all plant cells contain chloroplasts;
- describing diffusion as requiring metabolic energy;
- describing osmosis without specifying water movement across a selectively permeable membrane;
- treating evolution as purposeful progress;
- treating a correlation as proof of causation;
- using oversimplified medical claims in genetics and disease topics.

## 6. Mastery policy

Lesson completion means the learner reached the end and completed required activities. It is not proof of mastery.

A topic should only be marked mastered after the learner demonstrates performance across more than one attempt and more than one question form, including delayed retrieval. Store accuracy, attempts, recency and evidence separately. A single easy multiple-choice question must never unlock a mastery badge.

## 7. Implementation status

- [x] JAMB Biology section and 24-unit map represented in app code.
- [x] WAEC cross-links recorded for each unit.
- [x] WAEC practical strand checklist represented.
- [x] First Biology cell lesson expanded for beginner teaching.
- [ ] Author full lessons for all remaining syllabus units.
- [x] Add an initial 12-item original cell-biology objective bank (still awaiting independent review).\n- [ ] Expand original questions across all syllabus units and add reviewed structured-response items.
- [ ] Add labelled diagrams, animations and offline visual assets.
- [ ] Add specimen/practical tasks with reviewed marking rubrics.
- [ ] Add syllabus coverage reporting and reviewer workflow.
- [ ] Independently review and approve the twelve authored lesson drafts, 120 objective questions and 102 revision cards.
- [ ] Validate every item against the current official syllabus before release.

The curriculum map is not a claim that all 24 units are already taught. The app must show authored, reviewed and pending content honestly.
