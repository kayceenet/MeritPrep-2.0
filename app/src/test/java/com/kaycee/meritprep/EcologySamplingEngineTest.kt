package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class EcologySamplingEngineTest {
    @Test
    fun estimatesDensityAndHabitatPopulationFromRepeatedQuadrats() {
        val result = EcologySamplingEngine.estimate(
            individualsPerQuadrat = listOf(8, 12, 10, 6),
            quadratAreaSquareMetres = 1.0,
            habitatAreaSquareMetres = 1_000.0
        )
        assertEquals(36, result.totalIndividuals)
        assertEquals(4, result.quadratCount)
        assertEquals(4.0, result.sampledAreaSquareMetres, 0.0001)
        assertEquals(9.0, result.meanDensityPerSquareMetre, 0.0001)
        assertEquals(9_000.0, result.estimatedHabitatPopulation, 0.0001)
    }

    @Test
    fun adjustsDensityForQuadratArea() {
        val result = EcologySamplingEngine.estimate(listOf(10, 10), 0.5, 100.0)
        assertEquals(20.0, result.meanDensityPerSquareMetre, 0.0001)
        assertEquals(2_000.0, result.estimatedHabitatPopulation, 0.0001)
    }

    @Test
    fun rejectsInvalidSamplingInputs() {
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(emptyList(), 1.0, 100.0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(listOf(2, -1), 1.0, 100.0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(listOf(2), 0.0, 100.0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(listOf(2), 1.0, 0.0)
        }
    }
    @Test
    fun rejectsNonFiniteAreasAndOverflowingCalculations() {
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(listOf(1), Double.NaN, 100.0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(listOf(1), 1.0, Double.POSITIVE_INFINITY)
        }
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(listOf(Int.MAX_VALUE, 1), 1.0, 100.0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(listOf(1, 2), Double.MAX_VALUE, Double.MAX_VALUE)
        }
        assertThrows(IllegalArgumentException::class.java) {
            EcologySamplingEngine.estimate(listOf(Int.MAX_VALUE), 1.0E-308, 1.0E308)
        }
    }

}
