package com.kaycee.meritprep

data class PhotosynthesisConditions(
    val lightIntensity: Double,
    val carbonDioxideIndex: Double,
    val temperatureCelsius: Double
)

data class PhotosynthesisResult(
    val relativeRatePercent: Double,
    val lightResponsePercent: Double,
    val carbonDioxideResponsePercent: Double,
    val temperatureResponsePercent: Double,
    val limitingFactor: String,
    val explanation: String
)

/**
 * A deliberately simplified teaching model for comparing limiting factors.
 * The percentages are illustrative indices, not measured rates for a real plant.
 */
object PhotosynthesisSimulation {
    fun evaluate(conditions: PhotosynthesisConditions): PhotosynthesisResult {
        require(conditions.lightIntensity.isFinite() && conditions.lightIntensity in 0.0..100.0)
        require(conditions.carbonDioxideIndex.isFinite() && conditions.carbonDioxideIndex in 0.0..100.0)
        require(conditions.temperatureCelsius.isFinite() && conditions.temperatureCelsius in 0.0..45.0)

        val light = saturatingResponse(conditions.lightIntensity, halfSaturation = 20.0, maximumInput = 100.0)
        val carbonDioxide = saturatingResponse(conditions.carbonDioxideIndex, halfSaturation = 15.0, maximumInput = 100.0)
        val temperature = temperatureResponse(conditions.temperatureCelsius)
        val minimum = minOf(light, carbonDioxide, temperature)
        val limiting = buildList {
            if (kotlin.math.abs(light - minimum) < 0.000001) add("light intensity")
            if (kotlin.math.abs(carbonDioxide - minimum) < 0.000001) add("carbon dioxide availability")
            if (kotlin.math.abs(temperature - minimum) < 0.000001) add("temperature")
        }
        val factorText = limiting.joinToString(" and ")
        val explanation = when {
            limiting.size > 1 -> "In this simplified model, $factorText share the lowest response index. Increasing only one factor may not raise the overall rate while another factor remains equally limiting."
            limiting.single() == "temperature" -> "Temperature is the lowest response index. Enzyme-controlled reactions are affected by temperature; very low or excessively high temperatures can reduce photosynthetic activity. The model's optimum is illustrative, not universal."
            limiting.single() == "light intensity" -> "Light intensity is the lowest response index. Increasing light may raise the modelled rate until another factor becomes limiting or the light response approaches saturation."
            else -> "Carbon dioxide availability is the lowest response index. Increasing carbon dioxide may raise the modelled rate until another factor becomes limiting or the response approaches saturation."
        }
        return PhotosynthesisResult(
            relativeRatePercent = minimum * 100.0,
            lightResponsePercent = light * 100.0,
            carbonDioxideResponsePercent = carbonDioxide * 100.0,
            temperatureResponsePercent = temperature * 100.0,
            limitingFactor = factorText,
            explanation = explanation
        )
    }

    private fun saturatingResponse(value: Double, halfSaturation: Double, maximumInput: Double): Double {
        val raw = value / (value + halfSaturation)
        val maximum = maximumInput / (maximumInput + halfSaturation)
        return (raw / maximum).coerceIn(0.0, 1.0)
    }

    private fun temperatureResponse(temperature: Double): Double = when {
        temperature <= 30.0 -> temperature / 30.0
        else -> ((45.0 - temperature) / 15.0).coerceIn(0.0, 1.0)
    }
}
