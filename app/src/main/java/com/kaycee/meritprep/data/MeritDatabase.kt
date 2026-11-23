package com.kaycee.meritprep.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.json.JSONArray

class MeritDatabase(context: Context) : SQLiteOpenHelper(context, "meritprep_local.db", null, 9) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE topic_progress (
                topic_id TEXT PRIMARY KEY,
                lessons_completed INTEGER NOT NULL DEFAULT 0,
                questions_attempted INTEGER NOT NULL DEFAULT 0,
                questions_correct INTEGER NOT NULL DEFAULT 0,
                last_studied INTEGER NOT NULL DEFAULT 0,
                demonstrated_mastery INTEGER NOT NULL DEFAULT 0
            )
        """.trimIndent())
        db.execSQL("""
            CREATE TABLE study_notes (
                note_id TEXT PRIMARY KEY,
                topic_id TEXT NOT NULL,
                title TEXT NOT NULL,
                body TEXT NOT NULL,
                updated_at INTEGER NOT NULL
            )
        """.trimIndent())
        db.execSQL("""
            CREATE TABLE revision_cards (
                card_id TEXT PRIMARY KEY,
                topic_id TEXT NOT NULL,
                front TEXT NOT NULL,
                back TEXT NOT NULL,
                due_at INTEGER NOT NULL,
                interval_days INTEGER NOT NULL DEFAULT 0,
                repetitions INTEGER NOT NULL DEFAULT 0,
                ease_factor REAL NOT NULL DEFAULT 2.5,
                lapses INTEGER NOT NULL DEFAULT 0
            )
        """.trimIndent())
        db.execSQL("""
            CREATE TABLE bookmarks (
                item_id TEXT PRIMARY KEY,
                item_type TEXT NOT NULL,
                created_at INTEGER NOT NULL
            )
        """.trimIndent())
        createCompletedLessonsTable(db)
        createCbtTables(db)
        createMistakeTable(db)
        createStudyPlanTables(db)
        createQuestionAttemptsTable(db)
    }

    private fun createCompletedLessonsTable(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS completed_lessons (
                lesson_id TEXT PRIMARY KEY,
                topic_id TEXT NOT NULL,
                completed_at INTEGER NOT NULL
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) createCompletedLessonsTable(db)
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE revision_cards ADD COLUMN ease_factor REAL NOT NULL DEFAULT 2.5")
            db.execSQL("ALTER TABLE revision_cards ADD COLUMN lapses INTEGER NOT NULL DEFAULT 0")
        }
        if (oldVersion < 4) createCbtTables(db)
        if (oldVersion < 5) createMistakeTable(db)
        if (oldVersion < 6) createStudyPlanTables(db)
        if (oldVersion < 7 && !hasColumn(db, "topic_progress", "demonstrated_mastery")) db.execSQL("ALTER TABLE topic_progress ADD COLUMN demonstrated_mastery INTEGER NOT NULL DEFAULT 0")
        if (oldVersion < 8) createQuestionAttemptsTable(db)
        if (oldVersion < 9 && !hasColumn(db, "cbt_sessions", "current_index")) db.execSQL("ALTER TABLE cbt_sessions ADD COLUMN current_index INTEGER NOT NULL DEFAULT 0")
    }

    private fun hasColumn(db: SQLiteDatabase, table: String, column: String): Boolean =
        db.rawQuery("PRAGMA table_info($table)", null).use { cursor ->
            val nameIndex = cursor.getColumnIndex("name")
            var found = false
            while (cursor.moveToNext()) if (cursor.getString(nameIndex) == column) found = true
            found
        }

    private fun createCbtTables(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS cbt_sessions (
                session_id TEXT PRIMARY KEY,
                subject_id TEXT NOT NULL,
                started_at INTEGER NOT NULL,
                ends_at INTEGER NOT NULL,
                total_questions INTEGER NOT NULL,
                status TEXT NOT NULL,
                current_index INTEGER NOT NULL DEFAULT 0,
                score INTEGER,
                submitted_at INTEGER
            )
        """.trimIndent())
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS cbt_session_questions (
                session_id TEXT NOT NULL,
                question_id TEXT NOT NULL,
                display_order INTEGER NOT NULL,
                options_json TEXT NOT NULL,
                correct_index INTEGER NOT NULL,
                PRIMARY KEY(session_id, question_id)
            )
        """.trimIndent())
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS cbt_answers (
                session_id TEXT NOT NULL,
                question_id TEXT NOT NULL,
                selected_index INTEGER,
                flagged INTEGER NOT NULL DEFAULT 0,
                updated_at INTEGER NOT NULL,
                PRIMARY KEY(session_id, question_id)
            )
        """.trimIndent())
    }

    private fun createMistakeTable(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS mistake_notebook (
                mistake_id TEXT PRIMARY KEY,
                question_id TEXT NOT NULL,
                topic_id TEXT NOT NULL,
                prompt TEXT NOT NULL,
                selected_answer TEXT NOT NULL,
                correct_answer TEXT NOT NULL,
                explanation TEXT NOT NULL,
                created_at INTEGER NOT NULL,
                is_resolved INTEGER NOT NULL DEFAULT 0
            )
        """.trimIndent())
    }

    fun saveMistake(note: MistakeNote) {
        val values = ContentValues().apply {
            put("mistake_id", note.mistakeId)
            put("question_id", note.questionId)
            put("topic_id", note.topicId)
            put("prompt", note.prompt)
            put("selected_answer", note.selectedAnswer)
            put("correct_answer", note.correctAnswer)
            put("explanation", note.explanation)
            put("created_at", note.createdAt)
            put("is_resolved", if (note.isResolved) 1 else 0)
        }
        writableDatabase.insertOrThrow("mistake_notebook", null, values)
    }

    fun getMistakes(resolvedOnly: Boolean = false, limit: Int = 200): List<MistakeNote> {
        val selection = if (resolvedOnly) "WHERE is_resolved = 1" else "WHERE is_resolved = 0"
        return readableDatabase.rawQuery(
            """SELECT mistake_id, question_id, topic_id, prompt, selected_answer, correct_answer,
                      explanation, created_at, is_resolved
               FROM mistake_notebook $selection ORDER BY created_at DESC LIMIT ?""".trimIndent(),
            arrayOf(limit.coerceIn(1, 1000).toString())
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) add(
                    MistakeNote(
                        mistakeId = cursor.getString(0),
                        questionId = cursor.getString(1),
                        topicId = cursor.getString(2),
                        prompt = cursor.getString(3),
                        selectedAnswer = cursor.getString(4),
                        correctAnswer = cursor.getString(5),
                        explanation = cursor.getString(6),
                        createdAt = cursor.getLong(7),
                        isResolved = cursor.getInt(8) == 1
                    )
                )
            }
        }
    }

    fun setMistakeResolved(mistakeId: String, resolved: Boolean) {
        val values = ContentValues().apply { put("is_resolved", if (resolved) 1 else 0) }
        writableDatabase.update("mistake_notebook", values, "mistake_id = ?", arrayOf(mistakeId))
    }

    fun getUnresolvedMistakeCount(): Int =
        readableDatabase.rawQuery("SELECT COUNT(*) FROM mistake_notebook WHERE is_resolved = 0", null)
            .use { if (it.moveToFirst()) it.getInt(0) else 0 }

    private fun insertQuestionAttempt(
        db: SQLiteDatabase,
        questionId: String,
        topicId: String,
        isCorrect: Boolean,
        attemptedAt: Long
    ) {
        val values = ContentValues().apply {
            put("attempt_id", java.util.UUID.randomUUID().toString())
            put("question_id", questionId)
            put("topic_id", topicId)
            put("is_correct", if (isCorrect) 1 else 0)
            put("attempted_at", attemptedAt)
        }
        db.insertOrThrow("question_attempts", null, values)
    }

    private fun updateMasteryEvidence(db: SQLiteDatabase, topicId: String) {
        val evidence = db.rawQuery(
            """SELECT COUNT(*), COUNT(DISTINCT question_id), COALESCE(SUM(is_correct), 0),
                      COUNT(DISTINCT date(attempted_at / 1000, 'unixepoch', 'localtime'))
               FROM question_attempts WHERE topic_id = ?""".trimIndent(),
            arrayOf(topicId)
        ).use { cursor ->
            cursor.moveToFirst()
            MasteryEvidence(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3))
        }
        val values = ContentValues().apply {
            put("demonstrated_mastery", if (MasteryPolicy.isDemonstrated(evidence)) 1 else 0)
        }
        db.update("topic_progress", values, "topic_id = ?", arrayOf(topicId))
    }

    private fun incrementTopicProgress(
        db: SQLiteDatabase,
        topicId: String,
        questionAttempts: Int = 0,
        questionsCorrect: Int = 0,
        lessonsCompleted: Int = 0,
        studiedAt: Long = System.currentTimeMillis()
    ) {
        val exists = db.rawQuery(
            "SELECT 1 FROM topic_progress WHERE topic_id = ? LIMIT 1",
            arrayOf(topicId)
        ).use { it.moveToFirst() }
        if (exists) {
            db.execSQL(
                """UPDATE topic_progress SET
                     questions_attempted = questions_attempted + ?,
                     questions_correct = questions_correct + ?,
                     lessons_completed = lessons_completed + ?,
                     last_studied = ?
                   WHERE topic_id = ?""".trimIndent(),
                arrayOf(questionAttempts, questionsCorrect, lessonsCompleted, studiedAt, topicId)
            )
        } else {
            val values = ContentValues().apply {
                put("topic_id", topicId)
                put("questions_attempted", questionAttempts)
                put("questions_correct", questionsCorrect)
                put("lessons_completed", lessonsCompleted)
                put("last_studied", studiedAt)
            }
            db.insertOrThrow("topic_progress", null, values)
        }
    }

    fun recordQuestionAttempt(topicId: String, isCorrect: Boolean, questionId: String? = null, studiedAt: Long = System.currentTimeMillis()) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            incrementTopicProgress(
                db, topicId, questionAttempts = 1,
                questionsCorrect = if (isCorrect) 1 else 0,
                studiedAt = studiedAt
            )
            if (questionId != null) {
                insertQuestionAttempt(db, questionId, topicId, isCorrect, studiedAt)
                updateMasteryEvidence(db, topicId)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun markLessonCompleted(lessonId: String, studiedAt: Long = System.currentTimeMillis()) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            val values = ContentValues().apply {
                put("lesson_id", lessonId)
                put("topic_id", lessonId)
                put("completed_at", studiedAt)
            }
            val inserted = db.insertWithOnConflict(
                "completed_lessons", null, values, SQLiteDatabase.CONFLICT_IGNORE
            )
            if (inserted != -1L) {
                incrementTopicProgress(db, lessonId, lessonsCompleted = 1, studiedAt = studiedAt)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun isLessonCompleted(lessonId: String): Boolean =
        readableDatabase.rawQuery(
            "SELECT 1 FROM completed_lessons WHERE lesson_id = ? LIMIT 1",
            arrayOf(lessonId)
        ).use { it.moveToFirst() }

    fun getNoteBody(noteId: String): String? =
        readableDatabase.rawQuery(
            "SELECT body FROM study_notes WHERE note_id = ? LIMIT 1",
            arrayOf(noteId)
        ).use { cursor -> if (cursor.moveToFirst()) cursor.getString(0) else null }

    fun saveNote(noteId: String, topicId: String, title: String, body: String) {
        val values = ContentValues().apply {
            put("note_id", noteId)
            put("topic_id", topicId)
            put("title", title)
            put("body", body)
            put("updated_at", System.currentTimeMillis())
        }
        writableDatabase.insertWithOnConflict("study_notes", null, values, SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun bookmark(itemId: String, itemType: String) {
        val values = ContentValues().apply {
            put("item_id", itemId)
            put("item_type", itemType)
            put("created_at", System.currentTimeMillis())
        }
        writableDatabase.insertWithOnConflict("bookmarks", null, values, SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun removeBookmark(itemId: String) {
        writableDatabase.delete("bookmarks", "item_id = ?", arrayOf(itemId))
    }


    fun seedRevisionCards(cards: List<RevisionCard>) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            cards.forEach { card ->
                val values = ContentValues().apply {
                    put("card_id", card.cardId)
                    put("topic_id", card.topicId)
                    put("front", card.front)
                    put("back", card.back)
                    put("due_at", card.dueAt)
                    put("interval_days", card.intervalDays)
                    put("repetitions", card.repetitions)
                    put("ease_factor", card.easeFactor)
                    put("lapses", card.lapses)
                }
                db.insertWithOnConflict("revision_cards", null, values, SQLiteDatabase.CONFLICT_IGNORE)
            }
            db.setTransactionSuccessful()
        } finally { db.endTransaction() }
    }

    fun getDueRevisionCards(now: Long = System.currentTimeMillis(), limit: Int = 200): List<RevisionCard> =
        readableDatabase.rawQuery(
            "SELECT card_id, topic_id, front, back, due_at, interval_days, repetitions, ease_factor, lapses FROM revision_cards WHERE due_at <= ? ORDER BY due_at ASC LIMIT ?",
            arrayOf(now.toString(), limit.toString())
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) add(RevisionCard(
                    cardId = cursor.getString(0), topicId = cursor.getString(1),
                    front = cursor.getString(2), back = cursor.getString(3),
                    dueAt = cursor.getLong(4), intervalDays = cursor.getInt(5),
                    repetitions = cursor.getInt(6), easeFactor = cursor.getFloat(7).toDouble(),
                    lapses = cursor.getInt(8)
                ))
            }
        }

    fun getRevisionCardCount(): Int =
        readableDatabase.rawQuery("SELECT COUNT(*) FROM revision_cards", null).use {
            if (it.moveToFirst()) it.getInt(0) else 0
        }

    fun reviewRevisionCard(card: RevisionCard) {
        val values = ContentValues().apply {
            put("due_at", card.dueAt); put("interval_days", card.intervalDays)
            put("repetitions", card.repetitions); put("ease_factor", card.easeFactor)
            put("lapses", card.lapses)
        }
        writableDatabase.update("revision_cards", values, "card_id = ?", arrayOf(card.cardId))
    }


    fun createCbtSession(
        sessionId: String,
        subjectId: String,
        startedAt: Long,
        endsAt: Long,
        questions: List<PresentedQuestion>
    ) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            val session = ContentValues().apply {
                put("session_id", sessionId)
                put("subject_id", subjectId)
                put("started_at", startedAt)
                put("ends_at", endsAt)
                put("total_questions", questions.size)
                put("status", "IN_PROGRESS")
            }
            db.insertOrThrow("cbt_sessions", null, session)
            questions.forEachIndexed { index, question ->
                val row = ContentValues().apply {
                    put("session_id", sessionId)
                    put("question_id", question.questionId)
                    put("display_order", index)
                    put("options_json", JSONArray(question.options).toString())
                    put("correct_index", question.correctPresentedIndex)
                }
                db.insertOrThrow("cbt_session_questions", null, row)
            }
            db.setTransactionSuccessful()
        } finally { db.endTransaction() }
    }

    fun updateCbtCurrentIndex(sessionId: String, currentIndex: Int) {
        val values = ContentValues().apply { put("current_index", currentIndex.coerceAtLeast(0)) }
        writableDatabase.update(
            "cbt_sessions",
            values,
            "session_id = ? AND status = 'IN_PROGRESS'",
            arrayOf(sessionId)
        )
    }

    fun saveCbtAnswer(sessionId: String, questionId: String, selectedIndex: Int?, flagged: Boolean) {
        val values = ContentValues().apply {
            put("session_id", sessionId)
            put("question_id", questionId)
            if (selectedIndex == null) putNull("selected_index") else put("selected_index", selectedIndex)
            put("flagged", if (flagged) 1 else 0)
            put("updated_at", System.currentTimeMillis())
        }
        writableDatabase.insertWithOnConflict("cbt_answers", null, values, SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun loadActiveCbtSession(subjectId: String): CbtSessionSnapshot? {
        val db = readableDatabase
        val session = db.rawQuery(
            "SELECT session_id, started_at, ends_at, total_questions, current_index FROM cbt_sessions WHERE subject_id = ? AND status = 'IN_PROGRESS' ORDER BY started_at DESC LIMIT 1",
            arrayOf(subjectId)
        ).use { cursor ->
            if (!cursor.moveToFirst()) null else listOf(
                cursor.getString(0), cursor.getLong(1), cursor.getLong(2), cursor.getInt(3), cursor.getInt(4)
            )
        } ?: return null
        val sessionId = session[0] as String
        val questions = db.rawQuery(
            """SELECT q.question_id, q.options_json, q.correct_index, a.selected_index, COALESCE(a.flagged, 0)
               FROM cbt_session_questions q
               LEFT JOIN cbt_answers a ON a.session_id = q.session_id AND a.question_id = q.question_id
               WHERE q.session_id = ? ORDER BY q.display_order ASC""".trimIndent(),
            arrayOf(sessionId)
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) {
                    val optionsJson = JSONArray(cursor.getString(1))
                    val options = buildList { for (i in 0 until optionsJson.length()) add(optionsJson.getString(i)) }
                    add(CbtQuestionSnapshot(
                        questionId = cursor.getString(0),
                        options = options,
                        correctPresentedIndex = cursor.getInt(2),
                        selectedIndex = if (cursor.isNull(3)) null else cursor.getInt(3),
                        flagged = cursor.getInt(4) == 1
                    ))
                }
            }
        }
        return CbtSessionSnapshot(
            sessionId = sessionId,
            startedAt = session[1] as Long,
            endsAt = session[2] as Long,
            totalQuestions = session[3] as Int,
            currentIndex = session[4] as Int,
            questions = questions
        )
    }

    fun completeCbtSession(
        sessionId: String,
        score: Int,
        attempts: List<QuestionAttemptRecord>,
        mistakes: List<MistakeNote> = emptyList(),
        submittedAt: Long = System.currentTimeMillis()
    ) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            val isActive = db.rawQuery(
                "SELECT 1 FROM cbt_sessions WHERE session_id = ? AND status = 'IN_PROGRESS' LIMIT 1",
                arrayOf(sessionId)
            ).use { it.moveToFirst() }
            if (!isActive) return
            attempts.forEach { attempt ->
                incrementTopicProgress(
                    db, attempt.topicId, questionAttempts = 1,
                    questionsCorrect = if (attempt.isCorrect) 1 else 0,
                    studiedAt = submittedAt
                )
                insertQuestionAttempt(db, attempt.questionId, attempt.topicId, attempt.isCorrect, submittedAt)
            }
            attempts.map { it.topicId }.toSet().forEach { updateMasteryEvidence(db, it) }
            mistakes.forEach { note ->
                val mistakeValues = ContentValues().apply {
                    put("mistake_id", note.mistakeId)
                    put("question_id", note.questionId)
                    put("topic_id", note.topicId)
                    put("prompt", note.prompt)
                    put("selected_answer", note.selectedAnswer)
                    put("correct_answer", note.correctAnswer)
                    put("explanation", note.explanation)
                    put("created_at", note.createdAt)
                    put("is_resolved", if (note.isResolved) 1 else 0)
                }
                db.insertOrThrow("mistake_notebook", null, mistakeValues)
            }
            val values = ContentValues().apply {
                put("status", "SUBMITTED")
                put("score", score)
                put("submitted_at", submittedAt)
            }
            db.update("cbt_sessions", values, "session_id = ? AND status = 'IN_PROGRESS'", arrayOf(sessionId))
            db.setTransactionSuccessful()
        } finally { db.endTransaction() }
    }

    fun abandonCbtSession(sessionId: String) {
        val values = ContentValues().apply { put("status", "ABANDONED") }
        writableDatabase.update("cbt_sessions", values, "session_id = ? AND status = 'IN_PROGRESS'", arrayOf(sessionId))
    }

    fun getAllTopicProgress(): List<TopicProgress> =
        readableDatabase.rawQuery(
            """SELECT topic_id, lessons_completed, questions_attempted, questions_correct,
                      last_studied, demonstrated_mastery
               FROM topic_progress ORDER BY last_studied DESC""".trimIndent(),
            null
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) add(
                    TopicProgress(
                        topicId = cursor.getString(0),
                        lessonsCompleted = cursor.getInt(1),
                        questionsAttempted = cursor.getInt(2),
                        questionsCorrect = cursor.getInt(3),
                        lastStudiedEpochMillis = cursor.getLong(4),
                        demonstratedMastery = cursor.getInt(5) == 1
                    )
                )
            }
        }

    private fun createQuestionAttemptsTable(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS question_attempts (
                attempt_id TEXT PRIMARY KEY,
                question_id TEXT NOT NULL,
                topic_id TEXT NOT NULL,
                is_correct INTEGER NOT NULL,
                attempted_at INTEGER NOT NULL
            )
        """.trimIndent())
        db.execSQL("CREATE INDEX IF NOT EXISTS idx_question_attempts_topic ON question_attempts(topic_id, attempted_at)")
    }

    private fun createStudyPlanTables(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS study_plans (
                plan_id TEXT PRIMARY KEY,
                created_at INTEGER NOT NULL,
                minutes_per_day INTEGER NOT NULL,
                is_active INTEGER NOT NULL DEFAULT 1
            )
        """.trimIndent())
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS study_plan_items (
                item_id TEXT PRIMARY KEY,
                plan_id TEXT NOT NULL,
                day_index INTEGER NOT NULL,
                topic_id TEXT NOT NULL,
                topic_title TEXT NOT NULL,
                activity_type TEXT NOT NULL,
                estimated_minutes INTEGER NOT NULL,
                is_completed INTEGER NOT NULL DEFAULT 0,
                completed_at INTEGER
            )
        """.trimIndent())
    }

    fun createStudyPlan(planId: String, createdAt: Long, minutesPerDay: Int, items: List<StudyPlanItem>) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            db.execSQL("UPDATE study_plans SET is_active = 0 WHERE is_active = 1")
            val plan = ContentValues().apply {
                put("plan_id", planId)
                put("created_at", createdAt)
                put("minutes_per_day", minutesPerDay)
                put("is_active", 1)
            }
            db.insertOrThrow("study_plans", null, plan)
            items.forEach { item ->
                val row = ContentValues().apply {
                    put("item_id", item.itemId)
                    put("plan_id", planId)
                    put("day_index", item.dayIndex)
                    put("topic_id", item.topicId)
                    put("topic_title", item.topicTitle)
                    put("activity_type", item.activityType)
                    put("estimated_minutes", item.estimatedMinutes)
                    put("is_completed", 0)
                }
                db.insertOrThrow("study_plan_items", null, row)
            }
            db.setTransactionSuccessful()
        } finally { db.endTransaction() }
    }

    fun getActiveStudyPlan(): StudyPlanSnapshot? {
        val db = readableDatabase
        val header = db.rawQuery(
            "SELECT plan_id, created_at, minutes_per_day FROM study_plans WHERE is_active = 1 ORDER BY created_at DESC LIMIT 1",
            null
        ).use { cursor ->
            if (!cursor.moveToFirst()) null else Triple(cursor.getString(0), cursor.getLong(1), cursor.getInt(2))
        } ?: return null
        val items = db.rawQuery(
            """SELECT item_id, day_index, topic_id, topic_title, activity_type, estimated_minutes, is_completed
               FROM study_plan_items WHERE plan_id = ? ORDER BY day_index, rowid""".trimIndent(),
            arrayOf(header.first)
        ).use { cursor ->
            buildList {
                while (cursor.moveToNext()) add(
                    StudyPlanItem(
                        itemId = cursor.getString(0),
                        dayIndex = cursor.getInt(1),
                        topicId = cursor.getString(2),
                        topicTitle = cursor.getString(3),
                        activityType = cursor.getString(4),
                        estimatedMinutes = cursor.getInt(5),
                        isCompleted = cursor.getInt(6) == 1
                    )
                )
            }
        }
        return StudyPlanSnapshot(header.first, header.second, header.third, items)
    }

    fun setStudyPlanItemCompleted(itemId: String, completed: Boolean) {
        val values = ContentValues().apply {
            put("is_completed", if (completed) 1 else 0)
            if (completed) put("completed_at", System.currentTimeMillis()) else putNull("completed_at")
        }
        writableDatabase.update("study_plan_items", values, "item_id = ?", arrayOf(itemId))
    }

    fun getTopicProgressCount(): Int =
        readableDatabase.rawQuery("SELECT COUNT(*) FROM topic_progress", null).use { cursor ->
            if (cursor.moveToFirst()) cursor.getInt(0) else 0
        }

    fun getCompletedLessonCount(): Int =
        readableDatabase.rawQuery("SELECT COUNT(*) FROM completed_lessons", null).use { cursor ->
            if (cursor.moveToFirst()) cursor.getInt(0) else 0
        }

    fun getQuestionAttemptCount(): Int =
        readableDatabase.rawQuery("SELECT COALESCE(SUM(questions_attempted), 0) FROM topic_progress", null).use { cursor ->
            if (cursor.moveToFirst()) cursor.getInt(0) else 0
        }

    fun getCorrectAnswerCount(): Int =
        readableDatabase.rawQuery("SELECT COALESCE(SUM(questions_correct), 0) FROM topic_progress", null).use { cursor ->
            if (cursor.moveToFirst()) cursor.getInt(0) else 0
        }
}
