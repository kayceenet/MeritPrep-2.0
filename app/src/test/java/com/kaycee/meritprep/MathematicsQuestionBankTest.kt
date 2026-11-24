package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MathematicsQuestionBankTest {
    @Test fun containsSeventyFiveOriginalQuestionsAcrossFifteenDevelopedTopics() {
        assertEquals(75, MathematicsQuestionBank.all.size)
        assertEquals(15, MathematicsCourse.topics.count { !it.isSyllabusOutline })
        MathematicsCourse.topics.filterNot { it.isSyllabusOutline }.forEach { topic ->
            assertEquals(5, MathematicsQuestionBank.forTopic(topic.id).size)
        }
    }

    @Test fun questionIdsAndAnswerKeysAreValid() {
        val questions = MathematicsQuestionBank.all
        assertEquals(questions.size, questions.map { it.id }.distinct().size)
        questions.forEach {
            assertTrue(it.prompt.isNotBlank())
            assertEquals(4, it.options.size)
            assertTrue(it.correctOptionIndex in it.options.indices)
            assertTrue(it.explanation.isNotBlank())
            assertFalse(it.isVerified)
            assertTrue(it.source.startsWith("MeritPrep original Mathematics practice"))
        }
    }

    @Test fun allQuestionsMapToDevelopedMathematicsTopics() {
        val developed = MathematicsCourse.topics.filterNot { it.isSyllabusOutline }.map { it.id }.toSet()
        assertFalse(MathematicsQuestionBank.all.any { it.topicId !in developed })
    }
}
