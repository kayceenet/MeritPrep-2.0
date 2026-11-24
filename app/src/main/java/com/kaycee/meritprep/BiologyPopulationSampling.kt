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
 * Estimates population size from representative quadrat samples.
 * Assumes the sampled quadrats have equal area and represent the habitat.
 */
fun estimatePopulationFromQuadrats(
    counts: List<Int>,
    quadratAreaM2: Double,
    habitatAreaM2: Double
): PopulationEstimate? {
    if (counts.isEmpty() || counts.any { it < 0 }) return null
    if (!quadratAreaM2.isFinite() || !habitatAreaM2.isFinite()) return null
    if (quadratAreaM2 <= 0.0 || habitatAreaM2 <= 0.0) return null

    val totalLong = counts.sumOf { it.toLong() }
    if (totalLong > Int.MAX_VALUE) return null
    val total = totalLong.toInt()
    val sampledArea = counts.size * quadratAreaM2
    if (!sampledArea.isFinite() || sampledArea > habitatAreaM2) return null
    val density = total.toDouble() / sampledArea
    return PopulationEstimate(
        totalCount = total,
        quadratCount = counts.size,
        sampledAreaM2 = sampledArea,
        habitatAreaM2 = habitatAreaM2,
        meanPerQuadrat = total.toDouble() / counts.size,
        densityPerM2 = density,
        estimatedPopulation = density * habitatAreaM2
    )
}
