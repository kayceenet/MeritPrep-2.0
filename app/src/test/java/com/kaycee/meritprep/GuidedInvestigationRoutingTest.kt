package com.kaycee.meritprep

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GuidedInvestigationRoutingTest {
    @Test
    fun everyDeclaredInvestigationRouteResolves() {
        val routes = listOf(
            "bio-cell", "bio-living", "bio-classification", "bio-adaptation",
            "bio-plant-anatomy", "bio-nutrition", "bio-transport", "bio-respiration",
            "bio-excretion", "bio-support", "bio-reproduction", "bio-growth",
            "bio-coordination", "bio-ecology-factors", "bio-habitats", "bio-biomes",
            "bio-environment", "bio-interactions", "bio-populations", "bio-soil",
            "bio-variation", "bio-biotechnology", "bio-evolution-evidence",
            "chem-separation", "chem-rates", "chem-solubility", "chem-electrolysis",
            "chem-acids-bases", "chem-redox", "chem-combination", "chem-energy", "chem-kinetic-gas",
            "chem-nuclear", "chem-industry", "chem-equilibrium", "chem-organic",
            "chem-pollution", "phy-motion", "phy-elasticity", "phy-pressure",
            "phy-liquids-rest", "phy-gas-laws", "phy-kinetic-theory", "phy-waves",
            "phy-sound-propagation", "phy-sound-characteristics", "phy-current-electricity",
            "phy-electrical-power", "phy-cells", "phy-friction", "phy-machines",
            "phy-thermal-expansion", "phy-heat-transfer", "phy-conduction",
            "phy-magnetic-force", "phy-ac-circuits", "phy-modern", "phy-gravity",
            "phy-equilibrium", "phy-work-energy", "phy-temperature", "phy-heat-quantity",
            "phy-change-state", "phy-vapours", "phy-light-energy", "phy-optical-instruments",
            "phy-dispersion", "phy-electrostatics", "phy-magnetism", "phy-electronics"
        )
        routes.forEach { id -> assertTrue("Missing guided investigation route: " + id, InvestigationProfiles.supports(id)) }
    }

    @Test
    fun unknownAndNonInvestigationTopicsDoNotCreateAnActivity() {
        assertFalse(InvestigationProfiles.supports("not-a-real-topic"))
        assertFalse(InvestigationProfiles.supports("bio-heredity"))
    }
}
