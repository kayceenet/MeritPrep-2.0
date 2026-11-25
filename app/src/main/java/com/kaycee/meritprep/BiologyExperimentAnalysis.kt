package com.kaycee.meritprep

/** Summarises repeated numerical observations for one experimental condition. */
data class BiologyReadingSummary(
    val count: Int,
    val mean: Double,
    val minimum: Double,
    val maximum: Double
) {
    val range: Double get() = maximum - minimum
}

object BiologyExperimentAnalysis {
    /** Readings are comma- or semicolon-separated. Invalid entries are never silently ignored. */
    fun summarizeReadings(raw: String): BiologyReadingSummary? {
        if (raw.isBlank()) return null
        val tokens = raw.split(',', ';').map { it.trim() }
        require(tokens.isNotEmpty() && tokens.all { it.isNotEmpty() }) {
            "Separate every reading with a comma or semicolon."
        }
        val readings = tokens.map { token ->
            token.toDoubleOrNull()?.also {
                require(it.isFinite()) { "Readings must be finite numbers." }
            } ?: throw IllegalArgumentException("Every reading must be a valid number.")
        }
        val mean = readings.sum() / readings.size
        require(mean.isFinite()) { "The calculated mean is outside the supported numeric range." }
        return BiologyReadingSummary(
            count = readings.size,
            mean = mean,
            minimum = readings.minOrNull()!!,
            maximum = readings.maxOrNull()!!
        )
    }
}