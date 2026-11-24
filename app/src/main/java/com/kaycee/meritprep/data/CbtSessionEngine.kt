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
        // Round-robin across syllabus topics so a short mock does not over-sample
        // a few topics simply because their question banks are larger.
        val topicBuckets = pool.groupBy { it.topicId }
            .mapValues { (_, questions) -> questions.shuffled(random) }
        val topicOrder = topicBuckets.keys.shuffled(random)
        val selected = ArrayList<AssessmentQuestion>(count)
        var round = 0
        while (selected.size < count) {
            var addedThisRound = false
            topicOrder.forEach { topicId ->
                val question = topicBuckets.getValue(topicId).getOrNull(round)
                if (question != null && selected.size < count) {
                    selected += question
                    addedThisRound = true
                }
            }
            if (!addedThisRound) break
            round++
        }
        check(selected.size == count) { "The question pool could not fill the requested session." }
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
