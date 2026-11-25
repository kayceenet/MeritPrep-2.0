package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LessonPracticalRoutingTest {
    @Test
    fun routesChemistryPracticalTopics() {
        assertEquals(LessonPracticalKind.CHEMISTRY_REDOX, LessonPracticalRouting.forTopic("chem-redox"))
        assertEquals(LessonPracticalKind.CHEMISTRY_REDOX, LessonPracticalRouting.forTopic("chem-acids-bases"))
        assertEquals(LessonPracticalKind.CHEMISTRY_QUALITATIVE, LessonPracticalRouting.forTopic("chem-metals"))
    }

    @Test
    fun routesPhysicsPracticalTopics() {
        assertEquals(LessonPracticalKind.PHYSICS_ELASTICITY, LessonPracticalRouting.forTopic("phy-elasticity"))
        assertEquals(LessonPracticalKind.PHYSICS_PRESSURE, LessonPracticalRouting.forTopic("phy-pressure"))
        assertEquals(LessonPracticalKind.PHYSICS_FLUIDS, LessonPracticalRouting.forTopic("phy-liquids-rest"))
        assertEquals(LessonPracticalKind.PHYSICS_HEAT, LessonPracticalRouting.forTopic("phy-heat-quantity"))
        assertEquals(LessonPracticalKind.PHYSICS_GAS_LAWS, LessonPracticalRouting.forTopic("phy-gas-laws"))
        assertEquals(LessonPracticalKind.PHYSICS_CAPACITORS, LessonPracticalRouting.forTopic("phy-capacitors"))
        assertEquals(LessonPracticalKind.PHYSICS_RESISTOR_NETWORK, LessonPracticalRouting.forTopic("phy-current-electricity"))
    }

    @Test
    fun leavesUnmappedLessonsOnTheGeneralGuidedInvestigationPath() {
        assertNull(LessonPracticalRouting.forTopic("bio-cell"))
        assertNull(LessonPracticalRouting.forTopic("phy-modern"))
    }
}
