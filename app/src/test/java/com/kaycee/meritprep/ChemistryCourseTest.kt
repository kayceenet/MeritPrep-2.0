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

    @Test fun firstEightTopicsHaveBeginnerLessonContent() {
        val developed = ChemistryCourse.topics.take(8)
        assertTrue(developed.all { it.objectives.size >= 4 })
        assertTrue(developed.all { it.sections.size >= 4 })
        assertTrue(developed.none { it.isSyllabusOutline })
        assertTrue(developed.all { it.checkOptions.size == 4 })
    }

    @Test fun newlyDevelopedLessonsCoverNuclearSolubilityPollutionAndAcids() {
        val ids = ChemistryCourse.topics.take(8).map { it.id }
        assertTrue(ids.containsAll(listOf("chem-nuclear", "chem-solubility", "chem-pollution", "chem-acids-bases")))
        assertTrue(ChemistryCourse.topics.take(8).all { it.sections.size >= 5 })
    }

    @Test fun remainingTenTopicsAreClearlyMarkedAsMappedDrafts() {
        val mappedDrafts = ChemistryCourse.topics.drop(8)
        assertEquals(10, mappedDrafts.size)
        assertTrue(mappedDrafts.all { it.isSyllabusOutline })
        assertTrue(mappedDrafts.all { it.sections.any { section -> section.first == "Syllabus scope" } })
    }
}
