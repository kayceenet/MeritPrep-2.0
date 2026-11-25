package com.kaycee.meritprep

enum class DiffusionDirection { HIGH_TO_LOW, NO_NET_MOVEMENT }

data class DiffusionConditions(
    val leftConcentration: Double,
    val rightConcentration: Double,
    val membranePermeabilityPercent: Double
)

data class DiffusionResult(
    val direction: DiffusionDirection,
    val concentrationGradient: Double,
    val relativeRateIndexPercent: Double,
    val explanation: String
)

/**
 * Simplified teaching model: relative diffusion rate is proportional to the
 * concentration difference and membrane permeability. It is not a measured
 * kinetic model and does not represent particle-level random motion.
 */
object DiffusionSimulation {
    fun evaluate(conditions: DiffusionConditions): DiffusionResult {
        val left = conditions.leftConcentration
        val right = conditions.rightConcentration
        val permeability = conditions.membranePermeabilityPercent
        require(left.isFinite() && right.isFinite() && permeability.isFinite()) {
            "Concentrations and permeability must be finite."
        }
        require(left >= 0.0 && right >= 0.0) { "Concentrations cannot be negative." }
        require(permeability in 0.0..100.0) { "Permeability must be between 0 and 100 percent." }

        val gradient = kotlin.math.abs(left - right)
        val direction = if (gradient == 0.0) DiffusionDirection.NO_NET_MOVEMENT
            else DiffusionDirection.HIGH_TO_LOW
        val rate = ((gradient / 1.5) * (permeability / 100.0) * 100.0).coerceIn(0.0, 100.0)
        val explanation = when {
            gradient == 0.0 ->
                "The concentrations are equal, so particles still move randomly in both directions but there is no net diffusion."
            permeability == 0.0 ->
                "A concentration gradient exists, but the modelled membrane is impermeable to this particle. No diffusion occurs across it."
            left > right ->
                "The left side has the higher concentration. Particles diffuse overall from left to right, down the concentration gradient."
            else ->
                "The right side has the higher concentration. Particles diffuse overall from right to left, down the concentration gradient."
        }
        return DiffusionResult(direction, gradient, rate, explanation)
    }
}
