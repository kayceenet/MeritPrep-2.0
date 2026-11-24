package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsWaecObjectiveBankTest {
    @Test fun containsFiftyOriginalQuestionsAcrossAllThirtyNineTopics() {
        val questions = PhysicsWaecObjectiveBank.all
        assertEquals(50, questions.size)
        assertEquals(50, questions.map { it.id }.distinct().size)
        assertEquals(39, questions.map { it.topicId }.distinct().size)
        questions.forEach {
            assertTrue(it.id.startsWith("waec-phy-"))
            assertEquals(4, it.options.size)
            assertTrue(it.correctIndex in it.options.indices)
            assertTrue(it.explanation.isNotBlank())
            assertTrue(it.sourceLabel.contains("MeritPrep original WAEC-style Physics practice"))
            assertTrue(!it.isOfficial)
        }
    }

    @Test fun containsNoDuplicateQuestionPrompts() {
        val prompts = PhysicsWaecObjectiveBank.all.map { it.prompt }
        assertEquals(prompts.size, prompts.distinct().size)
    }
}
