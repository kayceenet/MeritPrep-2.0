package com.kaycee.meritprep

import com.kaycee.meritprep.data.PhysicsPracticalExtension
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PhysicsPracticalExtensionTest {
    @Test fun springModelCalculatesForceAndStoredEnergy() {
        val result = PhysicsPracticalExtension.spring(200.0, 0.05)
        assertEquals(10.0, result.restoringForceMagnitudeN, 1e-9)
        assertEquals(0.25, result.elasticPotentialEnergyJ, 1e-9)
    }

    @Test fun pressureUsesForcePerUnitArea() {
        assertEquals(5000.0, PhysicsPracticalExtension.pressure(100.0, 0.02), 1e-9)
    }

    @Test fun fluidModelCalculatesDensityGaugePressureAndUpthrust() {
        val result = PhysicsPracticalExtension.fluid(
            massKg = 2.0,
            volumeM3 = 0.001,
            fluidDensityKgPerM3 = 1000.0,
            displacedVolumeM3 = 0.0005,
            depthM = 2.0
        )
        assertEquals(2000.0, result.densityKgPerM3, 1e-9)
        assertEquals(19620.0, result.gaugePressurePa, 1e-9)
        assertEquals(4.905, result.upthrustN, 1e-9)
    }

    @Test fun heatAndLatentHeatModelsUseCorrectRelationships() {
        assertEquals(21000.0, PhysicsPracticalExtension.heat(0.5, 4200.0, 10.0), 1e-9)
        assertEquals(66800.0, PhysicsPracticalExtension.latentHeat(0.2, 334000.0), 1e-9)
        assertEquals(-4200.0, PhysicsPracticalExtension.heat(1.0, 4200.0, -1.0), 1e-9)
    }

    @Test fun linearExpansionReturnsChangeAndFinalLength() {
        val result = PhysicsPracticalExtension.linearExpansion(2.0, 0.000012, 50.0)
        assertEquals(0.0012, result.changeInLengthM, 1e-12)
        assertEquals(2.0012, result.finalLengthM, 1e-12)
    }

    @Test fun gasLawModelsUseAbsoluteTemperatureAndConstantConditions() {
        val boyle = PhysicsPracticalExtension.boylesLawFinalPressure(100000.0, 0.004, 0.002)
        assertEquals(200000.0, boyle.finalPressurePa!!, 1e-9)
        val charles = PhysicsPracticalExtension.charlesLawFinalVolume(0.002, 300.0, 450.0)
        assertEquals(0.003, charles.finalVolumeM3!!, 1e-12)
    }

    @Test fun rejectsInvalidPhysicalInputs() {
        assertThrows(IllegalArgumentException::class.java) { PhysicsPracticalExtension.spring(0.0, 0.1) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsPracticalExtension.spring(100.0, -0.1) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsPracticalExtension.pressure(10.0, 0.0) }
        assertThrows(IllegalArgumentException::class.java) {
            PhysicsPracticalExtension.fluid(1.0, 0.0, 1000.0, 0.001, 1.0)
        }
        assertThrows(IllegalArgumentException::class.java) { PhysicsPracticalExtension.heat(1.0, 0.0, 10.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsPracticalExtension.latentHeat(1.0, -1.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsPracticalExtension.linearExpansion(1.0, 1e-5, -200000.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsPracticalExtension.boylesLawFinalPressure(1.0, 1.0, 0.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsPracticalExtension.charlesLawFinalVolume(1.0, 0.0, 300.0) }
    }
}
