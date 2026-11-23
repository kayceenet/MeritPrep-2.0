package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyGlossaryTest {
    @Test
    fun glossaryEntriesAreUniqueCompleteAndMappedToBiologyLessons() {
        val entries = BiologyGlossary.entries
        assertTrue(entries.size >= 50)
        assertEquals(entries.size, entries.map { it.term.trim().lowercase() }.toSet().size)
        entries.forEach { entry ->
            assertTrue(entry.term.isNotBlank())
            assertTrue(entry.definition.isNotBlank())
            assertTrue(entry.example.isNotBlank())
            assertTrue(
                "Unmapped glossary term: ${entry.term}",
                BiologyCurriculum.units.any { it.lessonId == entry.topicId }
            )
        }
    }
}
