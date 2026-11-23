package com.kaycee.meritprep.data

import kotlin.random.Random

data class CbtResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val unanswered: Int,
    val percentage: Int
)

object CbtSessionEngine {
    fun createQuestions(
        pool: List<AssessmentQuestion>,
        count: Int = 40,
        random: Random = Random.Default
    ): List<PresentedQuestion> {
        require(count > 0) { "A CBT session must contain at least one question." }
        require(pool.size >= count) { "The question pool is smaller than the requested session." }
        val selected = pool.shuffled(random).take(count)
        return selected.map { AssessmentEngine.present(it, random) }
    }

    fun score(
        questions: List<PresentedQuestion>,
        selectedAnswers: Map<String, Int>
    ): CbtResult {
        val correct = questions.count { selectedAnswers[it.questionId] == it.correctPresentedIndex }
        val unanswered = questions.count { selectedAnswers[it.questionId] == null || selectedAnswers[it.questionId] == -1 }
        val total = questions.size
        return CbtResult(
            totalQuestions = total,
            correctAnswers = correct,
            unanswered = unanswered,
            percentage = if (total == 0) 0 else (correct * 100) / total
        )
    }
}
