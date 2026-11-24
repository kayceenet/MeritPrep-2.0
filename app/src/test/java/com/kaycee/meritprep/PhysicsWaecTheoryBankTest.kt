package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsWaecTheoryBankTest {
    @Test fun containsSixOriginalStructuredTheoryTasks() {
        val tasks = PhysicsWaecTheoryBank.tasks
        assertEquals(6, tasks.size)
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

    @Test fun tasksAreClearlyIdentifiedAsOriginalPracticeMaterial() {
        assertTrue(PhysicsWaecTheoryBank.tasks.all { it.id.startsWith("waec-physics-theory-") })
    }
}
