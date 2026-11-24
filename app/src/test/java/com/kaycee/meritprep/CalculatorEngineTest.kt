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
    @Test fun chemistryCalculations() {
        assertEquals(0.5, CalculatorEngine.molarity(0.25, 0.5).value, 1e-9)
        assertEquals(100.0, CalculatorEngine.dilution(2.0, 25.0, 0.5).value, 1e-9)
    }
    @Test fun invalidInputsAreRejected() {
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.percentage(2.0, 0.0) }
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.ohmsLaw(12.0, 0.0) }
        assertThrows(IllegalArgumentException::class.java) { CalculatorEngine.dilution(1.0, 10.0, 2.0) }
    }
}
