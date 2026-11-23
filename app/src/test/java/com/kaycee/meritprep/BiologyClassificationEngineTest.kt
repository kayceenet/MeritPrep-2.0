package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyClassificationEngineTest {
    @Test fun distinguishesTheSixTeachingSpecimens() {
        val cases = listOf(
            SpecimenFeatures(true, false, false, false, false) to "Moss (a non-vascular plant)",
            SpecimenFeatures(true, true, false, false, false) to "Fern (a seedless vascular plant)",
            SpecimenFeatures(true, true, true, false, false) to "Pine (a conifer)",
            SpecimenFeatures(true, true, true, true, true) to "Maize (a monocot)",
            SpecimenFeatures(true, true, true, true, false) to "Bean (a eudicot)",
            SpecimenFeatures(false, false, false, false, false, true) to "Mushroom (fungus)"
        )
        cases.forEach { (features, expected) ->
            val result = BiologyClassificationEngine.identify(features)
            assertEquals(expected, result.identification)
            assertTrue(result.keySteps.isNotEmpty())
            assertTrue(result.explanation.isNotBlank())
        }
    }

    @Test fun rejectsAnUnsupportedNonPhotosyntheticNonFungalSpecimen() {
        val result = BiologyClassificationEngine.identify(
            SpecimenFeatures(false, false, false, false, false, false)
        )
        assertNull(result.identification)
        assertTrue(result.explanation.contains("broader key"))
    }

    @Test fun rejectsContradictoryFungalAndPhotosyntheticObservations() {
        val result = BiologyClassificationEngine.identify(
            SpecimenFeatures(true, false, false, false, false, true)
        )
        assertNull(result.identification)
        assertTrue(result.explanation.contains("Recheck"))
    }

    @Test fun rejectsFruitOrCotyledonClaimsWhenNoSeedsAreObserved() {
        val result = BiologyClassificationEngine.identify(
            SpecimenFeatures(true, true, false, true, true)
        )
        assertNull(result.identification)
    }

    @Test fun keyExplainsTheDifferenceBetweenSporeBearingAndSeedProducingPlants() {
        val fern = BiologyClassificationEngine.identify(SpecimenFeatures(true, true, false, false, false))
        val pine = BiologyClassificationEngine.identify(SpecimenFeatures(true, true, true, false, false))
        assertTrue(fern.keySteps.any { it.contains("does not produce seeds") })
        assertTrue(pine.keySteps.any { it.contains("not enclosed in a fruit") })
    }
}
