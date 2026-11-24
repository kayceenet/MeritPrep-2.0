package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsQuestionBankTest {
    @Test fun containsFortyOriginalQuestionsAcrossEightDevelopedTopics() {
        assertEquals(40, PhysicsQuestionBank.all.size)
        assertEquals(8, PhysicsCourse.topics.count { !it.isSyllabusOutline })
        PhysicsCourse.topics.filterNot { it.isSyllabusOutline }.forEach { topic ->
            assertEquals(5, PhysicsQuestionBank.forTopic(topic.id).size)
        }
    }

    @Test fun questionIdsAreUniqueAndOptionsHaveOneValidKey() {
        val questions = PhysicsQuestionBank.all
        assertEquals(questions.size, questions.map { it.id }.distinct().size)
        questions.forEach {
            assertTrue(it.prompt.isNotBlank())
            assertEquals(4, it.options.size)
            assertTrue(it.correctOptionIndex in it.options.indices)
            assertTrue(it.explanation.isNotBlank())
            assertFalse(it.isVerified)
            assertTrue(it.source.startsWith("MeritPrep original Physics practice"))
        }
    }

    @Test fun allQuestionsAreMappedToDevelopedPhysicsTopics() {
        val developed = PhysicsCourse.topics.filterNot { it.isSyllabusOutline }.map { it.id }.toSet()
        assertFalse(PhysicsQuestionBank.all.any { it.topicId !in developed })
    }
}
