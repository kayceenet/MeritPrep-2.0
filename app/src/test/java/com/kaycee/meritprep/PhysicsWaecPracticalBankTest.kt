package com.kaycee.meritprep

import com.kaycee.meritprep.data.PhysicsPracticalAnalysisEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsWaecPracticalBankTest {
    @Test fun containsNineOriginalPracticalTasksWithCompleteData() {
        val tasks = PhysicsWaecPracticalBank.tasks
        assertEquals(9, tasks.size)
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

        val moments = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-moments").readings)
        assertEquals(0.20, moments.gradient, 1e-9)
        val lens = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-focal-length").readings)
        assertTrue(lens.gradient in -1.02..-0.98)
        assertEquals(10.0, 1.0 / lens.intercept, 0.1)
        val heating = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-specific-heat").readings)
        assertEquals(840.0, heating.gradient, 1e-9)
        assertEquals(4200.0, heating.gradient / 0.20, 1e-9)
        val parallel = PhysicsPracticalAnalysisEngine.linearFit(tasks.getValue("waec-physics-parallel-resistors").readings)
        assertEquals(2.0, parallel.gradient, 1e-9)
    }
}
