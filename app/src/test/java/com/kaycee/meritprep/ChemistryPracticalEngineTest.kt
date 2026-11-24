package com.kaycee.meritprep

import com.kaycee.meritprep.data.ChemistryPracticalEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertThrows
import org.junit.Test

class ChemistryPracticalEngineTest {
    @Test fun calculatesMeanFromConcordantBuretteReadings() {
        val mean = ChemistryPracticalEngine.meanConcordantTitre(
            listOf(0.00, 0.10, 0.00),
            listOf(24.80, 24.85, 25.40)
        )
        assertEquals(24.775, mean, 1e-9)
    }

    @Test fun rejectsNonConcordantOrIncompleteTitres() {
        assertThrows(IllegalArgumentException::class.java) {
            ChemistryPracticalEngine.meanConcordantTitre(listOf(0.0, 0.0), listOf(20.0, 21.0))
        }
        assertThrows(IllegalArgumentException::class.java) {
            ChemistryPracticalEngine.meanConcordantTitre(listOf(0.0), listOf(20.0))
        }
    }

    @Test fun calculatesOneToOneTitration() {
        val result = ChemistryPracticalEngine.calculateOneToOneTitration(0.1, 24.8, 25.0)
        assertEquals(24.8, result.titreCm3, 1e-9)
        assertEquals(0.0992, result.analyteConcentrationMolPerDm3, 1e-9)
        assertTrue(result.calculation.contains("C₁V₁"))
    }

    @Test fun rejectsInvalidTitrationValues() {
        assertThrows(IllegalArgumentException::class.java) {
            ChemistryPracticalEngine.calculateOneToOneTitration(0.0, 24.8, 25.0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            ChemistryPracticalEngine.calculateOneToOneTitration(0.1, -1.0, 25.0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            ChemistryPracticalEngine.calculateOneToOneTitration(0.1, 24.8, 0.0)
        }
    }

    @Test fun modelsSelectedConfirmatoryCationObservations() {
        assertTrue(ChemistryPracticalEngine.cationObservation("Cu²⁺", "NH₃(aq)", true).contains("deep-blue"))
        assertTrue(ChemistryPracticalEngine.cationObservation("Fe³⁺", "NaOH(aq)", false).contains("Reddish-brown"))
        assertTrue(ChemistryPracticalEngine.cationObservation("Zn²⁺", "NaOH(aq)", true).contains("dissolves"))
        assertTrue(ChemistryPracticalEngine.cationObservation("NH₄⁺", "NaOH(aq)", true, true).contains("ammonia gas"))
    }

    @Test fun rejectsUnsupportedCationReagentCombination() {
        assertThrows(IllegalArgumentException::class.java) {
            ChemistryPracticalEngine.cationObservation("NH₄⁺", "NH₃(aq)", false)
        }
    }
}
