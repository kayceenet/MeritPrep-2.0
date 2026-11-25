package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Test

class ScientificNotationTest {
    @Test
    fun formatsCommonLatexOperatorsAndGreekLetters() {
        assertEquals("Δ T → μ", ScientificNotation.format("\\Delta T \\rightarrow \\mu"))
    }

    @Test
    fun formatsPowersAndChemicalSubscripts() {
        assertEquals("H₂O + CO₂", ScientificNotation.format("H_2O + CO_2"))
        assertEquals("v²", ScientificNotation.format("v^2"))
    }

    @Test
    fun formatsFractionsRootsAndTextCommands() {
        assertEquals("(1)⁄(2)mv²", ScientificNotation.format("\\frac{1}{2}mv^2"))
        assertEquals("√(x + 1)", ScientificNotation.format("\\sqrt{x + 1}"))
        assertEquals("rate", ScientificNotation.format("\\text{rate}"))
    }

    @Test
    fun formatsScientificEquationUsedInChemistry() {
        assertEquals("pV = nRT", ScientificNotation.format("pV = nRT"))
        assertEquals("C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O", ScientificNotation.format("C_6H_{12}O_6 + 6O_2 \\rightarrow 6CO_2 + 6H_2O"))
    }
}
