package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsGuidedInvestigationCoverageTest {
    @Test
    fun everyPhysicsCourseTopicHasAGuidedInvestigationProfile() {
        val missing = PhysicsCourse.topics.map { it.id }.filterNot { InvestigationProfiles.supports(it) }
        assertTrue("Physics topics without a guided investigation profile: $missing", missing.isEmpty())
    }
}
