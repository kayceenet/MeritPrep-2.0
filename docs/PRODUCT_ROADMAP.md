# MeritPrep 2.0 Product Roadmap

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

**Implemented in the current alpha:** a 40-question, 30-minute Biology practice mock, random question and option order, navigation, answer flags, scoring, explanations, and local persistence of active session state. The app stores the question order, selected answers, flags and session end time, then attempts to restore an active session on launch. Ten original WAEC-style Biology structured-response tasks are also available with model responses, self-check marking points and locally saved learner responses. These are not official papers or marking schemes.

## E. Mastery and revision
Topic mastery based on repeated evidence, spaced retrieval, flashcards, mistake notebook and study planner.

**Implemented in the current alpha:** local revision-card storage, a four-rating spaced-retrieval scheduler, 198 Biology revision cards spanning the mapped Biology topics, a revision screen and unit-tested scheduling rules. The objective-practice mastery indicator requires at least 10 attempts, 8 distinct questions, 2 distinct study days and 80% correct. It is deliberately not presented as proof of mastery across theory or practical work. The mistake notebook captures incorrect and unanswered Biology practice items with the selected answer, correction and explanation; learners can mark items reviewed. The seven-day Biology planner prioritises weaker, unattempted and less recently studied topics using a transparent evidence-based heuristic, stores progress offline and supports daily targets from 60 to 150 minutes.

## F. Practical learning
Inventory existing APK assets and lab logic; rebuild Biology, Chemistry and Physics simulations with safe, accessible controls and clear learning objectives.

**Implemented prototypes:** Biology food-test interpretation with prediction feedback, osmosis, microscope magnification and quadrat-based ecological sampling; introductory Physics Ohm's law and Chemistry concentration simulations. These are learning prototypes, not a complete WAEC practical examination system.

## G. Platform operations
Authenticated sync, teacher/admin CMS, analytics, backups, privacy and security review.

## Current alpha status

- Biology syllabus map: 24 units represented; each unit resolves to an authored lesson draft.
- Biology lessons: 24 beginner-oriented lesson drafts, mapped to the syllabus units; independent academic review is pending.
- Biology original objective practice: 240 items across the 24 units; items are explicitly marked as original and unverified.
- WAEC-style theory: ten original structured-response tasks with model responses, self-check points and locally saved responses; independent review is pending.
- Practice: topic-focused sessions and a full Biology question-bank mode with shuffled question and option order.
- Timed CBT: a 40-question, 30-minute Biology mock with answer navigation, flags, explanations and local active-session recovery logic.
- Revision: 198 offline Biology cards with Again/Hard/Good/Easy scheduling.
- Mistake notebook: offline capture of incorrect quick-practice answers and incorrect/unanswered CBT items, with review status.
- Study planner: adaptive seven-day Biology schedule with configurable daily time target, offline activity completion and shortcuts into lessons, practice and revision.
- Mastery indicator: objective-question evidence threshold of 10 attempts, 8 distinct questions, 2 study days and at least 80% correct; theory and practical evidence are not yet included.
- Practical learning: four Biology prototypes plus introductory Physics and Chemistry simulations.
- Academic approval: pending independent subject review; no generated question is presented as an official past question.
- Other subjects: starter lesson content only; full syllabus mapping and course development remain outstanding.

## Acceptance principles
- Do not equate lesson completion with mastery.
- Never present unverified questions, answer keys or marking rules as official.
- Store source, rights/licence, reviewer, approval status and content version.
- Core downloaded content should remain usable offline.
- Never promise a particular examination score.
