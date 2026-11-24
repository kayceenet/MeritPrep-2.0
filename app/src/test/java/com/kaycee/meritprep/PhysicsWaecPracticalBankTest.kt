package com.kaycee.meritprep

import com.kaycee.meritprep.data.PhysicsPracticalAnalysisEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsWaecPracticalBankTest {
    @Test fun containsThreeOriginalPracticalTasksWithCompleteData() {
        val tasks = PhysicsWaecPracticalBank.tasks
        assertEquals(3, tasks.size)
        assertEquals(tasks.size, tasks.map { it.id }.distinct().size)
        tasks.forEach {
            assertTrue(it.aim.isNotBlank())
            assertTrue(it.apparatus.isNotEmpty())
            assertTrue(it.independentVariable.contains("("))
            assertTrue(it.dependentVariable.contains("("))
            assertEquals(5, it.readings.size)
            assertTrue(it.taskPrompt.isNotBlank())
            assertTrue(it.examinerPoints.size >= 6)
        }
    }

    @Test fun eachTaskProducesAReasonablePhysicalResult() {
        val tasks = PhysicsWaecPracticalBank.tasks.associateBy { it.id }
        val spring = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-hooke").readings)
        assertEquals(49.0, spring.gradient, 0.01)

        val pendulum = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-pendulum").readings)
        val gravity = PhysicsPracticalAnalysisEngine.gravityFromPendulumGradient(pendulum.gradient)
        assertTrue(gravity in 9.5..10.2)

        val ohm = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-ohm").readings)
        assertTrue(ohm.gradient in 4.8..5.2)
    }
}
