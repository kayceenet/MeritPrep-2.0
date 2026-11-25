package com.kaycee.meritprep

import kotlin.math.exp
import kotlin.math.abs

enum class EnzymeProfile(
    val displayName: String,
    val illustrativeOptimumPh: Double
) {
    SALIVARY_AMYLASE("Salivary amylase", 7.0),
    PEPSIN("Pepsin", 2.0),
    TRYPSIN("Trypsin", 8.0)
}

data class EnzymeConditions(
    val profile: EnzymeProfile,
    val temperatureCelsius: Double,
    val pH: Double
)

data class EnzymeActivityResult(
    val activityIndexPercent: Double,
    val temperatureResponsePercent: Double,
    val pHResponsePercent: Double,
    val limitingCondition: String,
    val explanation: String
)

/**
 * Illustrative teaching model only. Optima and response curves are approximations,
 * not measured kinetic constants or universal values for every preparation.
 */
object EnzymeActivitySimulation {
    private const val HUMAN_BODY_TEMPERATURE_OPTIMUM_C = 37.0
    private const val TEMPERATURE_DECLINE_WIDTH_C = 8.0
    private const val PH_RESPONSE_WIDTH = 1.6

    fun evaluate(conditions: EnzymeConditions): EnzymeActivityResult {
        require(conditions.temperatureCelsius.isFinite() && conditions.temperatureCelsius in 0.0..80.0)
        require(conditions.pH.isFinite() && conditions.pH in 0.0..14.0)

        val temperatureResponseRaw = if (conditions.temperatureCelsius <= HUMAN_BODY_TEMPERATURE_OPTIMUM_C) {
            conditions.temperatureCelsius / HUMAN_BODY_TEMPERATURE_OPTIMUM_C
        } else {
            val distanceFromOptimum = (conditions.temperatureCelsius - HUMAN_BODY_TEMPERATURE_OPTIMUM_C) / TEMPERATURE_DECLINE_WIDTH_C
            exp(-0.5 * distanceFromOptimum * distanceFromOptimum)
        }
        val temperatureResponse = temperatureResponseRaw.coerceIn(0.0, 1.0)

        val pHDifference = (conditions.pH - conditions.profile.illustrativeOptimumPh) / PH_RESPONSE_WIDTH
        val pHResponse = exp(-0.5 * pHDifference * pHDifference).coerceIn(0.0, 1.0)
        val activity = temperatureResponse * pHResponse * 100.0
        val limitingCondition = when {
            abs(temperatureResponse - pHResponse) < 0.000001 -> "temperature and pH"
            temperatureResponse < pHResponse -> "temperature"
            else -> "pH"
        }
        val explanation = when (limitingCondition) {
            "temperature and pH" -> "Temperature and pH have similar effects in this model. Change one variable at a time to separate their effects."
            "temperature" -> "Temperature is further from the model's illustrative optimum. Low temperature slows molecular activity; high temperature can disrupt enzyme structure and reduce activity."
            else -> "The pH is further from this enzyme profile's illustrative optimum. Changes in pH can alter charges and the shape of the active site, reducing activity."
        }
        return EnzymeActivityResult(
            activityIndexPercent = activity,
            temperatureResponsePercent = temperatureResponse * 100.0,
            pHResponsePercent = pHResponse * 100.0,
            limitingCondition = limitingCondition,
            explanation = explanation
        )
    }
}
