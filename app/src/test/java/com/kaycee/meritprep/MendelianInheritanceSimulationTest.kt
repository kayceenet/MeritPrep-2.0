package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MendelianInheritanceSimulationTest {
    @Test
    fun heterozygousCrossProducesExpectedOneTwoOneGenotypeRatio() {
        val result = MendelianInheritanceSimulation.evaluate(
            MendelianGenotype.HETEROZYGOUS,
            MendelianGenotype.HETEROZYGOUS
        )
        assertEquals(listOf("AA", "Aa", "Aa", "aa"), result.fourPossibleCombinations)
        assertEquals(25.0, result.outcomes.first { it.genotype == "AA" }.percentage, 0.000001)
        assertEquals(50.0, result.outcomes.first { it.genotype == "Aa" }.percentage, 0.000001)
        assertEquals(25.0, result.outcomes.first { it.genotype == "aa" }.percentage, 0.000001)
        assertEquals(75.0, result.dominantPhenotypePercentage, 0.000001)
        assertEquals(25.0, result.recessivePhenotypePercentage, 0.000001)
    }

    @Test
    fun homozygousRecessiveCrossProducesOnlyRecessiveOffspring() {
        val result = MendelianInheritanceSimulation.evaluate(
            MendelianGenotype.HOMOZYGOUS_RECESSIVE,
            MendelianGenotype.HOMOZYGOUS_RECESSIVE
        )
        assertEquals(listOf("aa", "aa", "aa", "aa"), result.fourPossibleCombinations)
        assertEquals(100.0, result.recessivePhenotypePercentage, 0.000001)
    }

    @Test
    fun homozygousDominantCrossCannotProduceRecessiveOffspring() {
        val result = MendelianInheritanceSimulation.evaluate(
            MendelianGenotype.HOMOZYGOUS_DOMINANT,
            MendelianGenotype.HETEROZYGOUS
        )
        assertEquals(0.0, result.recessivePhenotypePercentage, 0.000001)
        assertEquals(50.0, result.outcomes.first { it.genotype == "AA" }.percentage, 0.000001)
        assertEquals(50.0, result.outcomes.first { it.genotype == "Aa" }.percentage, 0.000001)
    }

    @Test
    fun everyCrossHasFourEquallyLikelyCombinationsAndHundredPercentTotal() {
        MendelianGenotype.values().forEach { first ->
            MendelianGenotype.values().forEach { second ->
                val result = MendelianInheritanceSimulation.evaluate(first, second)
                assertEquals(4, result.fourPossibleCombinations.size)
                assertEquals(100.0, result.outcomes.sumOf { it.percentage }, 0.000001)
                assertEquals(100.0, result.dominantPhenotypePercentage + result.recessivePhenotypePercentage, 0.000001)
                assertTrue(result.outcomes.all { it.countOutOfFour in 1..4 })
            }
        }
    }
}
