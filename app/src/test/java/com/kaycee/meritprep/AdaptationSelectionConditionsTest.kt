package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AdaptationSelectionConditionsTest {
    @Test
    fun darkBarkFavoursDarkVariantInTheIllustrativeModel() {
        val conditions = AdaptationSelectionConditions(SelectionHabitat.DARK_BARK, 50, 60, 8)
        val result = NaturalSelectionSimulation.evaluate(conditions.toSimulationConditions())
        assertTrue(result.generations.last().darkTraitFrequencyPercent > 50.0)
        assertEquals("dark trait", result.moreFavouredTrait)
    }

    @Test
    fun lightSandFavoursLightVariantInTheIllustrativeModel() {
        val conditions = AdaptationSelectionConditions(SelectionHabitat.LIGHT_SAND, 50, 60, 8)
        val result = NaturalSelectionSimulation.evaluate(conditions.toSimulationConditions())
        assertTrue(result.generations.last().lightTraitFrequencyPercent > 50.0)
        assertEquals("light trait", result.moreFavouredTrait)
    }

    @Test
    fun mixedBackgroundWithEqualRelativeFitnessDoesNotShiftFrequencies() {
        val conditions = AdaptationSelectionConditions(SelectionHabitat.MIXED, 35, 60, 10)
        val result = NaturalSelectionSimulation.evaluate(conditions.toSimulationConditions())
        result.generations.forEach {
            assertEquals(65.0, it.lightTraitFrequencyPercent, 0.000001)
            assertEquals(35.0, it.darkTraitFrequencyPercent, 0.000001)
        }
        assertEquals("neither trait", result.moreFavouredTrait)
    }

    @Test
    fun invalidInitialFrequencyIntensityAndGenerationValuesAreRejected() {
        val invalid = listOf(
            Triple(0, 60, 5),
            Triple(100, 60, 5),
            Triple(50, 9, 5),
            Triple(50, 91, 5),
            Triple(50, 60, 0),
            Triple(50, 60, 21)
        )
        invalid.forEach { (initial, intensity, generations) ->
            var rejected = false
            try {
                AdaptationSelectionConditions(SelectionHabitat.DARK_BARK, initial, intensity, generations)
            } catch (_: IllegalArgumentException) {
                rejected = true
            }
            assertTrue("Expected invalid model settings to be rejected", rejected)
        }
    }
}
