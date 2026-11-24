package com.kaycee.meritprep.data

enum class NetworkConnection { SERIES, PARALLEL }

data class ResistorNetworkResult(
    val equivalentResistanceOhms: Double,
    val totalCurrentAmps: Double,
    val totalPowerWatts: Double
)

data class CapacitorNetworkResult(
    val equivalentCapacitanceFarads: Double,
    val totalChargeCoulombs: Double,
    val storedEnergyJoules: Double
)

object PhysicsCircuitEngine {
    fun currentFromCharge(chargeCoulombs: Double, timeSeconds: Double): Double {
        require(chargeCoulombs.isFinite() && chargeCoulombs >= 0.0) {
            "Charge must be finite and cannot be negative."
        }
        require(timeSeconds.isFinite() && timeSeconds > 0.0) {
            "Time must be finite and greater than zero."
        }
        val current = chargeCoulombs / timeSeconds
        require(current.isFinite()) { "Calculated current is outside the supported range." }
        return current
    }

    fun resistorNetwork(
        resistancesOhms: List<Double>,
        supplyVoltageVolts: Double,
        connection: NetworkConnection
    ): ResistorNetworkResult {
        require(resistancesOhms.isNotEmpty()) { "Enter at least one resistance." }
        require(resistancesOhms.all { it.isFinite() && it > 0.0 }) {
            "Every resistance must be finite and greater than zero."
        }
        require(supplyVoltageVolts.isFinite() && supplyVoltageVolts >= 0.0) {
            "Supply voltage must be finite and cannot be negative."
        }
        val equivalent = when (connection) {
            NetworkConnection.SERIES -> resistancesOhms.sum()
            NetworkConnection.PARALLEL -> 1.0 / resistancesOhms.sumOf { 1.0 / it }
        }
        val current = supplyVoltageVolts / equivalent
        val power = supplyVoltageVolts * current
        require(equivalent.isFinite() && equivalent > 0.0 && current.isFinite() && power.isFinite()) {
            "Calculated resistor-network values are outside the supported range."
        }
        return ResistorNetworkResult(equivalent, current, power)
    }

    fun capacitorNetwork(
        capacitancesFarads: List<Double>,
        supplyVoltageVolts: Double,
        connection: NetworkConnection
    ): CapacitorNetworkResult {
        require(capacitancesFarads.isNotEmpty()) { "Enter at least one capacitance." }
        require(capacitancesFarads.all { it.isFinite() && it > 0.0 }) {
            "Every capacitance must be finite and greater than zero."
        }
        require(supplyVoltageVolts.isFinite() && supplyVoltageVolts >= 0.0) {
            "Supply voltage must be finite and cannot be negative."
        }
        val equivalent = when (connection) {
            NetworkConnection.SERIES -> 1.0 / capacitancesFarads.sumOf { 1.0 / it }
            NetworkConnection.PARALLEL -> capacitancesFarads.sum()
        }
        val charge = equivalent * supplyVoltageVolts
        val energy = 0.5 * equivalent * supplyVoltageVolts * supplyVoltageVolts
        require(equivalent.isFinite() && equivalent > 0.0 && charge.isFinite() && energy.isFinite()) {
            "Calculated capacitor-network values are outside the supported range."
        }
        return CapacitorNetworkResult(equivalent, charge, energy)
    }
}
