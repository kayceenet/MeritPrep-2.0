package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsWaecTheoryBankTest {
    @Test fun containsSixOriginalStructuredTheoryTasks() {
        val tasks = PhysicsWaecTheoryBank.tasks
        assertEquals(12, tasks.size)
        assertEquals(tasks.size, tasks.map { it.id }.distinct().size)
        tasks.forEach {
            assertTrue(it.topic.isNotBlank())
            assertTrue(it.prompt.isNotBlank())
            assertTrue(it.marks > 0)
            assertTrue(it.markingPoints.isNotEmpty())
            assertTrue(it.markingPoints.all(String::isNotBlank))
            assertTrue(it.guidance.isNotBlank())
        }
    }

    @Test fun coversMajorWaecPhysicsDomains() {
        val topics = PhysicsWaecTheoryBank.tasks.map { it.topic }
        listOf(
            "Motion, forces and energy",
            "Thermal physics and gas laws",
            "Waves and sound",
            "Refraction and optical instruments",
            "Current electricity and electromagnetic induction",
            "Atomic physics, radioactivity and electronics"
        ).forEach { assertTrue("Missing theory area: $it", it in topics) }
    }

    @Test fun newTasksCoverMeasurementFluidsFieldsMagnetismOscillationsAndPracticalAnalysis() {
        val tasks = PhysicsWaecTheoryBank.tasks.associateBy { it.id }
        val required = listOf(
            "waec-physics-theory-measurement-07",
            "waec-physics-theory-pressure-08",
            "waec-physics-theory-electrostatics-09",
            "waec-physics-theory-magnetism-10",
            "waec-physics-theory-oscillations-11",
            "waec-physics-theory-practical-12"
        )
        assertTrue(required.all { it in tasks })
        assertTrue(tasks.getValue("waec-physics-theory-electrostatics-09").markingPoints.any { it.contains("0.60 N") })
        assertTrue(tasks.getValue("waec-physics-theory-practical-12").guidance.contains("gradient"))
        assertTrue(tasks.getValue("waec-physics-theory-pressure-08").markingPoints.any { it.contains("3,000 N") })
    }

    @Test fun tasksAreClearlyIdentifiedAsOriginalPracticeMaterial() {
        assertTrue(PhysicsWaecTheoryBank.tasks.all { it.id.startsWith("waec-physics-theory-") })
    }
}
