package com.kaycee.meritprep.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class CbtSessionEngineTest {
    private fun question(id: String, correct: Int = 0) = AssessmentQuestion(
        id = id, topicId = "bio-cell", prompt = "Question $id",
        options = listOf("A", "B", "C", "D"), correctOptionIndex = correct,
        explanation = "Explanation", source = "MeritPrep original practice item", isVerified = false
    )

    @Test fun createsRequestedNumberOfUniqueQuestions() {
        val pool = (1..60).map { question("q$it", it % 4) }
        val session = CbtSessionEngine.createQuestions(pool, 40, Random(9))
        assertEquals(40, session.size)
        assertEquals(40, session.map { it.questionId }.toSet().size)
        assertTrue(session.all { it.topicId == "bio-cell" && it.correctPresentedIndex in it.options.indices })
    }

    @Test fun createsFiftyQuestionWaecPracticeSessionFromTheSameOriginalPool() {
        val pool = (1..60).map { question("waec$it", it % 4) }
        val session = CbtSessionEngine.createQuestions(pool, 50, Random(21))
        assertEquals(50, session.size)
        assertEquals(50, session.map { it.questionId }.toSet().size)
    }

    @Test fun reportsTopicPerformanceAndPrioritisesTheLowestAccuracy() {
        val questions = listOf(
            PresentedQuestion("q1", "bio-cell", "Q1", listOf("A", "B"), 0, "E"),
            PresentedQuestion("q2", "bio-cell", "Q2", listOf("A", "B"), 1, "E"),
            PresentedQuestion("q3", "bio-transport", "Q3", listOf("A", "B"), 0, "E")
        )
        val performance = CbtSessionEngine.topicPerformance(
            questions,
            mapOf("q1" to 0, "q2" to 0, "q3" to 0)
        )
        assertEquals("bio-cell", performance.first().topicId)
        assertEquals(2, performance.first().totalQuestions)
        assertEquals(1, performance.first().correctAnswers)
        assertEquals(50, performance.first().accuracyPercent)
        assertEquals(100, performance.last().accuracyPercent)
    }

    @Test fun emptyTopicPerformanceIsAnEmptyList() {
        assertTrue(CbtSessionEngine.topicPerformance(emptyList(), emptyMap()).isEmpty())
    }

    @Test fun scoresCorrectAnswersAndUnansweredQuestions() {
        val pool = listOf(question("q1"), question("q2"), question("q3"))
        val session = CbtSessionEngine.createQuestions(pool, 3, Random(1))
        val answers = session.take(2).associate { it.questionId to it.correctPresentedIndex }
        val result = CbtSessionEngine.score(session, answers)
        assertEquals(3, result.totalQuestions)
        assertEquals(2, result.correctAnswers)
        assertEquals(1, result.unanswered)
        assertEquals(66, result.percentage)
    }
    @Test fun spreadsMockQuestionsAcrossTopicsBeforeRepeatingTopics() {
        val pool = (0 until 24).flatMap { topic ->
            (0 until 5).map { index ->
                AssessmentQuestion(
                    id = "topic${topic}_q$index",
                    topicId = "topic$topic",
                    prompt = "Question $topic/$index",
                    options = listOf("A", "B", "C", "D"),
                    correctOptionIndex = index % 4,
                    explanation = "Explanation",
                    source = "MeritPrep original practice item",
                    isVerified = false
                )
            }
        }
        val session = CbtSessionEngine.createQuestions(pool, 40, Random(17))
        val counts = session.groupingBy { it.topicId }.eachCount()
        assertEquals(24, counts.size)
        assertTrue(counts.values.all { it in 1..2 })
        assertTrue(counts.values.maxOrNull()!! - counts.values.minOrNull()!! <= 1)
        assertEquals(40, session.map { it.questionId }.toSet().size)
    }

}