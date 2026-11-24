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
        val developed = ChemistryCourse.topics
        assertTrue(developed.all { it.objectives.size >= 4 })
        assertTrue(developed.all { it.sections.size >= 4 })
        assertTrue(developed.none { it.isSyllabusOutline })
        assertTrue(developed.all { it.checkOptions.size == 4 })
    }

    @Test fun finalSixTopicsHaveBeginnerLessonContent() {
        val ids = ChemistryCourse.topics.map { it.id }
        assertTrue(ids.containsAll(listOf("chem-nuclear", "chem-solubility", "chem-pollution", "chem-acids-bases", "chem-redox", "chem-electrolysis", "chem-energy", "chem-rates")))
        assertEquals(18, ChemistryCourse.topics.count { !it.isSyllabusOutline })
    }

    @Test fun allTopicsAreDevelopedAndNotOutlineOnly() {
        val mappedDrafts = ChemistryCourse.topics.drop(12)
        assertEquals(0, mappedDrafts.size)
        assertTrue(mappedDrafts.all { it.isSyllabusOutline })
        assertTrue(mappedDrafts.all { it.sections.any { section -> section.first == "Syllabus scope" } })
    }
}
