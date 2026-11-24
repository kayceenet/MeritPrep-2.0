package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsCourseTest {
    @Test fun mapsThirtyNineJambPhysicsTopicsInSyllabusOrder() {
        assertEquals(39, PhysicsCourse.topics.size)
        assertEquals("phy-measurements", PhysicsCourse.topics.first().id)
        assertEquals("phy-electronics", PhysicsCourse.topics.last().id)
        assertEquals(39, PhysicsCourse.topics.map { it.id }.distinct().size)
        assertTrue(PhysicsCourse.topics.all { it.subjectCode == "PHY" })
    }

    @Test fun firstEightTopicsAreDevelopedAndRemainingEntriesAreOutlines() {
        assertEquals(8, PhysicsCourse.topics.count { !it.isSyllabusOutline })
        assertEquals(31, PhysicsCourse.topics.count { it.isSyllabusOutline })
        PhysicsCourse.topics.filterNot { it.isSyllabusOutline }.forEach {
            assertTrue(it.objectives.size >= 4)
            assertTrue(it.sections.size >= 4)
            assertTrue(it.checkOptions.size == 4)
            assertTrue(it.correctOption in it.checkOptions.indices)
        }
    }

    @Test fun allTopicIdsAreUniqueAndAllChecksHaveValidAnswers() {
        PhysicsCourse.topics.forEach {
            assertFalse(it.id.isBlank())
            assertTrue(it.title.isNotBlank())
            assertTrue(it.checkQuestion.isNotBlank())
            assertTrue(it.explanation.isNotBlank())
            assertTrue(it.correctOption in it.checkOptions.indices)
        }
    }
}
