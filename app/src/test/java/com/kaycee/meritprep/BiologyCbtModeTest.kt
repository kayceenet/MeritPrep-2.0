package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CbtModeTest {
    @Test fun jambAndWaecModesUseDistinctPracticeSessionConfigurations() {
        assertEquals(40, CbtMode.JAMB_UTME.questionCount)
        assertEquals(30 * 60, CbtMode.JAMB_UTME.durationSeconds)
        assertEquals("BIO", CbtMode.JAMB_UTME.sessionSubjectId)

        assertEquals(50, CbtMode.WAEC_OBJECTIVE.questionCount)
        assertEquals(60 * 60, CbtMode.WAEC_OBJECTIVE.durationSeconds)
        assertEquals("BIO_WAEC", CbtMode.WAEC_OBJECTIVE.sessionSubjectId)
        assertNotEquals(CbtMode.JAMB_UTME.sessionSubjectId, CbtMode.WAEC_OBJECTIVE.sessionSubjectId)
    }
}
