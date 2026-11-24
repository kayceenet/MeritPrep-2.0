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

    @Test fun firstFourTopicsHaveBeginnerLessonContent() {
        val developed = ChemistryCourse.topics.take(4)
        assertTrue(developed.all { it.objectives.size >= 4 })
        assertTrue(developed.all { it.sections.size >= 4 })
        assertTrue(developed.all { it.checkOptions.size == 4 })
    }

    @Test fun remainingTopicsAreClearlyMarkedAsMappedDrafts() {
        val mappedDrafts = ChemistryCourse.topics.drop(4)
        assertEquals(14, mappedDrafts.size)
        assertTrue(mappedDrafts.all { it.sections.any { section -> section.first == "Syllabus scope" } })
    }
}
