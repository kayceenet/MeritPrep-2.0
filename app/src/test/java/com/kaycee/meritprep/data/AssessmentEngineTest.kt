package com.kaycee.meritprep.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class AssessmentEngineTest {
    private val question = AssessmentQuestion(
        id = "bio-cell-001",
        topicId = "bio-cell",
        prompt = "Which organelle is the principal site of aerobic respiration?",
        options = listOf("Mitochondrion", "Ribosome", "Cell wall", "Nucleus"),
        correctOptionIndex = 0,
        explanation = "The mitochondrion is the principal site of aerobic respiration in eukaryotic cells.",
        source = "MeritPrep demonstration item",
        isVerified = false
    )

    @Test fun shuffledOptionsRetainCorrectAnswer() {
        val presented = AssessmentEngine.present(question, Random(42))
        assertEquals("Mitochondrion", presented.options[presented.correctPresentedIndex])
    }

    @Test fun scoringProducesExpectedPercentage() {
        val presented = AssessmentEngine.present(question, Random(42))
        val result = AssessmentEngine.score(listOf(presented), mapOf(question.id to presented.correctPresentedIndex))
        assertEquals(100, result.percentage)
        assertEquals(1, result.correctAnswers)
    }

    @Test fun emptyAssessmentHasZeroPercentage() {
        val result = AssessmentEngine.score(emptyList(), emptyMap())
        assertEquals(0, result.percentage)
        assertTrue(result.answers.isEmpty())
    }
}
