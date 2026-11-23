package com.kaycee.meritprep

data class QuadratEstimate(
    val totalIndividuals: Int,
    val quadratCount: Int,
    val sampledAreaSquareMetres: Double,
    val meanDensityPerSquareMetre: Double,
    val estimatedHabitatPopulation: Double
)

object EcologySamplingEngine {
    fun estimate(
        individualsPerQuadrat: List<Int>,
        quadratAreaSquareMetres: Double,
        habitatAreaSquareMetres: Double
    ): QuadratEstimate {
        require(individualsPerQuadrat.isNotEmpty()) { "At least one quadrat sample is required." }
        require(individualsPerQuadrat.all { it >= 0 }) { "Organism counts cannot be negative." }
        require(quadratAreaSquareMetres > 0.0) { "Quadrat area must be greater than zero." }
        require(habitatAreaSquareMetres > 0.0) { "Habitat area must be greater than zero." }

        val total = individualsPerQuadrat.sum()
        val sampledArea = individualsPerQuadrat.size * quadratAreaSquareMetres
        val density = total / sampledArea
        return QuadratEstimate(
            totalIndividuals = total,
            quadratCount = individualsPerQuadrat.size,
            sampledAreaSquareMetres = sampledArea,
            meanDensityPerSquareMetre = density,
            estimatedHabitatPopulation = density * habitatAreaSquareMetres
        )
    }
}
