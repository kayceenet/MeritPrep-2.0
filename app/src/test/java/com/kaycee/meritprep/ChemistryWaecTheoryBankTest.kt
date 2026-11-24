package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryWaecTheoryBankTest {
    @Test fun theoryTasksHaveUniqueIdsAndValidMarkingGuides() {
        val tasks = ChemistryWaecTheoryBank.tasks
        assertEquals(6, tasks.size)
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
