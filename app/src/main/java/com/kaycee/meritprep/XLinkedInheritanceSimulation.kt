package com.kaycee.meritprep

enum class XLinkedMotherGenotype(val notation: String, val gametes: List<String>) {
    UNAFFECTED("XᴴXᴴ", listOf("Xᴴ", "Xᴴ")),
    CARRIER("XᴴXʰ", listOf("Xᴴ", "Xʰ")),
    AFFECTED("XʰXʰ", listOf("Xʰ", "Xʰ"))
}

enum class XLinkedFatherGenotype(val notation: String, val gametes: List<String>) {
    UNAFFECTED("XᴴY", listOf("Xᴴ", "Y")),
    AFFECTED("XʰY", listOf("Xʰ", "Y"))
}

data class XLinkedOffspringOutcome(
    val genotype: String,
    val sex: String,
    val phenotype: String,
    val countOutOfFour: Int,
    val percentage: Double
)

data class XLinkedInheritanceResult(
    val mother: String,
    val father: String,
    val fourPossibleCombinations: List<String>,
    val outcomes: List<XLinkedOffspringOutcome>,
    val explanation: String
)

/**
 * Probability model for one X-linked recessive allele.
 * H represents the usual allele in this teaching example; h represents a recessive allele.
 */
object XLinkedInheritanceSimulation {
    fun evaluate(
        mother: XLinkedMotherGenotype,
        father: XLinkedFatherGenotype
    ): XLinkedInheritanceResult {
        val combinations = mother.gametes.flatMap { maternal ->
            father.gametes.map { paternal ->
                if (paternal == "Y") maternal + "Y" else canonicalFemale(maternal, paternal)
            }
        }
        val outcomes = combinations.groupingBy { it }.eachCount().map { (genotype, count) ->
            val isMale = genotype.endsWith("Y")
            val affected = if (isMale) genotype.startsWith("Xʰ") else genotype == "XʰXʰ"
            val carrier = !isMale && genotype == "XᴴXʰ"
            XLinkedOffspringOutcome(
                genotype = genotype,
                sex = if (isMale) "Male" else "Female",
                phenotype = when {
                    affected && isMale -> "Affected male"
                    affected -> "Affected female"
                    carrier -> "Carrier female"
                    isMale -> "Unaffected male"
                    else -> "Unaffected female"
                },
                countOutOfFour = count,
                percentage = count * 25.0
            )
        }.sortedWith(compareBy<XLinkedOffspringOutcome> { if (it.sex == "Female") 0 else 1 }.thenBy { it.genotype })

        val explanation = when {
            mother == XLinkedMotherGenotype.CARRIER && father == XLinkedFatherGenotype.UNAFFECTED ->
                "A carrier mother can pass Xᴴ or Xʰ. The unaffected father passes Xᴴ to daughters and Y to sons. In this cross, no daughter is affected, while half the sons are expected to be affected."
            mother == XLinkedMotherGenotype.AFFECTED && father == XLinkedFatherGenotype.UNAFFECTED ->
                "The mother passes Xʰ to every child. The father passes Xᴴ to daughters and Y to sons, so all daughters are carriers and all sons are affected in this model."
            else ->
                "A daughter receives an X chromosome from each parent; a son receives the father's Y and the mother's X. A male with one X-linked recessive allele expresses it in this simplified model because he has no second X allele at that locus."
        }
        return XLinkedInheritanceResult(
            mother.notation, father.notation, combinations, outcomes, explanation
        )
    }

    private fun canonicalFemale(first: String, second: String): String =
        if (first == "Xᴴ") first + second else second + first
}
