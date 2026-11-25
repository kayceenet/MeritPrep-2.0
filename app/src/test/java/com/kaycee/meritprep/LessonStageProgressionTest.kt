package com.kaycee.meritprep

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LessonStageProgressionTest {
    @Test
    fun unlocksPillarsOnlyAfterEveryEarlierPillarIsComplete() {
        assertTrue(isLessonStageUnlocked(LessonStage.CONCEPT, emptySet()))
        assertFalse(isLessonStageUnlocked(LessonStage.EXAMPLES, emptySet()))
        assertTrue(isLessonStageUnlocked(LessonStage.EXAMPLES, setOf(LessonStage.CONCEPT)))
        assertFalse(isLessonStageUnlocked(LessonStage.LAB, setOf(LessonStage.CONCEPT, LessonStage.EXAMPLES)))
        assertTrue(isLessonStageUnlocked(LessonStage.LAB, setOf(LessonStage.CONCEPT, LessonStage.EXAMPLES, LessonStage.APPLICATIONS)))
    }

    @Test
    fun completedPillarsRemainAvailableForRevision() {
        val completed = setOf(LessonStage.CONCEPT, LessonStage.EXAMPLES)
        assertTrue(isLessonStageUnlocked(LessonStage.CONCEPT, completed))
        assertTrue(isLessonStageUnlocked(LessonStage.EXAMPLES, completed))
        assertTrue(isLessonStageUnlocked(LessonStage.APPLICATIONS, completed))
        assertFalse(isLessonStageUnlocked(LessonStage.LAB, completed))
    }
}
