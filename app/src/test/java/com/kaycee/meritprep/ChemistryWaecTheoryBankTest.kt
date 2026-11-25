package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryWaecTheoryBankTest {
    @Test fun newTheoryTasksCoverCorePhysicalAndInorganicChemistry() {
        val tasks = ChemistryWaecTheoryBank.tasks.associateBy { it.id }
        val required = listOf(
            "waec-chem-theory-atomic-07",
            "waec-chem-theory-gas-08",
            "waec-chem-theory-rates-09",
            "waec-chem-theory-energy-10",
            "waec-chem-theory-solubility-11",
            "waec-chem-theory-metals-12"
        )
        assertTrue(required.all { it in tasks })
        assertTrue(tasks.getValue("waec-chem-theory-gas-08").guidance.contains("kelvin", ignoreCase = true))
        assertTrue(tasks.getValue("waec-chem-theory-rates-09").markingPoints.any { it.contains("activation energy", ignoreCase = true) })
        assertTrue(tasks.getValue("waec-chem-theory-metals-12").markingPoints.any { it.contains("Fe₂O₃ + 3CO") })
    }

    @Test fun theoryTasksHaveUniqueIdsAndValidMarkingGuides() {
        val tasks = ChemistryWaecTheoryBank.tasks
        assertEquals(12, tasks.size)
        assertEquals(tasks.size, tasks.map { it.id }.toSet().size)
        tasks.forEach {
            assertTrue(it.topic.isNotBlank())
            assertTrue(it.prompt.isNotBlank())
            assertTrue(it.marks > 0)
            assertTrue(it.markingPoints.isNotEmpty())
            assertTrue(it.markingPoints.all(String::isNotBlank))
            assertTrue(it.guidance.isNotBlank())
        }
    }
}
