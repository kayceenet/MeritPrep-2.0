package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
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
    @Test fun animalAndPlantCellsRespondDifferentlyWhenWaterLeaves() {
        val plant = OsmosisSimulation.evaluate(0.6, 0.3, OsmosisCellType.PLANT)
        val animal = OsmosisSimulation.evaluate(0.6, 0.3, OsmosisCellType.ANIMAL)
        assertEquals(CellResponse.PLASMOLYSED, plant.response)
        assertEquals(CellResponse.SHRINKING, animal.response)
        assertEquals(plant.waterMovement, animal.waterMovement)
    }

    @Test fun rejectsNonFiniteConcentrations() {
        assertThrows(IllegalArgumentException::class.java) {
            OsmosisSimulation.evaluate(Double.POSITIVE_INFINITY, 0.3, OsmosisCellType.PLANT)
        }
        assertThrows(IllegalArgumentException::class.java) {
            OsmosisSimulation.evaluate(0.3, Double.NaN, OsmosisCellType.ANIMAL)
        }
    }

    @Test fun rejectsNegativeConcentrations() {
        assertThrows(IllegalArgumentException::class.java) {
            OsmosisSimulation.evaluate(-0.1, 0.3, OsmosisCellType.PLANT)
        }
    }

}
