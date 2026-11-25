package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class BiologySupplementalActivityCoverageTest {
    @Test
    fun everyRemainingBiologyCourseUnitHasASupplementalInteractive() {
        val required = setOf(
            "bio-plant-anatomy", "bio-respiration", "bio-excretion", "bio-support",
            "bio-reproduction", "bio-coordination", "bio-ecology-factors",
            "bio-habitats", "bio-biomes", "bio-soil", "bio-environment",
            "bio-variation", "bio-biotechnology", "bio-evolution-evidence"
        )
        assertTrue(biologySupplementalActivityTopicIds.containsAll(required))
    }
}
