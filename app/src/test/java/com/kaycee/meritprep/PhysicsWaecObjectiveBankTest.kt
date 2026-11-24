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
        assertEquals(
            PhysicsCourse.topics.map { it.id }.toSet(),
            questions.map { it.topicId }.toSet()
        )
        questions.forEach {
            assertTrue(it.id.startsWith("waec-phy-"))
            assertEquals(4, it.options.size)
            assertTrue(it.correctOptionIndex in it.options.indices)
            assertTrue(it.explanation.isNotBlank())
            assertTrue(it.source.contains("MeritPrep original WAEC-style Physics practice"))
            assertTrue(!it.isVerified)
        }
    }

    @Test fun containsNoDuplicateQuestionPrompts() {
        val prompts = PhysicsWaecObjectiveBank.all.map { it.prompt }
        assertEquals(prompts.size, prompts.distinct().size)
    }
}
