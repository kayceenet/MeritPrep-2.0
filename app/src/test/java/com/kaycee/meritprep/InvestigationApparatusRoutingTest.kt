package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class InvestigationApparatusRoutingTest {
    @Test
    fun routesBiologyChemistryAndPhysicsApparatus() {
        assertEquals(ApparatusDiagram.MICROSCOPE, apparatusFor("bio-cell"))
        assertEquals(ApparatusDiagram.QUADRAT, apparatusFor("bio-populations"))
        assertEquals(ApparatusDiagram.TITRATION, apparatusFor("chem-acids-bases"))
        assertEquals(ApparatusDiagram.ELECTROLYSIS, apparatusFor("chem-electrolysis"))
        assertEquals(ApparatusDiagram.SPRING, apparatusFor("phy-elasticity"))
        assertEquals(ApparatusDiagram.CIRCUIT, apparatusFor("phy-current-electricity"))
        assertEquals(ApparatusDiagram.WAVE_TANK, apparatusFor("phy-waves"))
    }

    @Test
    fun doesNotShowUnrelatedApparatus() {
        assertNull(apparatusFor("bio-heredity"))
        assertNull(apparatusFor("chem-organic"))
        assertNull(apparatusFor("phy-modern"))
    }
}
