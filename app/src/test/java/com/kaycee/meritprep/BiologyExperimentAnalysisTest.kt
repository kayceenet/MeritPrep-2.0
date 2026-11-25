package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Test

class BiologyExperimentAnalysisTest {
    @Test
    fun calculatesMeanAndRangeFromRepeatedReadings() {
        val summary = BiologyExperimentAnalysis.summarizeReadings("12, 13; 11")!!
        assertEquals(3, summary.count)
        assertEquals(12.0, summary.mean, 0.000001)
        assertEquals(2.0, summary.range, 0.000001)
        assertEquals(11.0, summary.minimum, 0.000001)
        assertEquals(13.0, summary.maximum, 0.000001)
    }

    @Test
    fun returnsNullWhenNoReadingsHaveBeenEntered() {
        assertNull(BiologyExperimentAnalysis.summarizeReadings(""))
        assertNull(BiologyExperimentAnalysis.summarizeReadings("   "))
    }

    @Test
    fun rejectsIncompleteOrNonNumericReadingsInsteadOfIgnoringThem() {
        assertThrows(IllegalArgumentException::class.java) { BiologyExperimentAnalysis.summarizeReadings("10, , 12") }
        assertThrows(IllegalArgumentException::class.java) { BiologyExperimentAnalysis.summarizeReadings("10, twelve, 12") }
        assertThrows(IllegalArgumentException::class.java) { BiologyExperimentAnalysis.summarizeReadings("NaN") }
        assertThrows(IllegalArgumentException::class.java) { BiologyExperimentAnalysis.summarizeReadings("Infinity") }
    }
}