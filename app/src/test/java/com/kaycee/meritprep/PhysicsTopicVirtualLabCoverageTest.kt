package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsTopicVirtualLabCoverageTest {
    @Test
    fun everyDedicatedVisualPhysicsLaboratoryMatchesARealSyllabusTopic() {
        val syllabusIds = PhysicsCourse.topics.map { it.id }.toSet()
        assertTrue(
            "Dedicated visual labs must map to actual Physics syllabus topics.",
            syllabusIds.containsAll(physicsTopicVirtualLabIds)
        )
    }

    @Test
    fun theFormerGenericCalculatorTopicsHaveDedicatedVisualLaboratories() {
        val required = setOf(
            "phy-temperature",
            "phy-heat-quantity",
            "phy-change-state",
            "phy-vapours",
            "phy-optical-instruments",
            "phy-dispersion",
            "phy-magnetism",
            "phy-conduction",
            "phy-electronics"
        )
        assertTrue(
            "A topic must not fall back to the generic missing-topic calculator: ${required - physicsTopicVirtualLabIds}",
            physicsTopicVirtualLabIds.containsAll(required)
        )
    }
}
