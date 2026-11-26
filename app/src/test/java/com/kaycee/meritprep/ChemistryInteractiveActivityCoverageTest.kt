package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class ChemistryInteractiveActivityCoverageTest {
    @Test
    fun everyChemistryCourseTopicHasATopicAlignedInteractiveOrSupplementalActivity() {
        val covered = chemistryInteractiveActivityTopicIds + chemistrySupplementalActivityTopicIds
        val missing = ChemistryCourse.topics.map { it.id }.filterNot { it in covered }
        assertTrue("Chemistry topics without a topic-aligned interactive laboratory: $missing", missing.isEmpty())
    }

    @Test
    fun atomicStructureAndAtomicBondingAreKeptAsDistinctTopics() {
        assertTrue("chem-atomic" in chemistryInteractiveActivityTopicIds)
        assertTrue("chem-atomic-bonding" in chemistrySupplementalActivityTopicIds)
    }
}
