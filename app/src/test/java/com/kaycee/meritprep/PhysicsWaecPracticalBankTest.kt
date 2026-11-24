package com.kaycee.meritprep

import com.kaycee.meritprep.data.PhysicsPracticalAnalysisEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsWaecPracticalBankTest {
    @Test fun containsFiveOriginalPracticalTasksWithCompleteData() {
        val tasks = PhysicsWaecPracticalBank.tasks
        assertEquals(5, tasks.size)
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

        val density = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-density").readings)
        assertTrue(density.gradient in 2.68..2.72)

        val resistancePerMetre = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-resistivity").readings)
        assertTrue(resistancePerMetre.gradient in 0.034..0.036)
        val resistivity = resistancePerMetre.gradient * 0.50e-6
        assertTrue(resistivity in 1.7e-8..1.8e-8)
    }
}
