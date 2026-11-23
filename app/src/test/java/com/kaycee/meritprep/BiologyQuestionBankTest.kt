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
    @Test
    fun supportBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.support
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-support", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun reproductionBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.reproduction
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-reproduction", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun growthBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.growth
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-growth", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun coordinationBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.coordination
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-coordination", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun ecologyFactorsBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.ecologyFactors
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-ecology-factors", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun ecologyInteractionsBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.ecologyInteractions
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-interactions", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun habitatsBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.habitats
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-habitats", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun nigerianBiomesBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.nigerianBiomes
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-biomes", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun populationsBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.populations
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-populations", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun soilBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.soil
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-soil", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
}

















