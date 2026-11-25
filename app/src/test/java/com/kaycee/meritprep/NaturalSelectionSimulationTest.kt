package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NaturalSelectionSimulationTest {
    @Test
    fun higherDarkTraitSurvivalRaisesItsFrequencyAcrossGenerations() {
        val result = NaturalSelectionSimulation.evaluate(
            NaturalSelectionConditions(50, 50, 40.0, 80.0, 5)
        )
        assertTrue(result.generations.last().darkTraitFrequencyPercent > 50.0)
        assertTrue(result.generations.last().lightTraitFrequencyPercent < 50.0)
        assertEquals("dark trait", result.moreFavouredTrait)
    }

    @Test
    fun higherLightTraitSurvivalRaisesItsFrequencyAcrossGenerations() {
        val result = NaturalSelectionSimulation.evaluate(
            NaturalSelectionConditions(50, 50, 80.0, 40.0, 4)
        )
        assertTrue(result.generations.last().lightTraitFrequencyPercent > 50.0)
        assertEquals("light trait", result.moreFavouredTrait)
    }

    @Test
    fun equalSurvivalLeavesTraitFrequenciesUnchanged() {
        val result = NaturalSelectionSimulation.evaluate(
            NaturalSelectionConditions(30, 70, 60.0, 60.0, 6)
        )
        result.generations.forEach { row ->
            assertEquals(30.0, row.lightTraitFrequencyPercent, 0.000001)
            assertEquals(70.0, row.darkTraitFrequencyPercent, 0.000001)
        }
        assertEquals("neither trait", result.moreFavouredTrait)
    }

    @Test
    fun frequenciesRemainNormalisedInEveryGeneration() {
        val result = NaturalSelectionSimulation.evaluate(
            NaturalSelectionConditions(25, 75, 35.0, 90.0, 10)
        )
        result.generations.forEach { row ->
            assertEquals(100.0, row.lightTraitFrequencyPercent + row.darkTraitFrequencyPercent, 0.000001)
        }
        assertEquals(11, result.generations.size)
    }

    @Test
    fun rejectsInvalidStartingPopulationSurvivalAndGenerationValues() {
        val invalid = listOf(
            NaturalSelectionConditions(0, 0, 50.0, 50.0, 5),
            NaturalSelectionConditions(-1, 10, 50.0, 50.0, 5),
            NaturalSelectionConditions(10, 10, -1.0, 50.0, 5),
            NaturalSelectionConditions(10, 10, 50.0, Double.NaN, 5),
            NaturalSelectionConditions(10, 10, 0.0, 0.0, 5),
            NaturalSelectionConditions(10, 10, 50.0, 50.0, 0),
            NaturalSelectionConditions(10, 10, 50.0, 50.0, 21)
        )
        invalid.forEach { conditions ->
            var rejected = false
            try {
                NaturalSelectionSimulation.evaluate(conditions)
            } catch (_: IllegalArgumentException) {
                rejected = true
            }
            assertTrue("Expected invalid conditions to be rejected: $conditions", rejected)
        }
    }
}
