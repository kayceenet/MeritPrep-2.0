package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyTheoryBankTest {
    @Test
    fun mitosisAndMeiosisTheoryTaskHasEightDistinctMarkingPoints() {
        val task = BiologyTheoryBank.all.single { it.id == "theory-growth-16" }
        assertEquals(8, task.marks)
        assertEquals(8, task.markingPoints.size)
        assertTrue(task.title.contains("mitosis", ignoreCase = true))
        assertTrue(task.title.contains("meiosis", ignoreCase = true))
        assertTrue(task.modelAnswer.contains("crossing over", ignoreCase = true))
        assertTrue(task.modelAnswer.contains("three chromosomes", ignoreCase = true))
    }

    @Test
    fun theoryTasksHaveUniqueIdsAndValidMarkingGuides() {
        val tasks = BiologyTheoryBank.all
        assertEquals(16, tasks.size)
        assertEquals(tasks.size, tasks.map { it.id }.toSet().size)
        tasks.forEach { task ->
            assertTrue(task.title.isNotBlank())
            assertTrue(task.instruction.isNotBlank())
            assertTrue(task.marks > 0)
            assertEquals(task.marks, task.markingPoints.size)
            assertTrue(task.modelAnswer.isNotBlank())
            assertFalse(task.topicId.isBlank())
            assertTrue(BiologyCurriculum.units.any { it.lessonId == task.topicId })
        }
    }
}
