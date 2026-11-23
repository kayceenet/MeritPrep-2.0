package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyGlossaryTest {
    @Test fun glossaryTermsAreUniqueAndHaveDefinitionsAndExamples() {
        val entries = BiologyGlossary.entries
        assertTrue(entries.size >= 40)
        assertEquals(entries.size, entries.map { it.term.trim().lowercase() }.toSet().size)
        assertTrue(entries.all { it.term.isNotBlank() && it.definition.isNotBlank() && it.example.isNotBlank() })
    }

    @Test fun glossaryCoversEveryMappedBiologyLessonTopic() {
        val syllabusTopics = BiologyCurriculum.units.mapNotNull { it.lessonId }.toSet()
        val glossaryTopics = BiologyGlossary.entries.map { it.topicId }.toSet()
        assertEquals(syllabusTopics, glossaryTopics)
    }
}
