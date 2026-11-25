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

data class BiologyExperimentPoint(val independentValue: Double, val meanResult: Double, val readingCount: Int)

object BiologyExperimentAnalysis {
    /** Converts completed result rows into ordered points for a continuous-variable graph. */
    fun graphPoints(rows: List<Pair<String, String>>): List<BiologyExperimentPoint> {
        val completed = rows.filter { it.first.isNotBlank() || it.second.isNotBlank() }
        require(completed.size >= 2) { "Enter at least two complete result rows to draw a graph." }
        val points = completed.map { (rawX, rawY) ->
            val x = rawX.trim().toDoubleOrNull()
                ?: throw IllegalArgumentException("Independent-variable values must be numbers to draw this graph.")
            require(x.isFinite()) { "Independent-variable values must be finite numbers." }
            val summary = summarizeReadings(rawY)
                ?: throw IllegalArgumentException("Complete every result row before drawing the graph.")
            BiologyExperimentPoint(x, summary.mean, summary.count)
        }.sortedBy { it.independentValue }
        require(points.map { it.independentValue }.distinct().size == points.size) {
            "Each graph row must have a different independent-variable value."
        }
        return points
    }
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