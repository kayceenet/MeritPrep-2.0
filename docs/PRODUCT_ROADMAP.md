# MeritPrep 2.0 Product Roadmap

## Product learning cycle
Learn → Explore → Practise → Assess → Correct → Retrieve → Master.

## Release stages
### A. Foundation
Android navigation, subject catalogue, lesson shell, build automation.

### B. Curriculum
Versioned JAMB/WAEC syllabus entities; subject/topic/objective mapping; content provenance, reviewer, approval and revision history.

### C. Learning
Structured lessons, worked examples, diagrams, formative checks, downloadable learning packs and offline progress.

### D. Examination
Timed CBT sessions, question randomisation, answer review, scoring policies, WAEC objective/theory/practical practice and transparent feedback.

### E. Mastery and revision
Topic mastery based on repeated evidence, spaced retrieval, flashcards, error notebook and study planner.

**Implemented in the current alpha:** local revision-card storage, a four-rating spaced-retrieval scheduler, 198 Biology revision cards spanning the mapped Biology topics, a revision screen and unit-tested scheduling rules. Mastery evidence across multiple attempts and question forms, an error notebook and a study planner remain outstanding.

### F. Practical learning
Inventory existing APK assets and lab logic; rebuild Biology, Chemistry and Physics simulations with safe, accessible controls and clear learning objectives.

**Implemented prototype work:** a Biology food-test interpretation engine and an osmosis simulation, with unit tests. These are learning prototypes, not a complete WAEC practical examination system.

### G. Platform operations
Authenticated sync, teacher/admin CMS, analytics, backups, privacy and security review.

### F. Timed CBT practice
A Biology practice mock now selects 40 random original questions from the 240-item bank, shuffles answer options, provides a 30-minute practice timer, question navigation, answer flags, unanswered-question review, scoring and explanations. This is a MeritPrep practice configuration, not a claim about official JAMB timing or an official past-paper set. Session recovery after force-close and multi-subject full-length mocks remain to be implemented.

## Current alpha status

- Biology syllabus map: 24 units represented; each unit resolves to an authored lesson draft.
- Biology lessons: 24 beginner-oriented lesson drafts, mapped to the syllabus units; independent academic review is pending.
- Biology original objective practice: 240 items across the 24 units; items are explicitly marked as original and unverified.
- Practice: topic-focused sessions and a full Biology question-bank mode with shuffled question and option order.
- Revision: 198 offline Biology cards with Again/Hard/Good/Easy scheduling.
- Practical learning: food-test and osmosis prototypes.
- Academic approval: pending independent subject review; no generated question is presented as an official past question.
- Other subjects: starter lesson content only; full syllabus mapping and course development remain outstanding.

## Acceptance principles
- Do not equate lesson completion with mastery.
- Never present unverified questions, answer keys or marking rules as official.
- Store source, rights/licence, reviewer, approval status and content version.
- Core downloaded content should remain usable offline.
- Never promise a particular examination score.
