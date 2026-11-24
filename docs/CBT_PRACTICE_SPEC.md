# MeritPrep timed subject practice CBT

## Current implementation
- Biology JAMB-style mode: 40 questions selected randomly from the original Biology bank.
- Physics JAMB-style mode: 40 questions selected randomly from the 195 original Physics questions mapped across all 39 JAMB Physics topics.
- Answer options are shuffled while preserving the correct answer mapping.
- 30-minute countdown for a MeritPrep practice session.
- Question navigation, horizontal question navigator, answer selection and review flags.
- Submission summary with score, percentage and unanswered count.
- Post-submission review shows the learner's answer, correct answer and explanation.
- Every response is recorded against its actual syllabus topic in local progress storage.

## Important limitations
- The 40-question/30-minute setting is a MeritPrep practice configuration, not an official JAMB or WAEC timing specification.
- Questions are original and unverified; they are not represented as official past questions.
- Biology and Physics have separate practice modes and local session keys. Full multi-subject JAMB sessions, other WAEC paper formats and official subject blueprints require separate implementation and academic verification.
- The active session, shuffled question order, presented options, answers and review flags are stored locally. On reopening the CBT screen, the app restores the session and recalculates remaining time from the saved deadline. The current question position is persisted and restored. Restoring completed-session review after leaving the screen remains a follow-up improvement.
- No readiness score or guaranteed examination outcome is inferred from this mock.

## Acceptance checks
- Question IDs in a session are unique.
- Correct option remains correct after option shuffling.
- Unanswered questions are counted as incorrect in the mock score.
- Topic-level practice statistics use each question's actual topic ID.
- The learner can inspect every correct answer and explanation after submission.