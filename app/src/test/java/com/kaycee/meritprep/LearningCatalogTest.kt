package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningCatalogTest {
    @Test
    fun topicIdsAreUnique() {
        val ids = LearningCatalog.topics.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun everyLessonHasUsableTeachingContentAndKnowledgeCheck() {
        LearningCatalog.topics.forEach { topic ->
            assertTrue(topic.title.isNotBlank())
            assertTrue(topic.introduction.isNotBlank())
            assertFalse(topic.objectives.isEmpty())
            assertFalse(topic.sections.isEmpty())
            assertTrue(topic.sections.all { it.first.isNotBlank() && it.second.isNotBlank() })
            assertTrue(topic.checkOptions.size >= 2)
            assertTrue(topic.correctOption in topic.checkOptions.indices)
            assertTrue(topic.explanation.isNotBlank())
        }
    }

    @Test
    fun eachInitialSubjectHasAtLeastOneLesson() {
        listOf("BIO", "CHEM", "PHY", "MATH", "ENG", "ECO", "GOV").forEach { code ->
            assertTrue("Missing starter lesson for $code", LearningCatalog.forSubject(code).isNotEmpty())
        }
    }
}
