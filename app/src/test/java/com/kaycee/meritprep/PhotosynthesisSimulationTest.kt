package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhotosynthesisSimulationTest {
    @Test
    fun returnsBoundedRelativeIndicesAndIdentifiesLimitingFactor() {
        val result = PhotosynthesisSimulation.evaluate(
            PhotosynthesisConditions(lightIntensity = 10.0, carbonDioxideIndex = 80.0, temperatureCelsius = 30.0)
        )
        assertTrue(result.relativeRatePercent in 0.0..100.0)
        assertEquals("light intensity", result.limitingFactor)
        assertTrue(result.lightResponsePercent < result.carbonDioxideResponsePercent)
    }

    @Test
    fun increasingTheLimitingInputRaisesTheIllustrativeRateUntilAnotherFactorLimits() {
        val lowLight = PhotosynthesisSimulation.evaluate(
            PhotosynthesisConditions(10.0, 100.0, 30.0)
        )
        val higherLight = PhotosynthesisSimulation.evaluate(
            PhotosynthesisConditions(50.0, 100.0, 30.0)
        )
        assertTrue(higherLight.relativeRatePercent > lowLight.relativeRatePercent)
        assertEquals("carbon dioxide availability", higherLight.limitingFactor)
    }

    @Test
    fun temperatureResponseFallsOnEitherSideOfTheIllustrativeOptimum() {
        val cool = PhotosynthesisSimulation.evaluate(PhotosynthesisConditions(100.0, 100.0, 15.0))
        val optimum = PhotosynthesisSimulation.evaluate(PhotosynthesisConditions(100.0, 100.0, 30.0))
        val hot = PhotosynthesisSimulation.evaluate(PhotosynthesisConditions(100.0, 100.0, 40.0))
        assertTrue(optimum.temperatureResponsePercent > cool.temperatureResponsePercent)
        assertTrue(optimum.temperatureResponsePercent > hot.temperatureResponsePercent)
        assertEquals(100.0, optimum.temperatureResponsePercent, 0.0001)
    }

    @Test
    fun rejectsValuesOutsideTheDocumentedModelRange() {
        val invalid = listOf(
            PhotosynthesisConditions(-1.0, 20.0, 25.0),
            PhotosynthesisConditions(20.0, 101.0, 25.0),
            PhotosynthesisConditions(20.0, 20.0, 46.0),
            PhotosynthesisConditions(Double.NaN, 20.0, 25.0)
        )
        invalid.forEach { conditions ->
            var rejected = false
            try {
                PhotosynthesisSimulation.evaluate(conditions)
            } catch (_: IllegalArgumentException) {
                rejected = true
            }
            assertTrue("Expected invalid conditions to be rejected: $conditions", rejected)
        }
    }
}
