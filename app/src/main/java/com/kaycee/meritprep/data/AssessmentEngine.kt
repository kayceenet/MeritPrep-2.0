package com.kaycee.meritprep.data

import kotlin.random.Random

data class PresentedQuestion(
    val questionId: String,
    val topicId: String,
    val prompt: String,
    val options: List<String>,
    val correctPresentedIndex: Int,
    val explanation: String
)

data class AssessmentResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val percentage: Int,
    val answers: List<AnswerRecord>
)

object AssessmentEngine {
    fun present(question: AssessmentQuestion, random: Random = Random.Default): PresentedQuestion {
        val indexed = question.options.mapIndexed { index, value -> index to value }.shuffled(random)
        val correctIndex = indexed.indexOfFirst { it.first == question.correctOptionIndex }
        return PresentedQuestion(
            questionId = question.id,
            topicId = question.topicId,
            prompt = question.prompt,
            options = indexed.map { it.second },
            correctPresentedIndex = correctIndex,
            explanation = question.explanation
        )
    }

    fun score(
        questions: List<PresentedQuestion>,
        selectedIndices: Map<String, Int>
    ): AssessmentResult {
        val answers = questions.map { question ->
            val selected = selectedIndices[question.questionId] ?: -1
            AnswerRecord(
                questionId = question.questionId,
                selectedOptionIndex = selected,
                isCorrect = selected == question.correctPresentedIndex
            )
        }
        val correct = answers.count { it.isCorrect }
        val total = questions.size
        return AssessmentResult(
            totalQuestions = total,
            correctAnswers = correct,
            percentage = if (total == 0) 0 else (correct * 100) / total,
            answers = answers
        )
    }
}
