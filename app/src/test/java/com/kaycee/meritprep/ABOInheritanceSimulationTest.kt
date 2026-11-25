package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ABOInheritanceSimulationTest {
    @Test
    fun heterozygousAByHeterozygousBCanProduceAllFourBloodGroups() {
        val result = ABOInheritanceSimulation.evaluate(ABOGenotype.A_HETEROZYGOUS, ABOGenotype.B_HETEROZYGOUS)
        assertEquals(4, result.fourPossibleCombinations.size)
        assertEquals(setOf("A", "B", "AB", "O"), result.outcomes.map { it.phenotype }.toSet())
        assertTrue(result.outcomes.all { it.percentage == 25.0 })
        assertEquals(100.0, result.outcomes.sumOf { it.percentage }, 0.000001)
    }

    @Test
    fun twoOParentsCanProduceOnlyOBloodGroupOffspring() {
        val result = ABOInheritanceSimulation.evaluate(ABOGenotype.O, ABOGenotype.O)
        assertEquals(listOf("ii", "ii", "ii", "ii"), result.fourPossibleCombinations)
        assertEquals(listOf("O"), result.outcomes.map { it.phenotype })
        assertEquals(100.0, result.outcomes.single().percentage, 0.000001)
    }

    @Test
    fun twoABParentsCannotProduceOBloodGroupOffspring() {
        val result = ABOInheritanceSimulation.evaluate(ABOGenotype.AB, ABOGenotype.AB)
        assertEquals(setOf("A", "AB", "B"), result.outcomes.map { it.phenotype }.toSet())
        assertFalse(result.outcomes.any { it.phenotype == "O" })
    }

    @Test
    fun heterozygousAAndOParentsProduceExpectedHalfAAndHalfO() {
        val result = ABOInheritanceSimulation.evaluate(ABOGenotype.A_HETEROZYGOUS, ABOGenotype.O)
        assertEquals(50.0, result.outcomes.first { it.phenotype == "A" }.percentage, 0.000001)
        assertEquals(50.0, result.outcomes.first { it.phenotype == "O" }.percentage, 0.000001)
    }

    @Test
    fun everyABOCrossHasFourCombinationsAndProbabilitiesTotalOneHundred() {
        ABOGenotype.values().forEach { first ->
            ABOGenotype.values().forEach { second ->
                val result = ABOInheritanceSimulation.evaluate(first, second)
                assertEquals(4, result.fourPossibleCombinations.size)
                assertEquals(100.0, result.outcomes.sumOf { it.percentage }, 0.000001)
                assertTrue(result.outcomes.all { it.countOutOfFour in 1..4 })
            }
        }
    }
}
