package com.kaycee.meritprep

import com.kaycee.meritprep.data.NetworkConnection
import com.kaycee.meritprep.data.PhysicsCircuitEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PhysicsCircuitEngineTest {
    @Test fun calculatesCurrentFromChargeAndTime() {
        assertEquals(2.0, PhysicsCircuitEngine.currentFromCharge(10.0, 5.0), 1e-9)
    }

    @Test fun calculatesSeriesAndParallelResistorNetworks() {
        val series = PhysicsCircuitEngine.resistorNetwork(listOf(4.0, 6.0), 12.0, NetworkConnection.SERIES)
        assertEquals(10.0, series.equivalentResistanceOhms, 1e-9)
        assertEquals(1.2, series.totalCurrentAmps, 1e-9)
        assertEquals(14.4, series.totalPowerWatts, 1e-9)

        val parallel = PhysicsCircuitEngine.resistorNetwork(listOf(4.0, 6.0), 12.0, NetworkConnection.PARALLEL)
        assertEquals(2.4, parallel.equivalentResistanceOhms, 1e-9)
        assertEquals(5.0, parallel.totalCurrentAmps, 1e-9)
        assertEquals(60.0, parallel.totalPowerWatts, 1e-9)
    }

    @Test fun calculatesSeriesAndParallelCapacitorNetworks() {
        val series = PhysicsCircuitEngine.capacitorNetwork(listOf(6e-6, 3e-6), 12.0, NetworkConnection.SERIES)
        assertEquals(2e-6, series.equivalentCapacitanceFarads, 1e-12)
        assertEquals(24e-6, series.totalChargeCoulombs, 1e-12)
        assertEquals(144e-6, series.storedEnergyJoules, 1e-12)

        val parallel = PhysicsCircuitEngine.capacitorNetwork(listOf(6e-6, 3e-6), 12.0, NetworkConnection.PARALLEL)
        assertEquals(9e-6, parallel.equivalentCapacitanceFarads, 1e-12)
        assertEquals(108e-6, parallel.totalChargeCoulombs, 1e-12)
        assertEquals(648e-6, parallel.storedEnergyJoules, 1e-12)
    }

    @Test fun rejectsInvalidInputs() {
        assertThrows(IllegalArgumentException::class.java) { PhysicsCircuitEngine.currentFromCharge(-1.0, 2.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsCircuitEngine.currentFromCharge(1.0, 0.0) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsCircuitEngine.resistorNetwork(emptyList(), 12.0, NetworkConnection.SERIES) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsCircuitEngine.resistorNetwork(listOf(4.0, 0.0), 12.0, NetworkConnection.PARALLEL) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsCircuitEngine.capacitorNetwork(listOf(-1e-6), 12.0, NetworkConnection.PARALLEL) }
        assertThrows(IllegalArgumentException::class.java) { PhysicsCircuitEngine.capacitorNetwork(listOf(1e-6), -12.0, NetworkConnection.SERIES) }
    }
}
