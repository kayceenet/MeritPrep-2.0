# Local database schema and migration guide

## Storage model
MeritPrep currently uses an offline-first SQLite database named `meritprep_local.db`. The database is private to the app sandbox and is not currently synchronised to a cloud service.

## Tables
- `topic_progress`: lesson counts, question attempts, correct answers, last study time and a separate demonstrated-mastery flag.
- `question_attempts`: per-question attempt history used to calculate evidence-based topic mastery.
- `completed_lessons`: idempotent lesson-completion records.
- `study_notes`: learner notes linked to syllabus topic IDs.
- `revision_cards`: offline flashcards with due time, interval, repetition count, ease factor and lapse count.
- `bookmarks`: saved content references.
- `cbt_sessions`: timed mock metadata, deadline, status and final score.
- `cbt_session_questions`: question order, presented option order and correct presented index for session restoration.
- `cbt_answers`: selected option and review flag for each question.
- `mistake_notebook`: incorrect and unanswered responses with correction, explanation and review status.
- `study_plans`: active/inactive weekly study plans and daily time target.
- `study_plan_items`: ordered lesson, practice and flashcard tasks with completion state.

## Schema version
Current database version: 9.

Migration history:
- v2: add completed-lesson tracking.
- v3: add spaced-repetition ease factor and lapse count to revision cards.
- v4: add timed CBT session and answer persistence.
- v5: add the mistake notebook.
- v6: add weekly study-plan storage.
- v7: add demonstrated-mastery state to topic progress. The migration checks the table schema before adding the column so installations that already contain it do not fail.
- v8: add question-level attempt history to support distinct-question and multi-day mastery evidence.
- v9: add the current question index to active CBT session metadata.

## Data integrity rules
- Lesson completion is stored separately from demonstrated mastery.
- Topic mastery is marked only after at least 10 recorded attempts, at least 8 distinct questions, activity across at least 2 calendar days and at least 80% accuracy. This is an internal learning signal, not an official examination prediction.
- CBT answer order and correct presented index are stored together so option shuffling does not corrupt scoring after restoration.
- CBT submission, topic-level attempt updates and mistake capture run in one SQLite transaction.
- Practice attempts are recorded against each question's actual syllabus topic ID.
- A study-plan checkbox records a learner action, not evidence of mastery.
- Mistake entries remain local and can be marked reviewed without deleting the learning record.

## Operational notes
- Do not put API keys, passwords or other secrets in the APK or local content database.
- Backups and device-to-device transfer are not yet implemented; uninstalling the app may remove local progress.
- Database migrations must be tested against both a fresh install and an existing database from the previous version.
- Avoid destructive migrations. If a future schema change requires data transformation, use a transaction and preserve learner progress.