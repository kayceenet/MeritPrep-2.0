package com.kaycee.meritprep.data

data class RedoxTitrationResult(
    val meanTitreCm3: Double,
    val analyteConcentrationMolPerDm3: Double,
    val molesPermanganate: Double,
    val molesIronIi: Double,
    val calculation: String
)

/**
 * Acidified permanganate titration of iron(II):
 * MnO₄⁻ + 5Fe²⁺ + 8H⁺ → Mn²⁺ + 5Fe³⁺ + 4H₂O
 *
 * This engine assumes the stated reaction and complete reaction at equivalence.
 */
object ChemistryRedoxTitrationEngine {
    fun calculateIronIiConcentration(
        permanganateConcentrationMolPerDm3: Double,
        initialReadingsCm3: List<Double>,
        finalReadingsCm3: List<Double>,
        ironIiAliquotCm3: Double,
        concordanceToleranceCm3: Double = 0.20
    ): RedoxTitrationResult {
        require(permanganateConcentrationMolPerDm3.isFinite() && permanganateConcentrationMolPerDm3 > 0.0) {
            "Permanganate concentration must be greater than zero."
        }
        require(ironIiAliquotCm3.isFinite() && ironIiAliquotCm3 > 0.0) {
            "Iron(II) aliquot volume must be greater than zero."
        }
        val meanTitre = ChemistryPracticalEngine.meanConcordantTitre(
            initialReadingsCm3, finalReadingsCm3, concordanceToleranceCm3
        )
        val molesMnO4 = permanganateConcentrationMolPerDm3 * (meanTitre / 1000.0)
        val molesFe2 = 5.0 * molesMnO4
        val concentrationFe2 = molesFe2 / (ironIiAliquotCm3 / 1000.0)
        require(concentrationFe2.isFinite()) { "Calculated concentration is outside the supported range." }
        return RedoxTitrationResult(
            meanTitreCm3 = meanTitre,
            analyteConcentrationMolPerDm3 = concentrationFe2,
            molesPermanganate = molesMnO4,
            molesIronIi = molesFe2,
            calculation = "n(MnO₄⁻) = C × V; n(Fe²⁺) = 5 × n(MnO₄⁻); C(Fe²⁺) = n ÷ V(aliquot)"
        )
    }
}
