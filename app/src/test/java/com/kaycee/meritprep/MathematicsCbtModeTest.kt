package com.kaycee.meritprep

import com.kaycee.meritprep.data.CbtSessionEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class MathematicsCbtModeTest {
    @Test fun mathematicsModeUsesFortyQuestionsAndMeritPrepPracticeDuration() {
        val mode = CbtMode.MATHEMATICS_UTME
        assertEquals("MATH", mode.sessionSubjectId)
        assertEquals(40, mode.questionCount)
        assertEquals(30 * 60, mode.durationSeconds)
    }

    @Test fun mathematicsQuestionPoolCreatesFortyUniquePresentedQuestions() {
        val presented = CbtSessionEngine.createQuestions(
            pool = MathematicsQuestionBank.all,
            count = CbtMode.MATHEMATICS_UTME.questionCount,
            random = Random(2026)
        )
        assertEquals(40, presented.size)
        assertEquals(40, presented.map { it.questionId }.distinct().size)
        assertTrue(presented.all { it.options.size == 4 })
    }
}
