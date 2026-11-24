package com.kaycee.meritprep.data

data class WavePropertiesResult(
    val speedMetresPerSecond: Double,
    val periodSeconds: Double
)

object PhysicsWaveOpticsEngine {
    fun waveProperties(frequencyHz: Double, wavelengthM: Double): WavePropertiesResult {
        require(frequencyHz.isFinite() && frequencyHz > 0.0) { "Frequency must be finite and greater than zero." }
        require(wavelengthM.isFinite() && wavelengthM > 0.0) { "Wavelength must be finite and greater than zero." }
        val speed = frequencyHz * wavelengthM
        val period = 1.0 / frequencyHz
        require(speed.isFinite() && period.isFinite()) { "Calculated wave properties are outside the supported range." }
        return WavePropertiesResult(speed, period)
    }

    /** Distance to the reflecting surface from a measured round-trip echo time. */
    fun echoDistance(soundSpeedMps: Double, roundTripTimeSeconds: Double): Double {
        require(soundSpeedMps.isFinite() && soundSpeedMps > 0.0) { "Sound speed must be finite and greater than zero." }
        require(roundTripTimeSeconds.isFinite() && roundTripTimeSeconds >= 0.0) {
            "Round-trip time must be finite and cannot be negative."
        }
        val distance = soundSpeedMps * roundTripTimeSeconds / 2.0
        require(distance.isFinite()) { "Calculated echo distance is outside the supported range." }
        return distance
    }

    /** For an ideal plane mirror, angles are measured from the normal. */
    fun reflectedAngleDegrees(incidentAngleDegrees: Double): Double {
        require(incidentAngleDegrees.isFinite() && incidentAngleDegrees in 0.0..90.0) {
            "Angle of incidence must be between 0 and 90 degrees."
        }
        return incidentAngleDegrees
    }
}
