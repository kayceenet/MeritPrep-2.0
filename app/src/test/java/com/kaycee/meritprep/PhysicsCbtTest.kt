package com.kaycee.meritprep

import com.kaycee.meritprep.data.CbtSessionEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsCbtTest {
    @Test fun physicsModeUsesFortyQuestionsAndItsOwnLocalSessionKey() {
        assertEquals("PHY", CbtMode.PHYSICS_UTME.sessionSubjectId)
        assertEquals(40, CbtMode.PHYSICS_UTME.questionCount)
        assertEquals(30 * 60, CbtMode.PHYSICS_UTME.durationSeconds)
        assertTrue(CbtMode.PHYSICS_UTME.title.contains("Physics"))
    }

    @Test fun physicsMockSelectsFortyUniqueQuestionsAndShufflesPresentation() {
        val questions = CbtSessionEngine.createQuestions(PhysicsQuestionBank.all, CbtMode.PHYSICS_UTME.questionCount)
        assertEquals(40, questions.size)
        assertEquals(40, questions.map { it.questionId }.distinct().size)
        questions.forEach {
            assertEquals(4, it.options.size)
            assertTrue(it.correctPresentedIndex in it.options.indices)
            assertTrue(it.topicId.startsWith("phy-"))
        }
    }

    @Test fun physicsMockCoversMultipleSyllabusTopics() {
        val questions = CbtSessionEngine.createQuestions(PhysicsQuestionBank.all, 40)
        assertTrue(questions.map { it.topicId }.distinct().size > 10)
    }
}
