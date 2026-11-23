package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyQuestionBankTest {
    @Test
    fun cellBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.cellBiology
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-cell", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun classificationBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.classification
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-classification", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun adaptationBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.adaptation
        assertTrue(questions.size >= 8)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-adaptation", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun plantAnatomyBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.plantAnatomy
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-plant-anatomy", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun nutritionBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.nutrition
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-nutrition", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun transportBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.transport
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-transport", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun respirationBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.respiration
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-respiration", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun excretionBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.excretion
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-excretion", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
}







