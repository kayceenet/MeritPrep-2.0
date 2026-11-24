package com.kaycee.meritprep

data class PopulationEstimate(
    val totalCount: Int,
    val quadratCount: Int,
    val sampledAreaM2: Double,
    val habitatAreaM2: Double,
    val meanPerQuadrat: Double,
    val densityPerM2: Double,
    val estimatedPopulation: Double
)

/**
 * Nullable lesson-facing adapter for the shared ecology sampling engine.
 * Invalid learner input is represented as null so the screen can show guidance.
 */
fun estimatePopulationFromQuadrats(
    counts: List<Int>,
    quadratAreaM2: Double,
    habitatAreaM2: Double
): PopulationEstimate? {
    val estimate = runCatching {
        EcologySamplingEngine.estimate(
            individualsPerQuadrat = counts,
            quadratAreaSquareMetres = quadratAreaM2,
            habitatAreaSquareMetres = habitatAreaM2
        )
    }.getOrNull() ?: return null

    return PopulationEstimate(
        totalCount = estimate.totalIndividuals,
        quadratCount = estimate.quadratCount,
        sampledAreaM2 = estimate.sampledAreaSquareMetres,
        habitatAreaM2 = habitatAreaM2,
        meanPerQuadrat = estimate.totalIndividuals.toDouble() / estimate.quadratCount,
        densityPerM2 = estimate.meanDensityPerSquareMetre,
        estimatedPopulation = estimate.estimatedHabitatPopulation
    )
}
