package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class XLinkedInheritanceSimulationTest {
    @Test
    fun carrierMotherAndUnaffectedFatherProduceHalfAffectedSonsAndNoAffectedDaughters() {
        val result = XLinkedInheritanceSimulation.evaluate(
            XLinkedMotherGenotype.CARRIER,
            XLinkedFatherGenotype.UNAFFECTED
        )
        assertEquals(4, result.fourPossibleCombinations.size)
        assertEquals(25.0, result.outcomes.first { it.genotype == "XᴴXᴴ" }.percentage, 0.000001)
        assertEquals(25.0, result.outcomes.first { it.genotype == "XᴴXʰ" }.percentage, 0.000001)
        assertEquals(25.0, result.outcomes.first { it.genotype == "XᴴY" }.percentage, 0.000001)
        assertEquals(25.0, result.outcomes.first { it.genotype == "XʰY" }.percentage, 0.000001)
        assertTrue(result.outcomes.none { it.phenotype == "Affected female" })
    }

    @Test
    fun affectedMotherAndUnaffectedFatherProduceCarrierDaughtersAndAffectedSons() {
        val result = XLinkedInheritanceSimulation.evaluate(
            XLinkedMotherGenotype.AFFECTED,
            XLinkedFatherGenotype.UNAFFECTED
        )
        assertEquals(50.0, result.outcomes.first { it.phenotype == "Carrier female" }.percentage, 0.000001)
        assertEquals(50.0, result.outcomes.first { it.phenotype == "Affected male" }.percentage, 0.000001)
    }

    @Test
    fun unaffectedMotherAndAffectedFatherProduceUnaffectedSonsAndCarrierDaughters() {
        val result = XLinkedInheritanceSimulation.evaluate(
            XLinkedMotherGenotype.UNAFFECTED,
            XLinkedFatherGenotype.AFFECTED
        )
        assertEquals(50.0, result.outcomes.first { it.phenotype == "Carrier female" }.percentage, 0.000001)
        assertEquals(50.0, result.outcomes.first { it.phenotype == "Unaffected male" }.percentage, 0.000001)
    }

    @Test
    fun everyXLinkedCrossHasFourCombinationsAndOneHundredPercentTotal() {
        XLinkedMotherGenotype.values().forEach { mother ->
            XLinkedFatherGenotype.values().forEach { father ->
                val result = XLinkedInheritanceSimulation.evaluate(mother, father)
                assertEquals(4, result.fourPossibleCombinations.size)
                assertEquals(100.0, result.outcomes.sumOf { it.percentage }, 0.000001)
            }
        }
    }
}
