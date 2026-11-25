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
        assertTrue(stages[7].keyEvent.contains("sister chromatids separate"))
        assertEquals(3, stages[5].chromosomeCountPerCell)
        assertEquals(6, stages[5].chromatidCountPerCell)
    }

    @Test fun crossingOverOccursInProphaseOneAndReplicationOccursOnlyOnce() {
        val stages = CellDivisionSimulation.lesson(CellDivisionType.MEIOSIS, 6).stages
        assertTrue(stages[2].keyEvent.contains("crossing over"))
        assertTrue(stages[5].description.contains("No DNA replication"))
    }

    @Test fun rejectsOddOrNonPositiveDiploidNumbers() {
        assertThrows(IllegalArgumentException::class.java) {
            CellDivisionSimulation.lesson(CellDivisionType.MEIOSIS, 5)
        }
        assertThrows(IllegalArgumentException::class.java) {
            CellDivisionSimulation.lesson(CellDivisionType.MITOSIS, 0)
        }
    }
}
