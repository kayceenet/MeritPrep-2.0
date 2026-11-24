package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryQuestionBankTest {
    @Test fun containsFortyOriginalQuestionsAcrossEightDevelopedTopics() {
        assertEquals(40, ChemistryQuestionBank.all.size)
        assertEquals(40, ChemistryQuestionBank.all.map { it.id }.distinct().size)
        assertEquals(8, ChemistryQuestionBank.all.map { it.topicId }.distinct().size)
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
