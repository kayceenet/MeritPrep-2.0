package com.kaycee.meritprep

enum class OsmosisCellType { PLANT, ANIMAL }
enum class WaterMovement { INTO_CELL, NO_NET_MOVEMENT, OUT_OF_CELL }
enum class CellResponse { TURGID, FLACCID, PLASMOLYSED, NORMAL, SWELLING_OR_LYSIS_RISK, SHRINKING }

data class OsmosisResult(
    val tonicity: String,
    val waterMovement: WaterMovement,
    val response: CellResponse,
    val explanation: String
)

object OsmosisSimulation {
    fun evaluate(externalSoluteMolarity: Double, internalSoluteMolarity: Double, cellType: OsmosisCellType): OsmosisResult {
        require(
            externalSoluteMolarity.isFinite() && internalSoluteMolarity.isFinite() &&
                externalSoluteMolarity >= 0.0 && internalSoluteMolarity >= 0.0
        ) { "Solute concentrations must be finite, non-negative values." }
        val movement = when {
            externalSoluteMolarity < internalSoluteMolarity -> WaterMovement.INTO_CELL
            externalSoluteMolarity > internalSoluteMolarity -> WaterMovement.OUT_OF_CELL
            else -> WaterMovement.NO_NET_MOVEMENT
        }
        val tonicity = when (movement) {
            WaterMovement.INTO_CELL -> "Hypotonic external solution"
            WaterMovement.NO_NET_MOVEMENT -> "Isotonic model condition"
            WaterMovement.OUT_OF_CELL -> "Hypertonic external solution"
        }
        val response = when (cellType) {
            OsmosisCellType.PLANT -> when (movement) {
                WaterMovement.INTO_CELL -> CellResponse.TURGID
                WaterMovement.NO_NET_MOVEMENT -> CellResponse.FLACCID
                WaterMovement.OUT_OF_CELL -> CellResponse.PLASMOLYSED
            }
            OsmosisCellType.ANIMAL -> when (movement) {
                WaterMovement.INTO_CELL -> CellResponse.SWELLING_OR_LYSIS_RISK
                WaterMovement.NO_NET_MOVEMENT -> CellResponse.NORMAL
                WaterMovement.OUT_OF_CELL -> CellResponse.SHRINKING
            }
        }
        val explanation = when (movement) {
            WaterMovement.INTO_CELL -> if (cellType == OsmosisCellType.PLANT)
                "The external solution is more dilute than the cell sap in this model. Water enters by osmosis. The cell becomes turgid; the cell wall resists excessive expansion."
            else
                "The external solution is more dilute than the cell contents in this model. Water enters by osmosis, so the animal cell swells and may burst if the influx is excessive."
            WaterMovement.NO_NET_MOVEMENT -> if (cellType == OsmosisCellType.PLANT)
                "There is no net water movement in this model. The plant cell is likely to be flaccid because it has little net turgor pressure."
            else
                "There is no net water movement in this model. The animal cell maintains approximately the same volume."
            WaterMovement.OUT_OF_CELL -> if (cellType == OsmosisCellType.PLANT)
                "The external solution is more concentrated than the cell sap in this model. Water leaves by osmosis; the protoplast may pull away from the cell wall, a process called plasmolysis."
            else
                "The external solution is more concentrated than the cell contents in this model. Water leaves by osmosis and the animal cell shrinks."
        }
        return OsmosisResult(tonicity, movement, response, explanation)
    }
}
