# MeritPrep 2.0 Product Roadmap

The complete agreed product scope and delivery gates are consolidated in [`MASTER_PRODUCT_SPEC.md`](MASTER_PRODUCT_SPEC.md). This roadmap is the implementation status view; the master specification is the requirements baseline.

## Product learning cycle
Learn → Explore → Practise → Assess → Correct → Retrieve → Master.

## A. Foundation
Android navigation, subject catalogue, lesson shell and build automation.

## B. Curriculum
Versioned JAMB/WAEC syllabus entities; subject/topic/objective mapping; content provenance, reviewer, approval and revision history.

## C. Learning
Structured lessons, worked examples, diagrams, formative checks, downloadable learning packs and offline progress.

## D. Examination
Timed CBT sessions, question randomisation, answer review, scoring policies, WAEC objective/theory/practical practice and transparent feedback.

**Implemented in the current alpha:** a 40-question, 30-minute JAMB-style Biology practice mock and a separate 50-question, 60-minute WAEC-style Biology objective mock. Both use random question and option order, navigation, answer flags, scoring, explanations and local persistence of active session state. The time limits are MeritPrep practice settings, not claims about official examination timings. The app stores the question order, selected answers, flags and session end time, then attempts to restore an active session on launch. Fifteen original WAEC-style Biology structured-response tasks are available with model responses, self-check marking points, locally saved learner responses and locally persisted checked rubric points. Coverage includes cells, food tests, osmosis, photosynthesis, circulation, ecology, population sampling, genetics, classification, adaptation, respiration, kidney function, flowering-plant reproduction, variation and soil. Previously saved plain-text responses remain readable. These are not official papers or marking schemes. The population ecology lesson includes an interactive quadrat-sampling estimator: learners enter counts from equal-area quadrats and habitat area to calculate mean count, organism density and an estimated population. Both the practical-lab quadrat simulation and the population lesson now use the same shared calculation engine, avoiding divergent formula implementations. It validates non-negative counts, positive finite areas, sampled area not exceeding habitat area, integer overflow and non-finite calculation results; unit tests cover normal estimates and invalid edge cases. A respiration comparison visual distinguishes cellular respiration from breathing and gas exchange, and compares aerobic respiration with anaerobic pathways in muscle and yeast. The population ecology lesson also includes a primary-versus-secondary succession visual that explains the role of soil and why succession does not follow one universal sequence. The flowering-plant reproduction visual now teaches double fertilisation explicitly, distinguishing diploid zygote formation from triploid endosperm formation and identifying the later fates of the ovule, integuments and ovary. The adaptation lesson compares structural, physiological and behavioural adaptations and distinguishes inherited adaptation from individual acclimatisation.

## E. Mastery and revision
Topic mastery based on repeated evidence, spaced retrieval, flashcards, mistake notebook and study planner.

**Implemented in the current alpha:** local revision-card storage, a four-rating spaced-retrieval scheduler, 198 Biology revision cards spanning the mapped Biology topics, a revision screen and unit-tested scheduling rules. The objective-practice mastery indicator requires at least 10 attempts, 8 distinct questions, 2 distinct study days and 80% correct. It is deliberately not presented as proof of mastery across theory or practical work. The mistake notebook captures incorrect and unanswered Biology practice items with the selected answer, correction and explanation; learners can mark items reviewed. The seven-day Biology planner prioritises weaker, unattempted and less recently studied topics using a transparent evidence-based heuristic, stores progress offline and supports daily targets from 60 to 150 minutes.

## F. Practical learning
Inventory existing APK assets and lab logic; rebuild Biology, Chemistry and Physics simulations with safe, accessible controls and clear learning objectives.

**Implemented prototypes:** Biology food-test interpretation with prediction feedback, osmosis, microscope magnification, quadrat-based ecological sampling and a dichotomous classification key, plus a biological drawing self-check checklist; introductory Physics Ohm's law and Chemistry concentration simulations. These are learning prototypes, not a complete WAEC practical examination system.

## G. Platform operations
Authenticated sync, teacher/admin CMS, analytics, backups, privacy and security review.

**Governance foundation added:** `ContentGovernancePolicy` now evaluates content provenance, syllabus mapping, version, authorship, independent approval, reviewer/date and reuse rights. It also blocks an official-past-question label unless the item is a reviewed question with verified exam year, paper and question number. This policy is unit-tested. It is not yet wired into SQLite, the learner publishing path or a teacher/admin CMS.

## Current alpha status

- Biology syllabus map: 24 units represented; each unit resolves to an authored lesson draft.
- Biology lessons: 24 beginner-oriented lesson drafts, mapped to the syllabus units; independent academic review is pending.
- Biology content integrity: automated unit tests check all 24 syllabus-to-lesson mappings, required beginner-lesson teaching elements, valid formative answer keys, unique question IDs, question provenance, glossary uniqueness and glossary-to-lesson links.
- Biology original objective practice: 242 items across the 24 units; items are explicitly marked as original and unverified.
- WAEC-style theory: 15 original structured-response tasks with model responses, self-check points and locally saved responses; independent review is pending.
- Practice: topic-focused sessions and a full Biology question-bank mode with shuffled question and option order.
- Timed CBT: a 40-question, 30-minute Biology mock with answer navigation, flags, explanations and local active-session recovery logic.
- Revision: 198 offline Biology cards with Again/Hard/Good/Easy scheduling.
- Mistake notebook: offline capture of incorrect quick-practice answers and incorrect/unanswered CBT items, with review status.
- Study planner: adaptive seven-day Biology schedule with configurable daily time target, offline activity completion and shortcuts into lessons, practice and revision.
- Mastery indicator: objective-question evidence threshold of 10 attempts, 8 distinct questions, 2 study days and at least 80% correct; theory and practical evidence are not yet included.
- Practical learning: seven Biology practical prototypes, including a quadrat population estimator with worked calculations and a controlled soil drainage/water-retention investigation, plus introductory Physics and Chemistry simulations.
- Academic approval: pending independent subject review; no generated question is presented as an official past question.
- Other subjects: starter lesson content only; full syllabus mapping and course development remain outstanding.

## Acceptance principles
- Do not equate lesson completion with mastery.
- Never present unverified questions, answer keys or marking rules as official.
- Store source, rights/licence, reviewer, approval status and content version.
- Core downloaded content should remain usable offline.
- Never promise a particular examination score.
