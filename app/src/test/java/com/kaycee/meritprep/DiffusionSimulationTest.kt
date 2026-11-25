package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertThrows
import org.junit.Test

class DiffusionSimulationTest {
    @Test fun particlesMoveNetFromHigherToLowerConcentration() {
        val result = DiffusionSimulation.evaluate(DiffusionConditions(1.2, 0.3, 100.0))
        assertEquals(DiffusionDirection.HIGH_TO_LOW, result.direction)
        assertTrue(result.relativeRateIndexPercent > 0.0)
        assertTrue(result.explanation.contains("left to right"))
    }

    @Test fun equalConcentrationsHaveNoNetDiffusion() {
        val result = DiffusionSimulation.evaluate(DiffusionConditions(0.5, 0.5, 100.0))
        assertEquals(DiffusionDirection.NO_NET_MOVEMENT, result.direction)
        assertEquals(0.0, result.relativeRateIndexPercent, 0.0001)
        assertTrue(result.explanation.contains("randomly in both directions"))
    }

    @Test fun impermeableMembranePreventsDiffusionAcrossIt() {
        val result = DiffusionSimulation.evaluate(DiffusionConditions(1.0, 0.1, 0.0))
        assertEquals(0.0, result.relativeRateIndexPercent, 0.0001)
        assertTrue(result.explanation.contains("impermeable"))
    }

    @Test fun greaterPermeabilityIncreasesModelledRate() {
        val low = DiffusionSimulation.evaluate(DiffusionConditions(1.0, 0.2, 25.0))
        val high = DiffusionSimulation.evaluate(DiffusionConditions(1.0, 0.2, 75.0))
        assertTrue(high.relativeRateIndexPercent > low.relativeRateIndexPercent)
    }

    @Test fun rejectsInvalidInputs() {
        assertThrows(IllegalArgumentException::class.java) {
            DiffusionSimulation.evaluate(DiffusionConditions(Double.NaN, 0.2, 50.0))
        }
        assertThrows(IllegalArgumentException::class.java) {
            DiffusionSimulation.evaluate(DiffusionConditions(-0.1, 0.2, 50.0))
        }
        assertThrows(IllegalArgumentException::class.java) {
            DiffusionSimulation.evaluate(DiffusionConditions(0.1, 0.2, 101.0))
        }
    }
}
