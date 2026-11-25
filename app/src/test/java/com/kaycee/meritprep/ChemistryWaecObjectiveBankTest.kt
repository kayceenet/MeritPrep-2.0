package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryWaecObjectiveBankTest {
    @Test fun correctAnswersAreBalancedAcrossAllFourPositions() {
        val counts = (0..3).map { position -> ChemistryWaecObjectiveBank.all.count { it.correctOptionIndex == position } }
        assertTrue(counts.maxOrNull()!! - counts.minOrNull()!! <= 1)
    }

    @Test fun containsThirtyUniqueOriginalItems() {
        assertEquals(30, ChemistryWaecObjectiveBank.all.size)
        assertEquals(30, ChemistryWaecObjectiveBank.all.map { it.id }.distinct().size)
    }

    @Test fun everyItemHasFourOptionsAndValidAnswer() {
        ChemistryWaecObjectiveBank.all.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.explanation.isNotBlank())
        }
    }

    @Test fun questionsMapToExistingChemistryTopics() {
        val topicIds = ChemistryCourse.topics.map { it.id }.toSet()
        ChemistryWaecObjectiveBank.all.forEach { question ->
            assertTrue("Unknown topic: ${question.topicId}", question.topicId in topicIds)
        }
    }

    @Test fun questionsAreNotMislabelledAsOfficialPastQuestions() {
        ChemistryWaecObjectiveBank.all.forEach { question ->
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original WAEC-style"))
        }
    }
}
