package com.kaycee.meritprep

data class NaturalSelectionConditions(
    val initialLightTraitCount: Int,
    val initialDarkTraitCount: Int,
    val lightTraitSurvivalPercent: Double,
    val darkTraitSurvivalPercent: Double,
    val generations: Int
)

data class SelectionGeneration(
    val generation: Int,
    val lightTraitFrequencyPercent: Double,
    val darkTraitFrequencyPercent: Double
)

data class NaturalSelectionResult(
    val generations: List<SelectionGeneration>,
    val moreFavouredTrait: String,
    val explanation: String
)

/**
 * Deterministic teaching model of selection on a heritable two-phenotype trait.
 *
 * Each generation is normalised to frequencies after weighting by the supplied
 * survival/reproductive-success values. Population size is not modelled.
 * There is no mutation, migration, genetic drift, dominance or age structure.
 * This is not a prediction for a real population.
 */
object NaturalSelectionSimulation {
    fun evaluate(conditions: NaturalSelectionConditions): NaturalSelectionResult {
        require(conditions.initialLightTraitCount >= 0)
        require(conditions.initialDarkTraitCount >= 0)
        require(conditions.initialLightTraitCount.toLong() + conditions.initialDarkTraitCount.toLong() > 0L)
        require(conditions.lightTraitSurvivalPercent.isFinite() && conditions.lightTraitSurvivalPercent in 0.0..100.0)
        require(conditions.darkTraitSurvivalPercent.isFinite() && conditions.darkTraitSurvivalPercent in 0.0..100.0)
        require(conditions.lightTraitSurvivalPercent > 0.0 || conditions.darkTraitSurvivalPercent > 0.0)
        require(conditions.generations in 1..20)

        val total = conditions.initialLightTraitCount.toDouble() + conditions.initialDarkTraitCount.toDouble()
        var lightFrequency = conditions.initialLightTraitCount / total
        var darkFrequency = conditions.initialDarkTraitCount / total
        val lightFitness = conditions.lightTraitSurvivalPercent / 100.0
        val darkFitness = conditions.darkTraitSurvivalPercent / 100.0

        val rows = mutableListOf(
            SelectionGeneration(0, lightFrequency * 100.0, darkFrequency * 100.0)
        )
        repeat(conditions.generations) { index ->
            val lightContribution = lightFrequency * lightFitness
            val darkContribution = darkFrequency * darkFitness
            val totalContribution = lightContribution + darkContribution
            require(totalContribution > 0.0 && totalContribution.isFinite()) {
                "The model has no surviving contribution from the starting population."
            }
            lightFrequency = lightContribution / totalContribution
            darkFrequency = darkContribution / totalContribution
            rows += SelectionGeneration(index + 1, lightFrequency * 100.0, darkFrequency * 100.0)
        }

        val difference = conditions.darkTraitSurvivalPercent - conditions.lightTraitSurvivalPercent
        val favoured = when {
            kotlin.math.abs(difference) < 0.000001 -> "neither trait"
            difference > 0.0 -> "dark trait"
            else -> "light trait"
        }
        val explanation = when (favoured) {
            "neither trait" -> "Both traits have the same modelled survival value, so their frequencies remain unchanged. Equal survival does not create a selection advantage in this model."
            "dark trait" -> "The dark trait has the higher modelled survival value. If the trait is heritable, its frequency rises across generations in this simplified population. Individual organisms do not change colour because they need to."
            else -> "The light trait has the higher modelled survival value. If the trait is heritable, its frequency rises across generations in this simplified population. Individual organisms do not change colour because they need to."
        }
        return NaturalSelectionResult(rows, favoured, explanation)
    }
}
