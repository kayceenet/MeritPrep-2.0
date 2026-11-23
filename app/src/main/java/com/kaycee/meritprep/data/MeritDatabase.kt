package com.kaycee.meritprep.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MeritDatabase(context: Context) : SQLiteOpenHelper(context, "meritprep_local.db", null, 3) {
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
    }

    fun recordQuestionAttempt(topicId: String, isCorrect: Boolean, studiedAt: Long = System.currentTimeMillis()) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            db.execSQL(
                """INSERT INTO topic_progress(topic_id, questions_attempted, questions_correct, last_studied)
                   VALUES(?, 1, ?, ?)
                   ON CONFLICT(topic_id) DO UPDATE SET
                     questions_attempted = questions_attempted + 1,
                     questions_correct = questions_correct + excluded.questions_correct,
                     last_studied = excluded.last_studied""".trimIndent(),
                arrayOf(topicId, if (isCorrect) 1 else 0, studiedAt)
            )
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
                db.execSQL(
                    """INSERT INTO topic_progress(topic_id, lessons_completed, last_studied)
                       VALUES(?, 1, ?)
                       ON CONFLICT(topic_id) DO UPDATE SET
                         lessons_completed = lessons_completed + 1,
                         last_studied = excluded.last_studied""".trimIndent(),
                    arrayOf(lessonId, studiedAt)
                )
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

    fun getDueRevisionCards(now: Long = System.currentTimeMillis(), limit: Int = 30): List<RevisionCard> =
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
