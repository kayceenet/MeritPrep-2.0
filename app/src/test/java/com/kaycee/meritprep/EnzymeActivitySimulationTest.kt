package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EnzymeActivitySimulationTest {
    @Test
    fun eachEnzymeProfileHasItsOwnIllustrativePHOptimum() {
        val cases = listOf(
            EnzymeProfile.SALIVARY_AMYLASE to 7.0,
            EnzymeProfile.PEPSIN to 2.0,
            EnzymeProfile.TRYPSIN to 8.0
        )
        cases.forEach { (profile, optimumPh) ->
            val result = EnzymeActivitySimulation.evaluate(EnzymeConditions(profile, 37.0, optimumPh))
            assertEquals(100.0, result.activityIndexPercent, 0.0001)
            assertEquals(100.0, result.pHResponsePercent, 0.0001)
        }
    }

    @Test
    fun activityFallsWhenPHMovesAwayFromTheSelectedEnzymeOptimum() {
        val optimum = EnzymeActivitySimulation.evaluate(
            EnzymeConditions(EnzymeProfile.PEPSIN, 37.0, 2.0)
        )
        val unsuitablePH = EnzymeActivitySimulation.evaluate(
            EnzymeConditions(EnzymeProfile.PEPSIN, 37.0, 7.0)
        )
        assertTrue(unsuitablePH.activityIndexPercent < optimum.activityIndexPercent)
        assertEquals("pH", unsuitablePH.limitingCondition)
    }

    @Test
    fun activityFallsAtLowAndHighTemperaturesInTheIllustrativeModel() {
        val cold = EnzymeActivitySimulation.evaluate(EnzymeConditions(EnzymeProfile.SALIVARY_AMYLASE, 10.0, 7.0))
        val optimum = EnzymeActivitySimulation.evaluate(EnzymeConditions(EnzymeProfile.SALIVARY_AMYLASE, 37.0, 7.0))
        val hot = EnzymeActivitySimulation.evaluate(EnzymeConditions(EnzymeProfile.SALIVARY_AMYLASE, 70.0, 7.0))
        assertTrue(cold.activityIndexPercent < optimum.activityIndexPercent)
        assertTrue(hot.activityIndexPercent < optimum.activityIndexPercent)
    }

    @Test
    fun rejectsNonFiniteAndOutOfRangeConditions() {
        val invalid = listOf(
            EnzymeConditions(EnzymeProfile.PEPSIN, -1.0, 2.0),
            EnzymeConditions(EnzymeProfile.PEPSIN, 37.0, 15.0),
            EnzymeConditions(EnzymeProfile.PEPSIN, Double.NaN, 2.0),
            EnzymeConditions(EnzymeProfile.PEPSIN, 37.0, Double.POSITIVE_INFINITY)
        )
        invalid.forEach { conditions ->
            var rejected = false
            try {
                EnzymeActivitySimulation.evaluate(conditions)
            } catch (_: IllegalArgumentException) {
                rejected = true
            }
            assertTrue("Expected invalid conditions to be rejected: $conditions", rejected)
        }
    }
}
