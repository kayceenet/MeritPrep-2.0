package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyDrawingGuideTest {
    @Test fun drawingChecklistHasUniquePracticalCriteria() {
        val criteria = BiologyDrawingGuide.criteria
        assertEquals(8, criteria.size)
        assertEquals(criteria.size, criteria.map { it.id }.toSet().size)
        assertTrue(criteria.all { it.title.isNotBlank() && it.rationale.isNotBlank() })
    }

    @Test fun checklistCoversLineQualityLabelsProportionAndObservation() {
        val text = BiologyDrawingGuide.criteria.joinToString(" ") { it.title + " " + it.rationale }.lowercase()
        listOf("label", "proportion", "observe", "lines", "shade").forEach { keyword ->
            assertTrue("Missing drawing guidance: $keyword", text.contains(keyword))
        }
    }
}
