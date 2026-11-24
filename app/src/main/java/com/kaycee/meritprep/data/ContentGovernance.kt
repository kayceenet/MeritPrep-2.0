package com.kaycee.meritprep.data

enum class ContentKind { LESSON, QUESTION, DIAGRAM, FLASHCARD, THEORY_TASK, PRACTICAL_ACTIVITY }
enum class ContentRightsStatus { ORIGINAL, LICENSED, PUBLIC_DOMAIN, PERMISSION_REQUIRED, UNKNOWN }

data class ContentProvenance(
    val contentId: String,
    val kind: ContentKind,
    val subjectId: String,
    val topicId: String,
    val syllabusVersion: String,
    val contentVersion: Int,
    val sourceDescription: String,
    val rightsStatus: ContentRightsStatus,
    val author: String,
    val reviewStatus: ReviewStatus,
    val reviewer: String? = null,
    val reviewedAtEpochMillis: Long? = null,
    val officialSourceVerified: Boolean = false,
    val examYear: Int? = null,
    val examPaper: String? = null,
    val examQuestionNumber: String? = null
)

data class ContentPublicationDecision(
    val publishable: Boolean,
    val blockers: List<String>
)

object ContentGovernancePolicy {
    fun evaluate(provenance: ContentProvenance): ContentPublicationDecision {
        val blockers = buildList {
            if (provenance.contentId.isBlank()) add("Content ID is required.")
            if (provenance.subjectId.isBlank()) add("Subject mapping is required.")
            if (provenance.topicId.isBlank()) add("Syllabus topic mapping is required.")
            if (provenance.syllabusVersion.isBlank()) add("Syllabus version is required.")
            if (provenance.contentVersion < 1) add("Content version must be positive.")
            if (provenance.sourceDescription.isBlank()) add("Source or provenance description is required.")
            if (provenance.author.isBlank()) add("Author attribution is required.")
            if (provenance.reviewStatus != ReviewStatus.APPROVED) {
                add("Content must pass independent subject review before publication.")
            }
            if (provenance.reviewer.isNullOrBlank()) add("A named reviewer is required.")
            if (provenance.reviewedAtEpochMillis == null || provenance.reviewedAtEpochMillis <= 0L) {
                add("A valid review date is required.")
            }
            if (provenance.rightsStatus == ContentRightsStatus.PERMISSION_REQUIRED ||
                provenance.rightsStatus == ContentRightsStatus.UNKNOWN
            ) {
                add("Content reuse rights must be established before publication.")
            }
        }
        return ContentPublicationDecision(blockers.isEmpty(), blockers)
    }

    fun mayBeLabelledOfficialPastQuestion(provenance: ContentProvenance): Boolean {
        if (provenance.kind != ContentKind.QUESTION) return false
        if (!evaluate(provenance).publishable) return false
        if (!provenance.officialSourceVerified) return false
        if (provenance.examYear == null || provenance.examYear < 1950) return false
        if (provenance.examPaper.isNullOrBlank()) return false
        if (provenance.examQuestionNumber.isNullOrBlank()) return false
        return true
    }
}
