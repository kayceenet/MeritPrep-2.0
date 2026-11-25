package com.kaycee.meritprep

import org.junit.Assert.assertEquals
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
    @Test
    fun representativeInvestigationModelsMatchHandCalculatedValues() {
        val acid = InvestigationProfiles.find("chem-acids-bases")!!
        assertEquals(0.100, acid.response(25f).toDouble(), 0.0001)

        val redox = InvestigationProfiles.find("chem-redox")!!
        assertEquals(0.080, redox.response(20f).toDouble(), 0.0001)

        val calorimetry = InvestigationProfiles.find("chem-energy")!!
        assertEquals(4180.0, calorimetry.response(10f).toDouble(), 0.1)

        val decay = InvestigationProfiles.find("chem-nuclear")!!
        assertEquals(50.0, decay.response(1f).toDouble(), 0.01)

        val pendulum = InvestigationProfiles.find("phy-gravity")!!
        assertEquals(2.006, pendulum.response(1f).toDouble(), 0.01)

        val lens = InvestigationProfiles.find("phy-optical-instruments")!!
        assertEquals(33.333, lens.response(50f).toDouble(), 0.01)
    }

    @Test
    fun modelsWithPhysicalDomainsUseValidSliderRanges() {
        val gas = InvestigationProfiles.find("phy-gas-laws")!!
        assertEquals(100f, gas.minX)
        assertEquals(800f, gas.maxX)
        assertTrue(gas.initialX in gas.minX..gas.maxX)

        val lens = InvestigationProfiles.find("phy-optical-instruments")!!
        assertTrue(lens.minX > 20f)
        assertTrue(lens.initialX > 20f)

        val dispersion = InvestigationProfiles.find("phy-dispersion")!!
        assertEquals(400f, dispersion.minX)
        assertEquals(700f, dispersion.maxX)
    }

}
