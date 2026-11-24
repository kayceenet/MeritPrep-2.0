package com.kaycee.meritprep.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ContentGovernancePolicyTest {
    private fun approved(
        kind: ContentKind = ContentKind.LESSON,
        rights: ContentRightsStatus = ContentRightsStatus.ORIGINAL
    ) = ContentProvenance(
        contentId = "bio-cell-v1",
        kind = kind,
        subjectId = "BIO",
        topicId = "bio-cell",
        syllabusVersion = "2026.1",
        contentVersion = 1,
        sourceDescription = "Original MeritPrep teaching content mapped to the syllabus.",
        rightsStatus = rights,
        author = "MeritPrep content team",
        reviewStatus = ReviewStatus.APPROVED,
        reviewer = "Qualified Biology reviewer",
        reviewedAtEpochMillis = 1_800_000_000_000L
    )

    @Test fun approvedOriginalContentWithCompleteProvenanceCanBePublished() {
        val decision = ContentGovernancePolicy.evaluate(approved())
        assertTrue(decision.publishable)
        assertTrue(decision.blockers.isEmpty())
    }

    @Test fun draftContentCannotBePublishedEvenWhenOtherMetadataExists() {
        val decision = ContentGovernancePolicy.evaluate(
            approved().copy(reviewStatus = ReviewStatus.DRAFT)
        )
        assertFalse(decision.publishable)
        assertTrue(decision.blockers.any { it.contains("independent subject review") })
    }

    @Test fun unknownOrUnclearedRightsBlockPublication() {
        listOf(ContentRightsStatus.UNKNOWN, ContentRightsStatus.PERMISSION_REQUIRED).forEach { rights ->
            assertFalse(ContentGovernancePolicy.evaluate(approved(rights = rights)).publishable)
        }
    }

    @Test fun missingReviewerAndReviewDateAreReported() {
        val decision = ContentGovernancePolicy.evaluate(
            approved().copy(reviewer = " ", reviewedAtEpochMillis = null)
        )
        assertEquals(2, decision.blockers.size)
    }

    @Test fun originalQuestionCannotBeMislabelledAsAnOfficialPastQuestion() {
        val original = approved(kind = ContentKind.QUESTION)
        assertFalse(ContentGovernancePolicy.mayBeLabelledOfficialPastQuestion(original))
    }

    @Test fun officialQuestionLabelRequiresVerifiedExamPaperAndQuestionProvenance() {
        val verified = approved(kind = ContentKind.QUESTION).copy(
            officialSourceVerified = true,
            examYear = 2024,
            examPaper = "Biology Paper 1",
            examQuestionNumber = "12"
        )
        assertTrue(ContentGovernancePolicy.mayBeLabelledOfficialPastQuestion(verified))
        assertFalse(
            ContentGovernancePolicy.mayBeLabelledOfficialPastQuestion(
                verified.copy(examQuestionNumber = null)
            )
        )
        assertFalse(
            ContentGovernancePolicy.mayBeLabelledOfficialPastQuestion(
                verified.copy(officialSourceVerified = false)
            )
        )
    }

    @Test fun nonQuestionContentCannotReceivePastQuestionLabel() {
        val lesson = approved().copy(
            officialSourceVerified = true,
            examYear = 2024,
            examPaper = "Biology Paper 1",
            examQuestionNumber = "12"
        )
        assertFalse(ContentGovernancePolicy.mayBeLabelledOfficialPastQuestion(lesson))
    }
}
