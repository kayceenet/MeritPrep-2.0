package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Test

class OsmosisSimulationTest {
    @Test fun diluteSolutionMakesPlantCellTurgid() {
        val result = OsmosisSimulation.evaluate(0.1, 0.3, OsmosisCellType.PLANT)
        assertEquals(WaterMovement.INTO_CELL, result.waterMovement)
        assertEquals(CellResponse.TURGID, result.response)
    }

    @Test fun concentratedSolutionCanPlasmolysePlantCell() {
        val result = OsmosisSimulation.evaluate(0.6, 0.3, OsmosisCellType.PLANT)
        assertEquals(WaterMovement.OUT_OF_CELL, result.waterMovement)
        assertEquals(CellResponse.PLASMOLYSED, result.response)
    }

    @Test fun hypotonicSolutionCanCauseAnimalCellToSwell() {
        val result = OsmosisSimulation.evaluate(0.1, 0.3, OsmosisCellType.ANIMAL)
        assertEquals(CellResponse.SWELLING_OR_LYSIS_RISK, result.response)
    }

    @Test fun equalModelConcentrationsProduceNoNetMovement() {
        val result = OsmosisSimulation.evaluate(0.3, 0.3, OsmosisCellType.PLANT)
        assertEquals(WaterMovement.NO_NET_MOVEMENT, result.waterMovement)
        assertEquals(CellResponse.FLACCID, result.response)
    }
}
