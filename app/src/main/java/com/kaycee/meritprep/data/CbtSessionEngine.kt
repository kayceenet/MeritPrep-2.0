package com.kaycee.meritprep.data

import kotlin.random.Random

data class CbtResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val unanswered: Int,
    val percentage: Int
)

data class CbtTopicPerformance(
    val topicId: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val accuracyPercent: Int
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

    /**
     * Summarises accuracy by syllabus topic. Topics with lower accuracy appear first
     * so the learner can use the result to choose the next revision task.
     * Unanswered questions remain in the denominator and are therefore not counted
     * as correct.
     */
    fun topicPerformance(
        questions: List<PresentedQuestion>,
        selectedAnswers: Map<String, Int>
    ): List<CbtTopicPerformance> {
        return questions
            .groupBy { it.topicId }
            .map { (topicId, topicQuestions) ->
                val correct = topicQuestions.count {
                    selectedAnswers[it.questionId] == it.correctPresentedIndex
                }
                val total = topicQuestions.size
                CbtTopicPerformance(
                    topicId = topicId,
                    totalQuestions = total,
                    correctAnswers = correct,
                    accuracyPercent = if (total == 0) 0 else (correct * 100) / total
                )
            }
            .sortedWith(compareBy<CbtTopicPerformance> { it.accuracyPercent }.thenBy { it.topicId })
    }

    fun topicPerformance(
        questions: List<PresentedQuestion>,
        selectedAnswers: Map<String, Int>
    ): List<CbtTopicPerformance> {
        return questions
            .groupBy { it.topicId }
            .map { (topicId, topicQuestions) ->
                val correct = topicQuestions.count { question ->
                    selectedAnswers[question.questionId] == question.correctPresentedIndex
                }
                CbtTopicPerformance(
                    topicId = topicId,
                    totalQuestions = topicQuestions.size,
                    correctAnswers = correct,
                    accuracyPercent = if (topicQuestions.isEmpty()) 0
                    else (correct * 100) / topicQuestions.size
                )
            }
            .sortedWith(compareBy<CbtTopicPerformance> { it.accuracyPercent }.thenBy { it.topicId })
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
