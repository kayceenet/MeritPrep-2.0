package com.kaycee.meritprep

import com.kaycee.meritprep.data.PhysicsPracticalAnalysisEngine
import com.kaycee.meritprep.data.PracticalGraphPoint
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsPracticalAnalysisEngineTest {
    @Test fun fitsStraightLineAndReturnsGradientInterceptAndR2() {
        val result = PhysicsPracticalAnalysisEngine.linearFit(
            listOf(
                PracticalGraphPoint(1.0, 3.0),
                PracticalGraphPoint(2.0, 5.0),
                PracticalGraphPoint(3.0, 7.0),
                PracticalGraphPoint(4.0, 9.0)
            )
        )
        assertEquals(2.0, result.gradient, 1e-9)
        assertEquals(1.0, result.intercept, 1e-9)
        assertEquals(1.0, result.coefficientOfDetermination, 1e-9)
    }

    @Test fun handlesNoisyExperimentalReadings() {
        val result = PhysicsPracticalAnalysisEngine.linearFit(
            listOf(
                PracticalGraphPoint(1.0, 2.1),
                PracticalGraphPoint(2.0, 3.9),
                PracticalGraphPoint(3.0, 6.2),
                PracticalGraphPoint(4.0, 7.8)
            )
        )
        assertEquals(1.98, result.gradient, 0.05)
        assertTrue(result.coefficientOfDetermination in 0.99..1.0)
    }

    @Test fun calculatesGravityFromPendulumGraphGradient() {
        assertEquals(9.8696044, PhysicsPracticalAnalysisEngine.gravityFromPendulumGradient(4.0), 1e-6)
    }

    @Test fun rejectsInsufficientNonFiniteAndDegenerateData() {
        assertThrows(IllegalArgumentException::class.java) {
            PhysicsPracticalAnalysisEngine.linearFit(listOf(PracticalGraphPoint(1.0, 2.0)))
        }
        assertThrows(IllegalArgumentException::class.java) {
            PhysicsPracticalAnalysisEngine.linearFit(listOf(PracticalGraphPoint(1.0, 2.0), PracticalGraphPoint(1.0, 3.0)))
        }
        assertThrows(IllegalArgumentException::class.java) {
            PhysicsPracticalAnalysisEngine.linearFit(listOf(PracticalGraphPoint(Double.NaN, 2.0), PracticalGraphPoint(2.0, 3.0)))
        }
        assertThrows(IllegalArgumentException::class.java) {
            PhysicsPracticalAnalysisEngine.gravityFromPendulumGradient(0.0)
        }
    }
}
