package com.kaycee.meritprep.data

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MasteryPolicyTest {
    @Test fun masteryRequiresRepeatedAccurateWorkAcrossDistinctQuestionsAndDays() {
        assertTrue(MasteryPolicy.isDemonstrated(MasteryEvidence(10, 8, 8, 2)))
        assertFalse(MasteryPolicy.isDemonstrated(MasteryEvidence(9, 9, 9, 2)))
        assertFalse(MasteryPolicy.isDemonstrated(MasteryEvidence(10, 7, 10, 2)))
        assertFalse(MasteryPolicy.isDemonstrated(MasteryEvidence(10, 8, 10, 1)))
        assertFalse(MasteryPolicy.isDemonstrated(MasteryEvidence(10, 8, 7, 2)))
    }
}
