package com.kaycee.meritprep

enum class ABOAllele(val notation: String, val dominanceRank: Int) {
    A("Iᴬ", 0),
    B("Iᴮ", 1),
    O("i", 2)
}

enum class ABOGenotype(val notation: String, val gametes: List<ABOAllele>) {
    A_HOMOZYGOUS("IᴬIᴬ", listOf(ABOAllele.A, ABOAllele.A)),
    A_HETEROZYGOUS("Iᴬi", listOf(ABOAllele.A, ABOAllele.O)),
    B_HOMOZYGOUS("IᴮIᴮ", listOf(ABOAllele.B, ABOAllele.B)),
    B_HETEROZYGOUS("Iᴮi", listOf(ABOAllele.B, ABOAllele.O)),
    AB("IᴬIᴮ", listOf(ABOAllele.A, ABOAllele.B)),
    O("ii", listOf(ABOAllele.O, ABOAllele.O))
}

data class ABOOffspringOutcome(
    val genotype: String,
    val phenotype: String,
    val countOutOfFour: Int,
    val percentage: Double
)

data class ABOInheritanceResult(
    val parentOne: String,
    val parentTwo: String,
    val fourPossibleCombinations: List<String>,
    val outcomes: List<ABOOffspringOutcome>,
    val explanation: String
)

/**
 * Simplified ABO blood-group inheritance. IA and IB are codominant;
 * both are dominant over i. Rh factor and rare blood-group variants are excluded.
 */
object ABOInheritanceSimulation {
    fun evaluate(parentOne: ABOGenotype, parentTwo: ABOGenotype): ABOInheritanceResult {
        val pairs = parentOne.gametes.flatMap { first ->
            parentTwo.gametes.map { second -> canonicalPair(first, second) }
        }
        val counts = pairs.groupingBy { it }.eachCount()
        val outcomes = counts.entries
            .groupBy { phenotype(it.key) }
            .map { (bloodGroup, entries) ->
                val count = entries.sumOf { it.value }
                ABOOffspringOutcome(
                    genotype = entries.joinToString(" / ") { it.key.notation },
                    phenotype = bloodGroup,
                    countOutOfFour = count,
                    percentage = count * 25.0
                )
            }
            .sortedBy { listOf("A", "B", "AB", "O").indexOf(it.phenotype) }

        val explanation = when {
            parentOne == ABOGenotype.O && parentTwo == ABOGenotype.O ->
                "Both parents have genotype ii and can pass only i. All offspring are expected to have genotype ii and blood group O."
            parentOne == ABOGenotype.AB && parentTwo == ABOGenotype.AB ->
                "Each parent can pass Iᴬ or Iᴮ. The expected offspring groups are A, AB and B; group O is not possible because neither parent carries i."
            else ->
                "Iᴬ and Iᴮ are codominant: a person with IᴬIᴮ has blood group AB. Each is dominant over i, so Iᴬi gives group A and Iᴮi gives group B. The percentages are probabilities, not guarantees for a small family."
        }
        return ABOInheritanceResult(
            parentOne.notation,
            parentTwo.notation,
            pairs.map { it.notation },
            outcomes,
            explanation
        )
    }

    private data class AllelePair(val first: ABOAllele, val second: ABOAllele) {
        val notation: String get() = first.notation + second.notation
    }

    private fun canonicalPair(first: ABOAllele, second: ABOAllele): AllelePair =
        if (first.dominanceRank <= second.dominanceRank) AllelePair(first, second)
        else AllelePair(second, first)

    private fun phenotype(pair: AllelePair): String = when {
        pair.first == ABOAllele.A && pair.second == ABOAllele.B -> "AB"
        pair.first == ABOAllele.A || pair.second == ABOAllele.A -> "A"
        pair.first == ABOAllele.B || pair.second == ABOAllele.B -> "B"
        else -> "O"
    }
}
