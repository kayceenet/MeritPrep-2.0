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
    fun sortsGraphPointsAndUsesMeanOfRepeatedReadings() {
        val points = BiologyExperimentAnalysis.graphPoints(
            listOf("20" to "4, 6", "10" to "2, 4", "30" to "8, 10")
        )
        assertEquals(10.0, points[0].independentValue, 0.000001)
        assertEquals(3.0, points[0].meanResult, 0.000001)
        assertEquals(2, points[0].readingCount)
        assertEquals(20.0, points[1].independentValue, 0.000001)
        assertEquals(5.0, points[1].meanResult, 0.000001)
    }

    @Test
    fun rejectsIncompleteDuplicateOrNonNumericGraphRows() {
        assertThrows(IllegalArgumentException::class.java) {
            BiologyExperimentAnalysis.graphPoints(listOf("10" to "2", "20" to ""))
        }
        assertThrows(IllegalArgumentException::class.java) {
            BiologyExperimentAnalysis.graphPoints(listOf("10" to "2", "10" to "3"))
        }
        assertThrows(IllegalArgumentException::class.java) {
            BiologyExperimentAnalysis.graphPoints(listOf("ten" to "2", "20" to "3"))
        }
    }

    @Test
    fun describesIncreasingDecreasingConstantAndVariablePatterns() {
        val increasing = listOf(BiologyExperimentPoint(1.0, 2.0, 1), BiologyExperimentPoint(2.0, 4.0, 1), BiologyExperimentPoint(3.0, 7.0, 1))
        val decreasing = increasing.map { it.copy(meanResult = 10.0 - it.meanResult) }
        val constant = increasing.map { it.copy(meanResult = 5.0) }
        val variable = listOf(BiologyExperimentPoint(1.0, 2.0, 1), BiologyExperimentPoint(2.0, 7.0, 1), BiologyExperimentPoint(3.0, 4.0, 1))
        assertEquals(true, BiologyExperimentAnalysis.describeTrend(increasing).contains("increases"))
        assertEquals(true, BiologyExperimentAnalysis.describeTrend(decreasing).contains("decreases"))
        assertEquals(true, BiologyExperimentAnalysis.describeTrend(constant).contains("constant"))
        assertEquals(true, BiologyExperimentAnalysis.describeTrend(variable).contains("fluctuates"))
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