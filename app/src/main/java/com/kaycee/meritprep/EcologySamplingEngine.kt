package com.kaycee.meritprep

data class QuadratEstimate(
    val totalIndividuals: Int,
    val quadratCount: Int,
    val sampledAreaSquareMetres: Double,
    val meanDensityPerSquareMetre: Double,
    val estimatedHabitatPopulation: Double
)

object EcologySamplingEngine {
    /**
     * Estimates abundance from equal-area quadrat samples.
     *
     * The samples must fit within the stated habitat area. Quadrat placement
     * must be representative; the calculation cannot correct biased sampling.
     */
    fun estimate(
        individualsPerQuadrat: List<Int>,
        quadratAreaSquareMetres: Double,
        habitatAreaSquareMetres: Double
    ): QuadratEstimate {
        require(individualsPerQuadrat.isNotEmpty()) { "At least one quadrat sample is required." }
        require(individualsPerQuadrat.all { it >= 0 }) { "Organism counts cannot be negative." }
        require(quadratAreaSquareMetres.isFinite() && quadratAreaSquareMetres > 0.0) {
            "Quadrat area must be a finite value greater than zero."
        }
        require(habitatAreaSquareMetres.isFinite() && habitatAreaSquareMetres > 0.0) {
            "Habitat area must be a finite value greater than zero."
        }

        val totalLong = individualsPerQuadrat.sumOf { it.toLong() }
        require(totalLong <= Int.MAX_VALUE) { "The total organism count is too large to represent safely." }
        val total = totalLong.toInt()
        val sampledArea = individualsPerQuadrat.size.toDouble() * quadratAreaSquareMetres
        require(sampledArea.isFinite()) { "The total sampled area is outside the supported range." }
        require(sampledArea <= habitatAreaSquareMetres) {
            "The combined quadrat area cannot exceed the habitat area."
        }

        val density = total.toDouble() / sampledArea
        val estimate = density * habitatAreaSquareMetres
        require(density.isFinite() && estimate.isFinite()) {
            "The calculated density or population estimate is outside the supported range."
        }

        return QuadratEstimate(
            totalIndividuals = total,
            quadratCount = individualsPerQuadrat.size,
            sampledAreaSquareMetres = sampledArea,
            meanDensityPerSquareMetre = density,
            estimatedHabitatPopulation = estimate
        )
    }
}
