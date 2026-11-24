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
        assertEquals("phy-elasticity", PhysicsCourse.topics[8].id)
        assertEquals("phy-pressure", PhysicsCourse.topics[9].id)
        assertEquals("phy-temperature", PhysicsCourse.topics[11].id)
        assertEquals("phy-gas-laws", PhysicsCourse.topics[13].id)
        assertEquals("phy-change-state", PhysicsCourse.topics[15].id)
        assertEquals("phy-vapours", PhysicsCourse.topics[16].id)
        assertEquals("phy-reflection", PhysicsCourse.topics[23].id)
        assertEquals("phy-refraction", PhysicsCourse.topics[24].id)
        assertEquals("phy-optical-instruments", PhysicsCourse.topics[25].id)
        assertEquals("phy-dispersion", PhysicsCourse.topics[26].id)
        assertEquals("phy-electrostatics", PhysicsCourse.topics[27].id)
        assertEquals("phy-capacitors", PhysicsCourse.topics[28].id)
        assertEquals("phy-cells", PhysicsCourse.topics[29].id)
        assertEquals("phy-current-electricity", PhysicsCourse.topics[30].id)
        assertEquals("phy-electrical-power", PhysicsCourse.topics[31].id)
        assertEquals(39, PhysicsCourse.topics.map { it.id }.distinct().size)
        assertTrue(PhysicsCourse.topics.all { it.subjectCode == "PHY" })
    }

    @Test fun allThirtyNineTopicsHaveDevelopedLessons() {
        assertEquals(39, PhysicsCourse.topics.count { !it.isSyllabusOutline })
        assertEquals(0, PhysicsCourse.topics.count { it.isSyllabusOutline })
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
