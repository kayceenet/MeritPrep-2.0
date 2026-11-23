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
}
