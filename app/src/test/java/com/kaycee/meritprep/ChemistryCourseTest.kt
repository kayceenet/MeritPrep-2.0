package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryCourseTest {
    @Test fun mapsAllEighteenJambSyllabusTopics() {
        assertEquals(18, ChemistryCourse.topics.size)
        assertTrue(ChemistryCourse.topics.all { it.subjectCode == "CHEM" })
        assertEquals(18, ChemistryCourse.topics.map { it.id }.distinct().size)
    }

    @Test fun allEighteenTopicsHaveBeginnerLessonContent() {
        val lessons = ChemistryCourse.topics
        assertEquals(18, lessons.count { !it.isSyllabusOutline })
        assertTrue(lessons.all { it.objectives.size >= 4 })
        assertTrue(lessons.all { it.sections.size >= 4 })
        assertTrue(lessons.all { it.checkOptions.size == 4 })
        assertTrue(lessons.all { it.introduction.isNotBlank() && it.explanation.isNotBlank() })
    }

    @Test fun finalSixTopicsHaveAuthoredTeachingContent() {
        val ids = ChemistryCourse.topics.map { it.id }
        assertTrue(ids.containsAll(listOf("chem-equilibrium", "chem-nonmetals", "chem-metals", "chem-organic", "chem-industry", "chem-astro")))
        assertTrue(ChemistryCourse.topics.none { it.isSyllabusOutline })
    }

    @Test fun noSyllabusTopicIsMistakenForAnUnfinishedOutline() {
        assertTrue(ChemistryCourse.topics.none { it.isSyllabusOutline })
    }
}
