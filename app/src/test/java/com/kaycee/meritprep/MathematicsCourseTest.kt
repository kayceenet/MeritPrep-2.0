package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MathematicsCourseTest {
    @Test fun mapsTwentyThreeJambMathematicsTopicsInSyllabusOrder() {
        assertEquals(23, MathematicsCourse.topics.size)
        assertEquals("math-number-bases", MathematicsCourse.topics.first().id)
        assertEquals("math-probability", MathematicsCourse.topics.last().id)
        assertEquals(23, MathematicsCourse.topics.map { it.id }.distinct().size)
        assertTrue(MathematicsCourse.topics.all { it.subjectCode == "MATH" })
    }

    @Test fun firstEighteenTopicsAreDevelopedAndRemainingFiveAreOutlines() {
        assertEquals(18, MathematicsCourse.topics.count { !it.isSyllabusOutline })
        assertEquals(5, MathematicsCourse.topics.count { it.isSyllabusOutline })
        MathematicsCourse.topics.filterNot { it.isSyllabusOutline }.forEach {
            assertTrue(it.objectives.size >= 4)
            assertTrue(it.sections.size >= 4)
            assertEquals(4, it.checkOptions.size)
            assertTrue(it.correctOption in it.checkOptions.indices)
        }
    }

    @Test fun allTopicIdsAndFormativeChecksAreValid() {
        MathematicsCourse.topics.forEach {
            assertFalse(it.id.isBlank())
            assertTrue(it.title.isNotBlank())
            assertTrue(it.checkQuestion.isNotBlank())
            assertTrue(it.explanation.isNotBlank())
            assertTrue(it.correctOption in it.checkOptions.indices)
        }
    }
}
