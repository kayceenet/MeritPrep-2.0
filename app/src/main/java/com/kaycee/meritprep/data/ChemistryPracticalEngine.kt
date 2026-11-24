package com.kaycee.meritprep.data

data class TitrationResult(
    val titreCm3: Double,
    val analyteConcentrationMolPerDm3: Double,
    val calculation: String
)

object ChemistryPracticalEngine {
    fun meanConcordantTitre(
        initialReadingsCm3: List<Double>,
        finalReadingsCm3: List<Double>,
        toleranceCm3: Double = 0.20
    ): Double {
        require(initialReadingsCm3.size == finalReadingsCm3.size && initialReadingsCm3.size >= 2) {
            "Enter at least two complete burette readings."
        }
        require(toleranceCm3.isFinite() && toleranceCm3 > 0.0) {
            "Concordance tolerance must be greater than zero."
        }
        require(initialReadingsCm3.all { it.isFinite() && it >= 0.0 } &&
            finalReadingsCm3.all { it.isFinite() && it >= 0.0 }) {
            "Burette readings must be valid non-negative numbers."
        }
        val titres = initialReadingsCm3.indices.map { index ->
            finalReadingsCm3[index] - initialReadingsCm3[index]
        }
        require(titres.all { it > 0.0 }) {
            "Each final burette reading must exceed its initial reading."
        }
        val sorted = titres.sorted()
        val bestCluster = sorted.indices.flatMap { start ->
            (start until sorted.size).mapNotNull { end ->
                val cluster = sorted.subList(start, end + 1)
                if (cluster.size >= 2 && cluster.last() - cluster.first() <= toleranceCm3) cluster else null
            }
        }.maxByOrNull { it.size }
        require(bestCluster != null) {
            "No two titres are concordant within %.2f cm³. Repeat the titration.".format(toleranceCm3)
        }
        return bestCluster.average()
    }

    fun calculateOneToOneTitration(
        titrantConcentration: Double,
        titreCm3: Double,
        analyteAliquotCm3: Double
    ): TitrationResult {
        require(titrantConcentration.isFinite() && titrantConcentration > 0.0) {
            "Titrant concentration must be greater than zero."
        }
        require(titreCm3.isFinite() && titreCm3 > 0.0) {
            "Titre must be greater than zero."
        }
        require(analyteAliquotCm3.isFinite() && analyteAliquotCm3 > 0.0) {
            "Analyte aliquot must be greater than zero."
        }
        val concentration = titrantConcentration * titreCm3 / analyteAliquotCm3
        require(concentration.isFinite()) { "Calculated concentration is outside the supported range." }
        return TitrationResult(
            titreCm3 = titreCm3,
            analyteConcentrationMolPerDm3 = concentration,
            calculation = "C₁V₁ = C₂V₂; C₂ = (%.5g × %.4g) ÷ %.4g".format(
                java.util.Locale.US, titrantConcentration, titreCm3, analyteAliquotCm3
            )
        )
    }

    fun cationObservation(ion: String, reagent: String, excess: Boolean, warmed: Boolean = false): String {
        val knownIons = setOf("NH₄⁺", "Ca²⁺", "Pb²⁺", "Cu²⁺", "Fe²⁺", "Fe³⁺", "Al³⁺", "Zn²⁺")
        require(ion in knownIons) { "Select a cation in the practical reference." }
        require(reagent == "NaOH(aq)" || reagent == "NH₃(aq)") { "Select aqueous sodium hydroxide or aqueous ammonia." }
        if (ion == "NH₄⁺") {
            require(reagent == "NaOH(aq)") { "The ammonium test in this model uses aqueous sodium hydroxide." }
            return if (warmed) {
                "On warming, ammonia gas is evolved; it turns damp red litmus paper blue."
            } else {
                "Add aqueous sodium hydroxide, then warm gently. Test any evolved gas with damp red litmus paper."
            }
        }
        return when (ion to reagent) {
            "Ca²⁺" to "NaOH(aq)" -> if (excess) "White precipitate remains insoluble in excess sodium hydroxide." else "White precipitate forms."
            "Ca²⁺" to "NH₃(aq)" -> "No significant precipitate is expected under this simplified test condition."
            "Pb²⁺" to "NaOH(aq)" -> if (excess) "White precipitate dissolves in excess sodium hydroxide." else "White precipitate forms."
            "Pb²⁺" to "NH₃(aq)" -> "White precipitate remains insoluble in excess ammonia."
            "Cu²⁺" to "NaOH(aq)" -> "Light-blue precipitate forms and remains insoluble in excess sodium hydroxide."
            "Cu²⁺" to "NH₃(aq)" -> if (excess) "Pale-blue precipitate dissolves in excess ammonia to form a deep-blue solution." else "Pale-blue precipitate forms."
            "Fe²⁺" to "NaOH(aq)" -> "Green precipitate forms; it gradually turns brown on exposure to air."
            "Fe²⁺" to "NH₃(aq)" -> "Green precipitate forms and may turn brown on standing."
            "Fe³⁺" to "NaOH(aq)", "Fe³⁺" to "NH₃(aq)" -> "Reddish-brown precipitate forms."
            "Al³⁺" to "NaOH(aq)" -> if (excess) "White gelatinous precipitate dissolves in excess sodium hydroxide." else "White gelatinous precipitate forms."
            "Al³⁺" to "NH₃(aq)" -> "White gelatinous precipitate remains insoluble in excess ammonia."
            "Zn²⁺" to "NaOH(aq)" -> if (excess) "White precipitate dissolves in excess sodium hydroxide." else "White precipitate forms."
            "Zn²⁺" to "NH₃(aq)" -> if (excess) "White precipitate dissolves in excess ammonia." else "White precipitate forms."
            else -> "No observation is provided for this combination in the simplified reference."
        }
    }
}
