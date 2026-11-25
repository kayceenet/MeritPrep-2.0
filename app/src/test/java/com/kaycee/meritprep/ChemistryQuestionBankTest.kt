package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryQuestionBankTest {
    @Test fun correctAnswersAreBalancedAcrossAllFourPositions() {
        val counts = (0..3).map { position -> ChemistryQuestionBank.all.count { it.correctOptionIndex == position } }
        assertTrue(counts.maxOrNull()!! - counts.minOrNull()!! <= 1)
    }

    @Test fun containsNinetyOriginalQuestionsAcrossEighteenTopics() {
        assertEquals(90, ChemistryQuestionBank.all.size)
        assertEquals(90, ChemistryQuestionBank.all.map { it.id }.distinct().size)
        assertEquals(18, ChemistryQuestionBank.all.map { it.topicId }.distinct().size)
    }

    @Test fun eachDevelopedTopicHasFiveQuestions() {
        ChemistryCourse.topics.filterNot { it.isSyllabusOutline }.forEach { topic ->
            assertEquals(topic.id, 5, ChemistryQuestionBank.forTopic(topic.id).size)
        }
    }

    @Test fun everyQuestionHasFourOptionsAndValidAnswer() {
        ChemistryQuestionBank.all.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.correctOptionIndex in question.options.indices)
            assertTrue(question.prompt.isNotBlank())
            assertTrue(question.explanation.isNotBlank())
        }
    }

    @Test fun originalPracticeItemsAreNotMarkedAsVerifiedPastQuestions() {
        ChemistryQuestionBank.all.forEach { question ->
            assertFalse(question.isVerified)
            assertTrue(question.source.startsWith("MeritPrep original Chemistry practice"))
        }
    }
}
