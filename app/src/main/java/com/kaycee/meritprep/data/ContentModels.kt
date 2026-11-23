package com.kaycee.meritprep.data

enum class ExamTrack { JAMB_UTME, WAEC_WASSCE }

data class CurriculumSubject(
    val id: String,
    val name: String,
    val examTracks: Set<ExamTrack>,
    val syllabusVersion: String
)

data class CurriculumTopic(
    val id: String,
    val subjectId: String,
    val title: String,
    val objectives: List<String>,
    val sequence: Int
)

data class Lesson(
    val id: String,
    val topicId: String,
    val title: String,
    val introduction: String,
    val sections: List<LessonSection>,
    val estimatedMinutes: Int,
    val contentVersion: Int,
    val reviewStatus: ReviewStatus
)

data class LessonSection(
    val heading: String,
    val body: String,
    val keyPoint: String? = null
)

enum class ReviewStatus { DRAFT, IN_REVIEW, APPROVED, RETIRED }

data class AssessmentQuestion(
    val id: String,
    val topicId: String,
    val prompt: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String,
    val source: String,
    val isVerified: Boolean
) {
    init {
        require(options.size >= 2) { "A question must have at least two options." }
        require(correctOptionIndex in options.indices) { "Correct option index is outside the options list." }
    }
}

data class AnswerRecord(
    val questionId: String,
    val selectedOptionIndex: Int,
    val isCorrect: Boolean
)

data class TopicProgress(
    val topicId: String,
    val lessonsCompleted: Int,
    val questionsAttempted: Int,
    val questionsCorrect: Int,
    val lastStudiedEpochMillis: Long,
    val demonstratedMastery: Boolean
)

 
data class RevisionCard(
    val cardId: String,
    val topicId: String,
    val front: String,
    val back: String,
    val dueAt: Long,
    val intervalDays: Int = 0,
    val repetitions: Int = 0,
    val easeFactor: Double = 2.5,
    val lapses: Int = 0
)
