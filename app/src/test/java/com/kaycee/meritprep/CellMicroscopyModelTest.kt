package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CellMicroscopyModelTest {
    @Test
    fun fieldOfViewDecreasesAsTotalMagnificationIncreases() {
        val low = CellMicroscopyModel.observe(CellSpecimen.ONION, 40)
        val medium = CellMicroscopyModel.observe(CellSpecimen.ONION, 100)
        val high = CellMicroscopyModel.observe(CellSpecimen.ONION, 400)

        assertEquals(4.5, low.fieldOfViewMm, 0.0001)
        assertEquals(1.8, medium.fieldOfViewMm, 0.0001)
        assertEquals(0.45, high.fieldOfViewMm, 0.0001)
        assertTrue(low.fieldOfViewMm > medium.fieldOfViewMm)
        assertTrue(medium.fieldOfViewMm > high.fieldOfViewMm)
    }

    @Test
    fun onionEpidermisModelDoesNotClaimChloroplastsAreNormallyVisible() {
        val result = CellMicroscopyModel.observe(CellSpecimen.ONION, 100)
        val chloroplast = result.features.first { it.first == "Chloroplasts" }.second
        assertTrue(chloroplast.contains("Not normally seen"))
        assertTrue(result.features.any { it.first == "Cell outline" && it.second.contains("cell wall") })
    }

    @Test
    fun cheekCellModelDoesNotInventCellWallOrChloroplasts() {
        val result = CellMicroscopyModel.observe(CellSpecimen.CHEEK, 400)
        val features = result.features.toMap()
        assertEquals("Absent", features["Cell wall"])
        assertEquals("Absent", features["Chloroplasts"])
        assertTrue(features["Nucleus"].orEmpty().contains("stained"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidMagnificationIsRejected() {
        CellMicroscopyModel.observe(CellSpecimen.CHEEK, 250)
    }
}
