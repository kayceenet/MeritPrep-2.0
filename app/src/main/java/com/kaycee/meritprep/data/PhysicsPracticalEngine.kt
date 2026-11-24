package com.kaycee.meritprep.data

import kotlin.math.atan2
import kotlin.math.sqrt

data class UniformMotionResult(val finalVelocityMps: Double, val displacementM: Double)
data class VectorResult(val magnitude: Double, val angleDegreesFromPositiveX: Double)
data class MachineResult(val mechanicalAdvantage: Double, val efficiencyPercent: Double)

object PhysicsPracticalEngine {
    fun uniformMotion(initialVelocityMps: Double, accelerationMps2: Double, timeSeconds: Double): UniformMotionResult {
        require(initialVelocityMps.isFinite()) { "Initial velocity must be finite." }
        require(accelerationMps2.isFinite()) { "Acceleration must be finite." }
        require(timeSeconds.isFinite() && timeSeconds >= 0.0) { "Time must be finite and cannot be negative." }
        val velocity = initialVelocityMps + accelerationMps2 * timeSeconds
        val displacement = initialVelocityMps * timeSeconds + 0.5 * accelerationMps2 * timeSeconds * timeSeconds
        require(velocity.isFinite() && displacement.isFinite()) { "Calculated motion values are outside the supported range." }
        return UniformMotionResult(velocity, displacement)
    }

    fun perpendicularVector(xComponent: Double, yComponent: Double): VectorResult {
        require(xComponent.isFinite() && yComponent.isFinite()) { "Vector components must be finite." }
        val magnitude = sqrt(xComponent * xComponent + yComponent * yComponent)
        val angle = Math.toDegrees(atan2(yComponent, xComponent))
        require(magnitude.isFinite() && angle.isFinite()) { "Calculated vector is outside the supported range." }
        return VectorResult(magnitude, angle)
    }

    fun moment(forceNewtons: Double, perpendicularArmMetres: Double): Double {
        require(forceNewtons.isFinite() && forceNewtons >= 0.0) { "Force must be finite and cannot be negative." }
        require(perpendicularArmMetres.isFinite() && perpendicularArmMetres >= 0.0) { "Moment arm must be finite and cannot be negative." }
        val result = forceNewtons * perpendicularArmMetres
        require(result.isFinite()) { "Calculated moment is outside the supported range." }
        return result
    }

    fun machinePerformance(loadNewtons: Double, effortNewtons: Double, velocityRatio: Double): MachineResult {
        require(loadNewtons.isFinite() && loadNewtons >= 0.0) { "Load must be finite and cannot be negative." }
        require(effortNewtons.isFinite() && effortNewtons > 0.0) { "Effort must be finite and greater than zero." }
        require(velocityRatio.isFinite() && velocityRatio > 0.0) { "Velocity ratio must be finite and greater than zero." }
        val ma = loadNewtons / effortNewtons
        val efficiency = ma / velocityRatio * 100.0
        require(ma.isFinite() && efficiency.isFinite()) { "Calculated machine values are outside the supported range." }
        return MachineResult(ma, efficiency)
    }
}
