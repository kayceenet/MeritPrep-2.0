package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class JambBiologyDeepContentTest {
    @Test
    fun livingOrganismsCoversEveryJambCandidateObjectiveWithIndependentChecks() {
        val objectives = JambBiologyDeepContent.forTopic("bio-cell")

        assertEquals(5, objectives.size)
        assertTrue(objectives.all { it.points.isNotEmpty() && it.checks.size >= 2 })
        assertTrue(objectives.all { it.traps.isNotEmpty() })
        assertTrue(objectives.flatMap { it.points }.all {
            it.heading.isNotBlank() && it.teaching.length >= 250 &&
                it.example.isNotBlank() && it.examFocus.isNotBlank()
        })
        assertTrue(objectives.flatMap { it.checks }.all {
            it.options.size == 4 && it.answer in it.options.indices && it.explanation.isNotBlank()
        })
    }

    @Test
    fun objectiveChecksHaveUniqueStableIds() {
        val ids = listOf("bio-cell", "bio-classification", "bio-adaptation", "bio-plant-anatomy")\n            .flatMap { JambBiologyDeepContent.forTopic(it) }\n            .flatMap { it.checks }\n            .map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun evolutionUnitCoversItsSyllabusDomainsWithIndependentConceptChecks() {
        val objectives = JambBiologyDeepContent.forTopic("bio-classification")

        assertEquals(10, objectives.size)
        assertTrue(objectives.all { it.points.isNotEmpty() && it.checks.size >= 2 })
        assertTrue(objectives.all { it.traps.isNotEmpty() })
        assertTrue(objectives.flatMap { it.points }.all {
            it.heading.isNotBlank() && it.teaching.length >= 250 &&
                it.example.isNotBlank() && it.examFocus.isNotBlank()
        })
        assertTrue(objectives.flatMap { it.checks }.all {
            it.options.size == 4 && it.answer in it.options.indices && it.explanation.isNotBlank()
        })
    }

    @Test
    fun adaptationUnitCoversJambObjectivesAndIndependentChecks() {
        val objectives = JambBiologyDeepContent.forTopic("bio-adaptation")

        assertEquals(5, objectives.size)
        assertTrue(objectives.all { it.points.isNotEmpty() && it.checks.size >= 2 })
        assertTrue(objectives.all { it.traps.isNotEmpty() })
        assertTrue(objectives.flatMap { it.points }.all {
            it.heading.isNotBlank() && it.teaching.length >= 250 &&
                it.example.isNotBlank() && it.examFocus.isNotBlank()
        })
        assertTrue(objectives.flatMap { it.checks }.all {
            it.options.size == 4 && it.answer in it.options.indices && it.explanation.isNotBlank()
        })
    }

    @Test
    fun plantAndMammalianStructureUnitCoversFiveObjectivesAndConceptChecks() {
        val objectives = JambBiologyDeepContent.forTopic("bio-plant-anatomy")

        assertEquals(5, objectives.size)
        assertTrue(objectives.all { it.points.isNotEmpty() && it.checks.size >= 3 })
        assertTrue(objectives.all { it.traps.isNotEmpty() })
        assertTrue(objectives.flatMap { it.points }.all {
            it.heading.isNotBlank() && it.teaching.length >= 250 &&
                it.example.isNotBlank() && it.examFocus.isNotBlank()
        })
        assertTrue(objectives.flatMap { it.checks }.all {
            it.options.size == 4 && it.answer in it.options.indices && it.explanation.isNotBlank()
        })
    }

    @Test
    fun allBiologyDeepConceptChecksUseUniqueIds() {
        val ids = listOf("bio-cell", "bio-classification", "bio-adaptation", "bio-plant-anatomy")
            .flatMap { JambBiologyDeepContent.forTopic(it) }
            .flatMap { it.checks }
            .map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun unrelatedTopicsDoNotReceiveBiologyCellContent() {
        assertTrue(JambBiologyDeepContent.forTopic("chem-atomic-bonding").isEmpty())
        assertTrue(JambBiologyDeepContent.forTopic("bio-nutrition").isEmpty())
    }
}
