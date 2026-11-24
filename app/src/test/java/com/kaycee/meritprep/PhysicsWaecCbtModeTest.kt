package com.kaycee.meritprep

import com.kaycee.meritprep.data.CbtSessionEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertEquals as assertEqualsAlias
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class PhysicsWaecCbtModeTest {
    @Test fun waecPhysicsModeUsesFiftyQuestionsAndWorkingReferenceDuration() {
        val mode = CbtMode.WAEC_PHYSICS_OBJECTIVE
        assertEquals(50, mode.questionCount)
        assertEquals(75 * 60, mode.durationSeconds)
        assertEquals("PHY_WAEC", mode.sessionSubjectId)
    }

    @Test fun objectivePoolCanCreateACompleteShuffledSession() {
        val presented = CbtSessionEngine.createQuestions(
            pool = PhysicsWaecObjectiveBank.all,
            count = CbtMode.WAEC_PHYSICS_OBJECTIVE.questionCount,
            random = Random(2026)
        )
        assertEquals(50, presented.size)
        assertEquals(50, presented.map { it.questionId }.distinct().size)
        assertTrue(presented.all { it.options.size == 4 })
    }
}
