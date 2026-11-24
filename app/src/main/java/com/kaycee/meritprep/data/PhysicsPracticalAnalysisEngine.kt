package com.kaycee.meritprep.data

data class PracticalGraphPoint(val x: Double, val y: Double)

data class LinearFitResult(
    val gradient: Double,
    val intercept: Double,
    val coefficientOfDetermination: Double
)

/** Analysis tools for WAEC-style practical tables and straight-line graphs. */
object PhysicsPracticalAnalysisEngine {
    fun linearFit(points: List<PracticalGraphPoint>): LinearFitResult {
        require(points.size >= 2) { "At least two plotted points are required." }
        require(points.all { it.x.isFinite() && it.y.isFinite() }) { "Every graph coordinate must be finite." }
        val meanX = points.map { it.x }.average()
        val meanY = points.map { it.y }.average()
        val sumXX = points.sumOf { (it.x - meanX) * (it.x - meanX) }
        require(sumXX.isFinite() && sumXX > 0.0) { "The x-values must not all be identical." }
        val sumXY = points.sumOf { (it.x - meanX) * (it.y - meanY) }
        val gradient = sumXY / sumXX
        val intercept = meanY - gradient * meanX
        val totalVariation = points.sumOf { (it.y - meanY) * (it.y - meanY) }
        val residualVariation = points.sumOf {
            val residual = it.y - (gradient * it.x + intercept)
            residual * residual
        }
        val rSquared = if (totalVariation == 0.0) 1.0 else 1.0 - residualVariation / totalVariation
        require(gradient.isFinite() && intercept.isFinite() && rSquared.isFinite()) {
            "The fitted line is outside the supported numerical range."
        }
        return LinearFitResult(gradient, intercept, rSquared.coerceIn(0.0, 1.0))
    }

    /** Simple-pendulum relation T² = (4π²/g)L for small oscillations. */
    fun gravityFromPendulumGradient(secondsSquaredPerMetre: Double): Double {
        require(secondsSquaredPerMetre.isFinite() && secondsSquaredPerMetre > 0.0) {
            "The T²-versus-length gradient must be finite and greater than zero."
        }
        val gravity = 4.0 * Math.PI * Math.PI / secondsSquaredPerMetre
        require(gravity.isFinite() && gravity > 0.0) { "Calculated gravitational field strength is invalid." }
        return gravity
    }
}
