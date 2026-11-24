package com.kaycee.meritprep

import com.kaycee.meritprep.data.PhysicsPracticalEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsPracticalEngineTest {
    @Test fun calculatesUniformMotionWithConstantAcceleration() {
        val result = PhysicsPracticalEngine.uniformMotion(2.0, 1.5, 4.0)
        assertEquals(8.0, result.finalVelocityMps, 0.000001)
        assertEquals(20.0, result.displacementM, 0.000001)
    }

    @Test fun allowsNegativeAccelerationAndZeroElapsedTime() {
        val result = PhysicsPracticalEngine.uniformMotion(12.0, -2.0, 3.0)
        assertEquals(6.0, result.finalVelocityMps, 0.000001)
        assertEquals(27.0, result.displacementM, 0.000001)
        val zero = PhysicsPracticalEngine.uniformMotion(4.0, 5.0, 0.0)
        assertEquals(4.0, zero.finalVelocityMps, 0.000001)
        assertEquals(0.0, zero.displacementM, 0.000001)
    }

    @Test fun rejectsInvalidTimeAndNonFiniteMotionInputs() {
        listOf(-1.0, Double.NaN, Double.POSITIVE_INFINITY).forEach {
            try {
                PhysicsPracticalEngine.uniformMotion(0.0, 1.0, it)
                throw AssertionError("Invalid time should be rejected")
            } catch (_: IllegalArgumentException) { }
        }
    }

    @Test fun calculatesPerpendicularVectorMagnitudeAndDirection() {
        val result = PhysicsPracticalEngine.perpendicularVector(3.0, 4.0)
        assertEquals(5.0, result.magnitude, 0.000001)
        assertEquals(53.130102, result.angleDegreesFromPositiveX, 0.00001)
    }

    @Test fun handlesVectorQuadrantsAndZeroVector() {
        assertEquals(135.0, PhysicsPracticalEngine.perpendicularVector(-1.0, 1.0).angleDegreesFromPositiveX, 0.000001)
        assertEquals(0.0, PhysicsPracticalEngine.perpendicularVector(0.0, 0.0).magnitude, 0.000001)
    }

    @Test fun calculatesMomentsFromForceAndPerpendicularArm() {
        assertEquals(4.0, PhysicsPracticalEngine.moment(10.0, 0.4), 0.000001)
    }

    @Test fun calculatesMachineAdvantageAndEfficiency() {
        val result = PhysicsPracticalEngine.machinePerformance(300.0, 100.0, 4.0)
        assertEquals(3.0, result.mechanicalAdvantage, 0.000001)
        assertEquals(75.0, result.efficiencyPercent, 0.000001)
        assertTrue(PhysicsPracticalEngine.machinePerformance(500.0, 10.0, 1.0).efficiencyPercent > 100.0)
    }

    @Test fun rejectsInvalidMachineInputs() {
        listOf(0.0, -1.0, Double.NaN).forEach {
            try {
                PhysicsPracticalEngine.machinePerformance(100.0, it, 2.0)
                throw AssertionError("Invalid effort should be rejected")
            } catch (_: IllegalArgumentException) { }
        }
        try {
            PhysicsPracticalEngine.machinePerformance(100.0, 10.0, 0.0)
            throw AssertionError("Invalid velocity ratio should be rejected")
        } catch (_: IllegalArgumentException) { }
    }
}
