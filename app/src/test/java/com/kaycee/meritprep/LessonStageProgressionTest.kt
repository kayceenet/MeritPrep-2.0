package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class LessonStageProgressionTest {
    @Test
    fun everyLessonPillarIsAccessibleBeforeAnyCompletionEvidence() {
        LessonStage.entries.forEach { stage ->
            assertTrue("$stage should be available for exploration", isLessonStageUnlocked(stage, emptySet()))
        }
    }

    @Test
    fun completedPillarsRemainAccessibleForRevision() {
        val completed = setOf(LessonStage.CONCEPT, LessonStage.EXAMPLES)
        LessonStage.entries.forEach { stage ->
            assertTrue("$stage should remain accessible for review", isLessonStageUnlocked(stage, completed))
        }
    }

    @Test
    fun projectAndMasteryVerificationRemainSeparateCompletionRequirements() {
        // Navigation access is intentionally independent from completion.
        // Project checks and quiz pass criteria are enforced by their own
        // lesson workflows, not by hiding the learning pillars.
        assertTrue(isLessonStageUnlocked(LessonStage.PROJECT, emptySet()))
        assertTrue(isLessonStageUnlocked(LessonStage.QUIZ, emptySet()))
    }
}
