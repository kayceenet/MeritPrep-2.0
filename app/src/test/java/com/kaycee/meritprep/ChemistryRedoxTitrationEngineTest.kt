package com.kaycee.meritprep

import com.kaycee.meritprep.data.ChemistryRedoxTitrationEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryRedoxTitrationEngineTest {
    @Test fun calculatesIronIiConcentrationUsingFiveToOneStoichiometry() {
        val result = ChemistryRedoxTitrationEngine.calculateIronIiConcentration(
            permanganateConcentrationMolPerDm3 = 0.02,
            initialReadingsCm3 = listOf(0.0, 0.0, 0.0),
            finalReadingsCm3 = listOf(24.80, 24.85, 25.40),
            ironIiAliquotCm3 = 25.0
        )
        assertEquals(24.825, result.meanTitreCm3, 0.000001)
        assertEquals(0.0004965, result.molesPermanganate, 0.0000001)
        assertEquals(0.0024825, result.molesIronIi, 0.0000001)
        assertEquals(0.0993, result.analyteConcentrationMolPerDm3, 0.000001)
    }

    @Test fun rejectsInvalidConcentrationsAndAliquots() {
        val initial = listOf(0.0, 0.0)
        val final = listOf(20.0, 20.05)
        listOf(0.0, -0.1, Double.NaN, Double.POSITIVE_INFINITY).forEach { value ->
            try {
                ChemistryRedoxTitrationEngine.calculateIronIiConcentration(value, initial, final, 25.0)
                throw AssertionError("Expected invalid permanganate concentration to be rejected")
            } catch (_: IllegalArgumentException) { }
        }
        listOf(0.0, -2.0, Double.NaN).forEach { value ->
            try {
                ChemistryRedoxTitrationEngine.calculateIronIiConcentration(0.02, initial, final, value)
                throw AssertionError("Expected invalid aliquot to be rejected")
            } catch (_: IllegalArgumentException) { }
        }
    }

    @Test fun rejectsNonConcordantTitres() {
        try {
            ChemistryRedoxTitrationEngine.calculateIronIiConcentration(
                0.02, listOf(0.0, 0.0), listOf(20.0, 21.0), 25.0
            )
            throw AssertionError("Expected non-concordant titres to be rejected")
        } catch (error: IllegalArgumentException) {
            assertTrue(error.message.orEmpty().contains("concordant"))
        }
    }
}
