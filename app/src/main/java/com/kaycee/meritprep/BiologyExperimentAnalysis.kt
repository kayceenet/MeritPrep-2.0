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
    /** Describes the observed sequence without claiming that the independent variable caused it. */
    fun describeTrend(points: List<BiologyExperimentPoint>): String {
        require(points.size >= 2) { "At least two points are needed to describe a trend." }
        val values = points.sortedBy { it.independentValue }.map { it.meanResult }
        val tolerance = maxOf(values.maxOf { kotlin.math.abs(it) }, 1.0) * 0.01
        val spread = values.maxOrNull()!! - values.minOrNull()!!
        return when {
            spread <= tolerance -> "The measured result remains approximately constant across the tested range."
            values.zipWithNext().all { (a, b) -> b >= a - tolerance } ->
                "The measured result generally increases as the independent-variable value increases."
            values.zipWithNext().all { (a, b) -> b <= a + tolerance } ->
                "The measured result generally decreases as the independent-variable value increases."
            else -> "The measured result fluctuates; there is no single consistent direction across the tested range."
        }
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