package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class BiologyCbtModeTest {
    @Test fun jambAndWaecModesUseDistinctPracticeSessionConfigurations() {
        assertEquals(40, BiologyCbtMode.JAMB_UTME.questionCount)
        assertEquals(30 * 60, BiologyCbtMode.JAMB_UTME.durationSeconds)
        assertEquals("BIO", BiologyCbtMode.JAMB_UTME.sessionSubjectId)

        assertEquals(50, BiologyCbtMode.WAEC_OBJECTIVE.questionCount)
        assertEquals(60 * 60, BiologyCbtMode.WAEC_OBJECTIVE.durationSeconds)
        assertEquals("BIO_WAEC", BiologyCbtMode.WAEC_OBJECTIVE.sessionSubjectId)
        assertNotEquals(BiologyCbtMode.JAMB_UTME.sessionSubjectId, BiologyCbtMode.WAEC_OBJECTIVE.sessionSubjectId)
    }
}
