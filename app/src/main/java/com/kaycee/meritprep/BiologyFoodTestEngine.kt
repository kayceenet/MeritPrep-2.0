package com.kaycee.meritprep

enum class FoodTestSample(val label: String, internal val starch: Boolean, internal val reducingSugar: Boolean, internal val protein: Boolean, internal val lipid: Boolean) {
    STARCH("Starch solution", true, false, false, false),
    GLUCOSE("Glucose solution", false, true, false, false),
    ALBUMEN("Egg albumen", false, false, true, false),
    OIL("Vegetable oil", false, false, false, true),
    MILK("Milk", false, true, true, true)
}

enum class FoodTestType(val label: String) {
    IODINE("Iodine test"),
    BENEDICT("Benedict's test"),
    BIURET("Biuret test"),
    EMULSION("Ethanol emulsion test")
}

data class FoodTestResult(val positive: Boolean, val procedure: String, val observation: String, val inference: String)

object BiologyFoodTestEngine {
    fun evaluate(sample: FoodTestSample, test: FoodTestType): FoodTestResult {
        val positive = when (test) {
            FoodTestType.IODINE -> sample.starch
            FoodTestType.BENEDICT -> sample.reducingSugar
            FoodTestType.BIURET -> sample.protein
            FoodTestType.EMULSION -> sample.lipid
        }
        val procedure = when (test) {
            FoodTestType.IODINE -> "Place a small amount of sample in a test tube or on a spotting tile. Add a few drops of iodine solution and observe the colour."
            FoodTestType.BENEDICT -> "Add Benedict's reagent to the sample, then heat the mixture in a hot-water bath for about 2–5 minutes. Do not heat the test tube directly in a flame."
            FoodTestType.BIURET -> "Add Biuret reagent to the sample and mix gently. Observe for a lilac or purple colour."
            FoodTestType.EMULSION -> "Shake the sample with ethanol, then pour the ethanolic extract into water. A milky emulsion indicates lipid. Ethanol is flammable: keep it away from flames and use teacher supervision."
        }
        val observation = when (test) {
            FoodTestType.IODINE -> if (positive) "Iodine changes from yellow-brown to blue-black." else "Iodine remains yellow-brown; no blue-black colour develops."
            FoodTestType.BENEDICT -> if (positive) "On heating in a hot-water bath, the blue reagent changes through green, yellow or orange towards brick-red, depending on the amount of reducing sugar." else "After heating in a hot-water bath, the solution remains blue."
            FoodTestType.BIURET -> if (positive) "The blue Biuret reagent develops a lilac or purple colour." else "The mixture remains blue; no lilac or purple colour develops."
            FoodTestType.EMULSION -> if (positive) "After adding water to the ethanolic extract, a cloudy white emulsion forms." else "The mixture remains clear; no milky-white emulsion forms."
        }
        return FoodTestResult(
            positive = positive,
            procedure = procedure,
            observation = observation,
            inference = if (positive) "The sample gives a positive result for the tested food substance." else "The sample gives a negative result for the tested food substance."
        )
    }
}
