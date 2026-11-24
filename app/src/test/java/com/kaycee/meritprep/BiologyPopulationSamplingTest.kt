package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertNotNull
import org.junit.Test

class BiologyPopulationSamplingTest {
    @Test
    fun estimatesDensityAndPopulationFromEqualAreaQuadrats() {
        val result = estimatePopulationFromQuadrats(
            counts = listOf(4, 6, 5, 7, 3),
            quadratAreaM2 = 1.0,
            habitatAreaM2 = 100.0
        )

        assertNotNull(result)
        assertEquals(25, result!!.totalCount)
        assertEquals(5, result.quadratCount)
        assertEquals(5.0, result.sampledAreaM2, 0.0001)
        assertEquals(5.0, result.meanPerQuadrat, 0.0001)
        assertEquals(5.0, result.densityPerM2, 0.0001)
        assertEquals(500.0, result.estimatedPopulation, 0.0001)
    }

    @Test
    fun acceptsZeroCountsButRejectsInvalidSamplingInputs() {
        assertEquals(0.0, estimatePopulationFromQuadrats(listOf(0, 0, 0), 1.0, 50.0)!!.estimatedPopulation, 0.0001)
        assertNull(estimatePopulationFromQuadrats(emptyList(), 1.0, 50.0))
        assertNull(estimatePopulationFromQuadrats(listOf(1, -1), 1.0, 50.0))
        assertNull(estimatePopulationFromQuadrats(listOf(1), 0.0, 50.0))
        assertNull(estimatePopulationFromQuadrats(listOf(1), 1.0, -50.0))
        assertNull(estimatePopulationFromQuadrats(listOf(1, 2), 1.0, 1.0))
        assertNull(estimatePopulationFromQuadrats(listOf(Int.MAX_VALUE, 1), 1.0, 10.0))
        assertNull(estimatePopulationFromQuadrats(listOf(1), Double.NaN, 50.0))
        assertNull(estimatePopulationFromQuadrats(listOf(1), 1.0, Double.POSITIVE_INFINITY))
    }

    @Test
    fun rejectsOverflowingSampleAreaAndNonFiniteEstimate() {
        assertNull(estimatePopulationFromQuadrats(listOf(1, 2), Double.MAX_VALUE, Double.MAX_VALUE))
        assertNull(estimatePopulationFromQuadrats(listOf(Int.MAX_VALUE), 1.0E-308, 1.0E308))
    }
}
