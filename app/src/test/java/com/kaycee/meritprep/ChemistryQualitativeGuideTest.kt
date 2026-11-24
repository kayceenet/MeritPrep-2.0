package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryQualitativeGuideTest {
    @Test fun includesCoreAnionAndGasTests() {
        val targets = ChemistryQualitativeGuide.tests.map { it.target }.toSet()
        listOf("Carbonate (CO₃²⁻)", "Sulfate (SO₄²⁻)", "Chloride (Cl⁻)", "Bromide (Br⁻)", "Iodide (I⁻)", "Nitrate (NO₃⁻)", "Sulfite (SO₃²⁻)", "Carbon dioxide (CO₂)", "Ammonia (NH₃)", "Hydrogen (H₂)", "Oxygen (O₂)", "Chlorine (Cl₂)", "Sulfur dioxide (SO₂)", "Hydrogen chloride (HCl)").forEach {
            assertTrue("Missing qualitative test: $it", it in targets)
        }
    }

    @Test fun everyReferenceHasObservationInferenceAndCaution() {
        ChemistryQualitativeGuide.tests.forEach {
            assertTrue(it.reagentOrTest.isNotBlank())
            assertTrue(it.observation.isNotBlank())
            assertTrue(it.inference.isNotBlank())
            assertTrue(it.caution.isNotBlank())
        }
    }

    @Test fun idsAreUniqueAndCategoriesAreValid() {
        val entries = ChemistryQualitativeGuide.tests
        assertEquals(entries.size, entries.map { it.id }.distinct().size)
        assertFalse(entries.any { it.category !in setOf("Anions", "Gases") })
    }

    @Test fun hazardousGasTestsCarryExplicitWarnings() {
        val hazardous = ChemistryQualitativeGuide.tests.filter { it.target in setOf("Chlorine (Cl₂)", "Sulfur dioxide (SO₂)", "Ammonia (NH₃)", "Hydrogen (H₂)", "Hydrogen chloride (HCl)") }
        assertEquals(5, hazardous.size)
        hazardous.forEach { assertTrue(it.caution.isNotBlank()) }
    }
}
