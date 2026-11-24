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
        assertTrue(questions.size >= 10)
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
    @Test
    fun humanEnvironmentBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.humanEnvironment
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-environment", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun variationBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.variation
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-variation", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun heredityBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.heredity
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-heredity", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun biotechnologyBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.biotechnology
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-biotechnology", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun evolutionTheoryBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.evolutionTheory
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-evolution-theory", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }
    @Test
    fun evolutionEvidenceBankHasUniqueIdsAndValidKeys() {
        val questions = BiologyQuestionBank.evolutionEvidence
        assertTrue(questions.size >= 10)
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
        questions.forEach { question ->
            assertEquals("bio-evolution-evidence", question.topicId)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.options.size >= 4)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.explanation.isNotBlank())
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original practice item"))
        }
    }

    @Test
    fun completeBankHasNoDuplicatePromptsOrRepeatedOptionsWithinAnItem() {
        val questions = BiologyQuestionBank.all
        assertEquals(244, questions.size)
        assertEquals(questions.size, questions.map { it.prompt.trim().lowercase() }.toSet().size)
        questions.forEach { question ->
            assertTrue(question.options.all { it.isNotBlank() })
            assertEquals(
                "Repeated distractor in ${question.id}",
                question.options.size,
                question.options.map { it.trim().lowercase() }.toSet().size
            )
        }
    }


    @Test
    fun correctAnswerPositionsAreBalancedAcrossTheCompleteBank() {
        val questions = BiologyQuestionBank.all
        val counts = questions.groupingBy { it.correctOptionIndex }.eachCount()

        assertEquals(setOf(0, 1, 2, 3), counts.keys)
        val frequencies = (0..3).map { counts[it] ?: 0 }
        assertTrue(
            "Correct answers should be distributed evenly across A-D",
            frequencies.maxOrNull()!! - frequencies.minOrNull()!! <= 1
        )
    }

    @Test
    fun anatomyQuestionsAreMappedToTheirRelevantBiologyTopics() {
        assertTrue(BiologyQuestionBank.plantAnatomy.any { it.id == "bio-plant-007" && it.prompt.contains("root", ignoreCase = true) })
        assertTrue(BiologyQuestionBank.plantAnatomy.any { it.id == "bio-plant-008" && it.prompt.contains("dicot stem", ignoreCase = true) })
        assertTrue(BiologyQuestionBank.nutrition.any { it.id == "bio-nut-011" && it.prompt.contains("villi", ignoreCase = true) })
        assertTrue(BiologyQuestionBank.respiration.any { it.id == "bio-resp-011" && it.prompt.contains("alveoli", ignoreCase = true) })
        assertFalse(BiologyQuestionBank.plantAnatomy.any { it.prompt.contains("alveoli", ignoreCase = true) || it.prompt.contains("villi", ignoreCase = true) })
    }

    @Test
    fun selectedBiologyItemsUseTopicRelevantDistractors() {
        val photosynthesis = BiologyQuestionBank.nutrition.first { it.id == "bio-nut-010" }
        assertEquals(setOf("Light intensity", "Temperature", "Carbon dioxide concentration", "Water availability"), photosynthesis.options.toSet())

        val habitat = BiologyQuestionBank.all.first { it.id == "bio-hab-001" }
        assertTrue(habitat.options.any { it.contains("niche", ignoreCase = true) })
        assertTrue(habitat.options.any { it.contains("community", ignoreCase = true) })

        val porosity = BiologyQuestionBank.all.first { it.id == "bio-soil-005" }
        assertTrue(porosity.options.any { it.contains("bulk volume", ignoreCase = true) })
        assertTrue(porosity.options.any { it.contains("water", ignoreCase = true) })
    }

}