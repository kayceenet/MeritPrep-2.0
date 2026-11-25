package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WaecScienceActivityRoutingTest {
    @Test
    fun biologyNigeriaSectionCRoutesAreDeclared() {
        val expected = setOf(
            "bio-cell", "bio-classification", "bio-nutrition", "bio-plant-anatomy",
            "bio-reproduction", "bio-interactions", "bio-ecology-factors", "bio-habitats",
            "bio-populations", "bio-environment", "bio-variation", "bio-adaptation",
            "bio-soil", "bio-excretion", "bio-coordination"
        )
        assertEquals(expected, WaecBiologyActivityRoutes.supportedTopicIds)
    }

    @Test
    fun physicsHarmonisedRoutesAreDeclared() {
        val expected = setOf(
            "phy-measurements", "phy-motion", "phy-gravity", "phy-elasticity",
            "phy-heat-transfer", "phy-refraction", "phy-light-energy", "phy-magnetism",
            "phy-electronics", "phy-kinetic-theory", "phy-modern"
        )
        assertEquals(expected, WaecPhysicsActivityRoutes.supportedTopicIds)
    }

    @Test
    fun chemistryWaecPracticalAndContentRoutesAreDeclared() {
        val expected = setOf("chem-organic", "chem-separation", "chem-acids-bases", "chem-atomic-bonding")
        assertEquals(expected, WaecChemistryActivityRoutes.supportedTopicIds)
    }

    @Test
    fun unknownTopicsAreNotClaimedAsWaecExtensions() {
        assertTrue("not-a-topic" !in WaecBiologyActivityRoutes.supportedTopicIds)
        assertTrue("not-a-topic" !in WaecPhysicsActivityRoutes.supportedTopicIds)
        assertTrue("not-a-topic" !in WaecChemistryActivityRoutes.supportedTopicIds)
    }
}
