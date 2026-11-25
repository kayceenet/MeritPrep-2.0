package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryInteractiveActivityCoverageTest {
    @Test
    fun everyChemistryCourseTopicHasAnInteractiveExperience() {
        val establishedTopics = setOf(
            "chem-acids-bases", "chem-redox", "chem-combination",
            "chem-separation", "chem-metals", "chem-nonmetals",
            "chem-rates", "chem-energy", "chem-electrolysis",
            "chem-kinetic-gas", "chem-equilibrium"
        )
        val covered = establishedTopics + chemistrySupplementalActivityTopicIds
        val missing = ChemistryCourse.topics.map { it.id }.filterNot { it in covered }
        assertTrue("Chemistry topics without an interactive experience: $missing", missing.isEmpty())
    }
}
