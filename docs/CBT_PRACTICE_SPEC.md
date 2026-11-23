# MeritPrep timed Biology practice CBT

## Current implementation
- 40 questions selected randomly from the 240 original Biology question bank.
- Answer options are shuffled while preserving the correct answer mapping.
- 30-minute countdown for a MeritPrep practice session.
- Question navigation, horizontal question navigator, answer selection and review flags.
- Submission summary with score, percentage and unanswered count.
- Post-submission review shows the learner's answer, correct answer and explanation.
- Every response is recorded against its actual syllabus topic in local progress storage.

## Important limitations
- The 40-question/30-minute setting is a MeritPrep practice configuration, not an official JAMB or WAEC timing specification.
- Questions are original and unverified; they are not represented as official past questions.
- The current mock is Biology-only. Full JAMB multi-subject sessions, WAEC paper formats and subject-specific official blueprints require separate implementation and academic verification.
- The current session is held in memory. If the app is force-closed or the process is killed, an in-progress session is not yet restored. Persistent session recovery is a next engineering task.
- No readiness score or guaranteed examination outcome is inferred from this mock.

## Acceptance checks
- Question IDs in a session are unique.
- Correct option remains correct after option shuffling.
- Unanswered questions are counted as incorrect in the mock score.
- Topic-level practice statistics use each question's actual topic ID.
- The learner can inspect every correct answer and explanation after submission.