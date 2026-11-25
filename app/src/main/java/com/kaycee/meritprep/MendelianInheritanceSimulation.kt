package com.kaycee.meritprep

enum class MendelianGenotype(val notation: String, val gametes: List<String>) {
    HOMOZYGOUS_DOMINANT("AA", listOf("A", "A")),
    HETEROZYGOUS("Aa", listOf("A", "a")),
    HOMOZYGOUS_RECESSIVE("aa", listOf("a", "a"))
}

data class MendelianOffspringOutcome(
    val genotype: String,
    val countOutOfFour: Int,
    val percentage: Double,
    val dominantPhenotypePercentage: Double,
    val recessivePhenotypePercentage: Double
)

data class MendelianCrossResult(
    val parentOne: String,
    val parentTwo: String,
    val fourPossibleCombinations: List<String>,
    val outcomes: List<MendelianOffspringOutcome>,
    val dominantPhenotypePercentage: Double,
    val recessivePhenotypePercentage: Double,
    val explanation: String
)

/**
 * One-gene Mendelian cross with complete dominance and equal gamete probabilities.
 * A is a dominant allele and a is a recessive allele. This is a probability model,
 * not a prediction that every family will produce exactly four offspring.
 */
object MendelianInheritanceSimulation {
    fun evaluate(parentOne: MendelianGenotype, parentTwo: MendelianGenotype): MendelianCrossResult {
        val combinations = parentOne.gametes.flatMap { first ->
            parentTwo.gametes.map { second -> canonicalGenotype(first + second) }
        }
        val counts = combinations.groupingBy { it }.eachCount()
        val outcomes = listOf("AA", "Aa", "aa").mapNotNull { genotype ->
            val count = counts[genotype] ?: return@mapNotNull null
            val dominant = genotype != "aa"
            MendelianOffspringOutcome(
                genotype = genotype,
                countOutOfFour = count,
                percentage = count * 25.0,
                dominantPhenotypePercentage = if (dominant) count * 25.0 else 0.0,
                recessivePhenotypePercentage = if (dominant) 0.0 else count * 25.0
            )
        }
        val dominantPercent = outcomes.filter { it.genotype != "aa" }.sumOf { it.percentage }
        val recessivePercent = outcomes.filter { it.genotype == "aa" }.sumOf { it.percentage }
        val explanation = when {
            parentOne == MendelianGenotype.HOMOZYGOUS_RECESSIVE &&
                parentTwo == MendelianGenotype.HOMOZYGOUS_RECESSIVE ->
                "Both parents can pass only the recessive allele a. All offspring are aa and show the recessive phenotype."
            parentOne == MendelianGenotype.HOMOZYGOUS_DOMINANT ||
                parentTwo == MendelianGenotype.HOMOZYGOUS_DOMINANT ->
                if (recessivePercent == 0.0)
                    "At least one parent is AA and can pass only A. No aa offspring are possible in this cross, although some offspring may be carriers (Aa)."
                else
                    "The AA parent always passes A. The other parent can pass A or a, so offspring may be AA or Aa; none are aa."
            else ->
                "Each heterozygous parent can pass A or a. The expected genotype ratio is 1 AA : 2 Aa : 1 aa, giving a 3:1 dominant-to-recessive phenotype ratio under complete dominance."
        }
        return MendelianCrossResult(
            parentOne.notation, parentTwo.notation, combinations, outcomes,
            dominantPercent, recessivePercent, explanation
        )
    }

    private fun canonicalGenotype(pair: String): String =
        if (pair == "aA") "Aa" else pair
}
