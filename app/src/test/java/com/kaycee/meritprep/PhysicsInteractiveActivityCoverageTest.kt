package com.kaycee.meritprep

import org.junit.Assert.assertTrue
import org.junit.Test

class PhysicsInteractiveActivityCoverageTest {
    @Test
    fun everyPhysicsCourseTopicHasAnInteractiveExperience() {
        val establishedTopics = setOf(
            "phy-current-electricity", "phy-electrical-power", "phy-cells",
            "phy-capacitors", "phy-waves", "phy-sound-propagation",
            "phy-sound-characteristics", "phy-light-energy", "phy-motion",
            "phy-gravity", "phy-elasticity", "phy-thermal-expansion",
            "phy-equilibrium", "phy-work-energy", "phy-reflection",
            "phy-refraction", "phy-electrostatics", "phy-induction"
        )
        val covered = establishedTopics + physicsSupplementalActivityTopicIds
        val missing = PhysicsCourse.topics.map { it.id }.filterNot { it in covered }
        assertTrue("Physics topics without an interactive experience: $missing", missing.isEmpty())
    }
}
