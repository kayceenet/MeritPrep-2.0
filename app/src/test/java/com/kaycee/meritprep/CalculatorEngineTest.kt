package com.kaycee.meritprep
import com.kaycee.meritprep.data.CalculatorEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
class CalculatorEngineTest {
    @Test fun percentage() { assertEquals(25.0, CalculatorEngine.percentage(5.0, 20.0).value, 1e-9) }
    @Test fun mean() { assertEquals(4.0, CalculatorEngine.mean(listOf(2.0, 4.0, 6.0)).value, 1e-9) }
    @Test fun ohmsLaw() { assertEquals(2.0, CalculatorEngine.ohmsLaw(12.0, 6.0).value, 1e-9) }
    @Test fun speedAndDensity() {
        assertEquals(5.0, CalculatorEngine.speed(100.0, 20.0).value, 1e-9)
        assertEquals(2.0, CalculatorEngine.density(20.0, 10.0).value, 1e-9)
    }
    @Test fun basicChemistryCalculations() {
        assertEquals(0.5, CalculatorEngine.molarity(0.25, 0.5).value, 1e-9)
        assertEquals(100.0, CalculatorEngine.dilution(2.0, 25.0, 0.5).value, 1e-9)
        assertEquals(1.0, CalculatorEngine.molesFromMass(18.0, 18.0).value, 1e-9)
    }
    @Test fun gasLaws() {
        assertEquals(200.0, CalculatorEngine.boylesLaw(100.0, 2.0, 1.0).value, 1e-9)
        assertEquals(3.0, CalculatorEngine.charlesLaw(2.0, 300.0, 450.0).value, 1e-9)
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.charlesLaw(2.0, 0.0, 300.0) }
    }
    @Test fun heatEnergyAllowsNegativeTemperatureChange() {
        assertEquals(8400.0, CalculatorEngine.heatEnergy(2.0, 4200.0, 1.0).value, 1e-9)
        assertEquals(-8400.0, CalculatorEngine.heatEnergy(2.0, 4200.0, -1.0).value, 1e-9)
    }
    @Test fun phAndFaradaysLaw() {
        assertEquals(3.0, CalculatorEngine.pH(1e-3).value, 1e-9)
        assertEquals(1.1855, CalculatorEngine.electrolysisMass(2.0, 3600.0, 63.546, 2.0).value, 1e-3)
    }
    @Test fun invalidInputsAreRejected() {
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.percentage(2.0, 0.0) }
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.ohmsLaw(12.0, 0.0) }
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.dilution(1.0, 10.0, 2.0) }
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.pH(0.0) }
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.electrolysisMass(1.0, 10.0, 20.0, 1.5) }
    }
}
