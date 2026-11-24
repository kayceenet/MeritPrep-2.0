package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyTheoryBankTest {
    @Test
    fun theoryTasksHaveUniqueIdsAndValidMarkingGuides() {
        val tasks = BiologyTheoryBank.all
        assertEquals(15, tasks.size)
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
