package com.kaycee.meritprep.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class PlacementAssessmentEngineTest {
    private fun question(id: String, topic: String, correct: Int = 0) = AssessmentQuestion(
        id = id,
        topicId = topic,
        prompt = "Question $id",
        options = listOf("A", "B", "C", "D"),
        correctOptionIndex = correct,
        explanation = "Explanation for $id",
        source = "MeritPrep original practice item",
        isVerified = false
    )

    @Test fun diagnosticSamplesOneQuestionFromEveryTopic() {
        val pool = listOf(
            question("a1", "bio-a"), question("a2", "bio-a", 1),
            question("b1", "bio-b"), question("b2", "bio-b", 2),
            question("c1", "bio-c")
        )
        val diagnostic = PlacementAssessmentEngine.createDiagnostic(pool, Random(7))
        assertEquals(3, diagnostic.size)
        assertEquals(setOf("bio-a", "bio-b", "bio-c"), diagnostic.map { it.topicId }.toSet())
        assertEquals(3, diagnostic.map { it.questionId }.toSet().size)
    }

    @Test fun scoringIncludesUnansweredAndRanksTopicEvidence() {
        val questions = listOf(
            PresentedQuestion("a", "bio-a", "A", listOf("A", "B"), 0, "E"),
            PresentedQuestion("b", "bio-b", "B", listOf("A", "B"), 1, "E"),
            PresentedQuestion("c", "bio-c", "C", listOf("A", "B"), 0, "E")
        )
        val result = PlacementAssessmentEngine.score(questions, mapOf("a" to 0, "b" to 0))
        assertEquals(3, result.totalQuestions)
        assertEquals(1, result.correctAnswers)
        assertEquals(1, result.unanswered)
        assertEquals(33, result.percentage)
        assertEquals("bio-b", result.priorityTopics.first())
        assertTrue(result.priorityTopics.isNotEmpty())
    }

    @Test fun emptyPoolIsRejected() {
        try {
            PlacementAssessmentEngine.createDiagnostic(emptyList())
            throw AssertionError("Expected empty pool to be rejected")
        } catch (_: IllegalArgumentException) {
            // Expected: an empty diagnostic would be misleading.
        }
    }
}
