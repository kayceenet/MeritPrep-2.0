package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryGuidedInvestigationCoverageTest {
    @Test
    fun everyChemistryCourseTopicHasAGuidedInvestigationProfile() {
        val missing = ChemistryCourse.topics.map { it.id }.filterNot { InvestigationProfiles.supports(it) }
        assertTrue("Chemistry topics without a guided investigation profile: $missing", missing.isEmpty())
    }
}
