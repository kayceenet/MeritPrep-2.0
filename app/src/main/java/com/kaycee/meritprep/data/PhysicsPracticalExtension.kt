package com.kaycee.meritprep.data

data class SpringPracticalResult(
    val restoringForceMagnitudeN: Double,
    val elasticPotentialEnergyJ: Double
)

data class FluidPracticalResult(
    val densityKgPerM3: Double,
    val gaugePressurePa: Double,
    val upthrustN: Double
)

data class ThermalPracticalResult(
    val heatTransferJ: Double,
    val finalLengthM: Double? = null
)

data class GasLawPracticalResult(
    val finalPressurePa: Double? = null,
    val finalVolumeM3: Double? = null
)

/**
 * Pure calculation models for introductory Physics practical learning.
 * Results are idealised calculations, not substitutes for measured observations.
 */
object PhysicsPracticalExtension {
    fun spring(springConstantNPerM: Double, extensionM: Double): SpringPracticalResult {
        require(springConstantNPerM.isFinite() && springConstantNPerM > 0.0) {
            "Spring constant must be finite and greater than zero."
        }
        require(extensionM.isFinite() && extensionM >= 0.0) {
            "Extension must be finite and cannot be negative."
        }
        val force = springConstantNPerM * extensionM
        val energy = 0.5 * springConstantNPerM * extensionM * extensionM
        require(force.isFinite() && energy.isFinite()) { "Calculated spring values are outside the supported range." }
        return SpringPracticalResult(force, energy)
    }

    fun pressure(forceN: Double, areaM2: Double): Double {
        require(forceN.isFinite() && forceN >= 0.0) { "Force must be finite and cannot be negative." }
        require(areaM2.isFinite() && areaM2 > 0.0) { "Area must be finite and greater than zero." }
        val result = forceN / areaM2
        require(result.isFinite()) { "Calculated pressure is outside the supported range." }
        return result
    }

    fun fluid(
        massKg: Double,
        volumeM3: Double,
        fluidDensityKgPerM3: Double,
        displacedVolumeM3: Double,
        depthM: Double,
        gravitationalFieldNPerKg: Double = 9.81
    ): FluidPracticalResult {
        require(massKg.isFinite() && massKg >= 0.0) { "Mass must be finite and cannot be negative." }
        require(volumeM3.isFinite() && volumeM3 > 0.0) { "Object volume must be finite and greater than zero." }
        require(fluidDensityKgPerM3.isFinite() && fluidDensityKgPerM3 > 0.0) { "Fluid density must be finite and greater than zero." }
        require(displacedVolumeM3.isFinite() && displacedVolumeM3 >= 0.0) { "Displaced volume must be finite and cannot be negative." }
        require(depthM.isFinite() && depthM >= 0.0) { "Depth must be finite and cannot be negative." }
        require(gravitationalFieldNPerKg.isFinite() && gravitationalFieldNPerKg > 0.0) { "Gravitational field strength must be finite and greater than zero." }
        val density = massKg / volumeM3
        val pressure = fluidDensityKgPerM3 * gravitationalFieldNPerKg * depthM
        val upthrust = fluidDensityKgPerM3 * gravitationalFieldNPerKg * displacedVolumeM3
        require(density.isFinite() && pressure.isFinite() && upthrust.isFinite()) {
            "Calculated fluid values are outside the supported range."
        }
        return FluidPracticalResult(density, pressure, upthrust)
    }

    fun heat(
        massKg: Double,
        specificHeatCapacityJPerKgK: Double,
        temperatureChangeK: Double
    ): Double {
        require(massKg.isFinite() && massKg >= 0.0) { "Mass must be finite and cannot be negative." }
        require(specificHeatCapacityJPerKgK.isFinite() && specificHeatCapacityJPerKgK > 0.0) {
            "Specific heat capacity must be finite and greater than zero."
        }
        require(temperatureChangeK.isFinite()) { "Temperature change must be finite." }
        val result = massKg * specificHeatCapacityJPerKgK * temperatureChangeK
        require(result.isFinite()) { "Calculated heat transfer is outside the supported range." }
        return result
    }

    fun latentHeat(massKg: Double, specificLatentHeatJPerKg: Double): Double {
        require(massKg.isFinite() && massKg >= 0.0) { "Mass must be finite and cannot be negative." }
        require(specificLatentHeatJPerKg.isFinite() && specificLatentHeatJPerKg > 0.0) {
            "Specific latent heat must be finite and greater than zero."
        }
        val result = massKg * specificLatentHeatJPerKg
        require(result.isFinite()) { "Calculated latent heat is outside the supported range." }
        return result
    }

    fun linearExpansion(
        originalLengthM: Double,
        coefficientPerKelvin: Double,
        temperatureChangeK: Double
    ): ThermalPracticalResult {
        require(originalLengthM.isFinite() && originalLengthM > 0.0) { "Original length must be finite and greater than zero." }
        require(coefficientPerKelvin.isFinite() && coefficientPerKelvin >= 0.0) {
            "Expansion coefficient must be finite and cannot be negative."
        }
        require(temperatureChangeK.isFinite()) { "Temperature change must be finite." }
        val finalLength = originalLengthM * (1.0 + coefficientPerKelvin * temperatureChangeK)
        require(finalLength.isFinite() && finalLength > 0.0) { "Calculated final length must be positive and finite." }
        return ThermalPracticalResult(
            heatTransferJ = originalLengthM * coefficientPerKelvin * temperatureChangeK,
            finalLengthM = finalLength
        )
    }

    fun boylesLawFinalPressure(
        initialPressurePa: Double,
        initialVolumeM3: Double,
        finalVolumeM3: Double
    ): GasLawPracticalResult {
        require(initialPressurePa.isFinite() && initialPressurePa > 0.0) { "Initial pressure must be finite and greater than zero." }
        require(initialVolumeM3.isFinite() && initialVolumeM3 > 0.0) { "Initial volume must be finite and greater than zero." }
        require(finalVolumeM3.isFinite() && finalVolumeM3 > 0.0) { "Final volume must be finite and greater than zero." }
        val result = initialPressurePa * initialVolumeM3 / finalVolumeM3
        require(result.isFinite() && result > 0.0) { "Calculated pressure must be positive and finite." }
        return GasLawPracticalResult(finalPressurePa = result)
    }

    fun charlesLawFinalVolume(
        initialVolumeM3: Double,
        initialTemperatureK: Double,
        finalTemperatureK: Double
    ): GasLawPracticalResult {
        require(initialVolumeM3.isFinite() && initialVolumeM3 > 0.0) { "Initial volume must be finite and greater than zero." }
        require(initialTemperatureK.isFinite() && initialTemperatureK > 0.0) { "Initial temperature must be in kelvin and greater than zero." }
        require(finalTemperatureK.isFinite() && finalTemperatureK > 0.0) { "Final temperature must be in kelvin and greater than zero." }
        val result = initialVolumeM3 * finalTemperatureK / initialTemperatureK
        require(result.isFinite() && result > 0.0) { "Calculated volume must be positive and finite." }
        return GasLawPracticalResult(finalVolumeM3 = result)
    }
}
