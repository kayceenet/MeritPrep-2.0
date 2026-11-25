package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertThrows
import org.junit.Test

class CellDivisionSimulationTest {
    @Test fun mitosisMaintainsChromosomeNumberAndProducesTwoDaughterCells() {
        val lesson = CellDivisionSimulation.lesson(CellDivisionType.MITOSIS, 46)
        assertEquals(2, lesson.finalDaughterCellCount)
        assertEquals(46, lesson.finalChromosomeNumberPerDaughter)
        assertEquals("Diploid (2n)", lesson.finalPloidy)
    }

    @Test fun meiosisHalvesChromosomeNumberAndProducesFourHaploidCells() {
        val lesson = CellDivisionSimulation.lesson(CellDivisionType.MEIOSIS, 46)
        assertEquals(4, lesson.finalDaughterCellCount)
        assertEquals(23, lesson.finalChromosomeNumberPerDaughter)
        assertEquals("Haploid (n)", lesson.finalPloidy)
    }

    @Test fun dnaReplicationDoublesChromatidsButNotChromosomes() {
        val mitosis = CellDivisionSimulation.lesson(CellDivisionType.MITOSIS, 6)
        val replicated = mitosis.stages[1]
        assertEquals(6, replicated.chromosomeCountPerCell)
        assertEquals(12, replicated.chromatidCountPerCell)
    }

    @Test fun meiosisSeparatesHomologuesBeforeSisterChromatids() {
        val stages = CellDivisionSimulation.lesson(CellDivisionType.MEIOSIS, 6).stages
        assertTrue(stages[4].keyEvent.contains("Homologous chromosomes separate"))
        assertTrue(stages[7].description.contains("sister chromatids separate"))
        assertEquals(3, stages[5].chromosomeCountPerCell)
        assertEquals(6, stages[5].chromatidCountPerCell)
    }

    @Test fun crossingOverOccursInProphaseOneAndReplicationOccursOnlyOnce() {
        val stages = CellDivisionSimulation.lesson(CellDivisionType.MEIOSIS, 6).stages
        assertTrue(stages[2].keyEvent.contains("crossing over"))
        assertTrue(stages[5].keyEvent.contains("No DNA replication"))
    }

    @Test fun dnaContentDoublesOnceBeforeDivisionAndHalvesAcrossTheProducts() {
        val mitosis = CellDivisionSimulation.lesson(CellDivisionType.MITOSIS, 6).stages
        assertEquals("2C", mitosis[0].dnaContentPerCell)
        assertEquals("4C", mitosis[1].dnaContentPerCell)
        assertEquals("4C", mitosis[3].dnaContentPerCell)
        assertEquals("2C per daughter", mitosis[5].dnaContentPerCell)

        val meiosis = CellDivisionSimulation.lesson(CellDivisionType.MEIOSIS, 6).stages
        assertEquals("4C", meiosis[2].dnaContentPerCell)
        assertEquals("2C per haploid cell", meiosis[5].dnaContentPerCell)
        assertEquals("2C per haploid cell", meiosis[6].dnaContentPerCell)
        assertEquals("1C per haploid product", meiosis[8].dnaContentPerCell)
    }

    @Test fun anaphaseChromosomeCountsAreExplicitlyModelled() {
        val mitosis = CellDivisionSimulation.lesson(CellDivisionType.MITOSIS, 6)
        assertEquals(12, mitosis.stages[4].chromosomeCountPerCell)
        assertEquals(12, mitosis.stages[4].chromatidCountPerCell)
        assertEquals(6, mitosis.stages[5].chromosomeCountPerCell)

        val meiosis = CellDivisionSimulation.lesson(CellDivisionType.MEIOSIS, 6)
        assertEquals(6, meiosis.stages[4].chromosomeCountPerCell)
        assertEquals(12, meiosis.stages[4].chromatidCountPerCell)
        assertEquals(6, meiosis.stages[7].chromosomeCountPerCell)
        assertEquals(6, meiosis.stages[7].chromatidCountPerCell)
    }

    @Test fun rejectsOddOrNonPositiveDiploidNumbers() {
        assertThrows(IllegalArgumentException::class.java) {
            CellDivisionSimulation.lesson(CellDivisionType.MEIOSIS, 5)
        }
        assertThrows(IllegalArgumentException::class.java) {
            CellDivisionSimulation.lesson(CellDivisionType.MITOSIS, 0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            CellDivisionSimulation.lesson(CellDivisionType.MITOSIS, Int.MAX_VALUE - 1)
        }
    }
}
