package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class BiologySupplementalActivityCoverageTest {
    @Test
    fun everyRemainingBiologyCourseUnitHasASupplementalInteractive() {
        val required = setOf(
            "bio-classification", "bio-adaptation", "bio-interactions", "bio-plant-anatomy", "bio-respiration", "bio-excretion", "bio-support",
            "bio-reproduction", "bio-coordination", "bio-ecology-factors",
            "bio-habitats", "bio-biomes", "bio-soil", "bio-environment",
            "bio-variation", "bio-biotechnology", "bio-evolution-evidence"
        )
        assertTrue(biologySupplementalActivityTopicIds.containsAll(required))
    }

    @Test
    fun everyBiologyCurriculumLessonHasAnInteractiveOrVisualExperience() {
        val establishedInteractiveTopics = setOf(
            "bio-cell", "bio-nutrition", "bio-transport", "bio-growth",
            "bio-heredity", "bio-evolution-theory", "bio-populations"
        )
        val covered = establishedInteractiveTopics + biologySupplementalActivityTopicIds
        val missing = BiologyCurriculum.units.mapNotNull { it.lessonId }.filterNot { it in covered }
        assertTrue("Biology lesson topics without a visual or interactive experience: $missing", missing.isEmpty())
    }

    @Test
    fun everyBiologyCurriculumLessonHasAGuidedInvestigationProfile() {
        val missing = BiologyCurriculum.units.mapNotNull { it.lessonId }.filterNot { InvestigationProfiles.supports(it) }
        assertTrue("Biology lessons without a guided investigation profile: $missing", missing.isEmpty())
    }
}
