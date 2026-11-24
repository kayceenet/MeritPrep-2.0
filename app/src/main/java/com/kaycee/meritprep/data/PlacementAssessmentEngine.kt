package com.kaycee.meritprep.data

import kotlin.random.Random

data class PlacementDiagnosticResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val unanswered: Int,
    val percentage: Int,
    val topicPerformance: List<CbtTopicPerformance>
) {
    val priorityTopics: List<String>
        get() = topicPerformance.filter { it.accuracyPercent < 60 }.map { it.topicId }
            .ifEmpty { topicPerformance.take(3).map { it.topicId } }
}

/**
 * Creates a broad starting-point check from the available approved-by-structure
 * question pool. It does not estimate an exam grade or certify readiness.
 */
object PlacementAssessmentEngine {
    fun createDiagnostic(
        pool: List<AssessmentQuestion>,
        random: Random = Random.Default
    ): List<PresentedQuestion> {
        require(pool.isNotEmpty()) { "A placement diagnostic needs questions." }
        val byTopic = pool.groupBy { it.topicId }
        require(byTopic.keys.none { it.isBlank() }) { "Every question must have a topic." }
        return byTopic.entries
            .sortedBy { it.key }
            .map { (_, questions) -> questions.random(random) }
            .shuffled(random)
            .map { AssessmentEngine.present(it, random) }
    }

    fun score(
        questions: List<PresentedQuestion>,
        selectedAnswers: Map<String, Int>
    ): PlacementDiagnosticResult {
        val base = CbtSessionEngine.score(questions, selectedAnswers)
        return PlacementDiagnosticResult(
            totalQuestions = base.totalQuestions,
            correctAnswers = base.correctAnswers,
            unanswered = base.unanswered,
            percentage = base.percentage,
            topicPerformance = CbtSessionEngine.topicPerformance(questions, selectedAnswers)
        )
    }
}
