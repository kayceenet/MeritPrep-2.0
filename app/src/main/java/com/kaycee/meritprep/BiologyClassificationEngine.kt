package com.kaycee.meritprep

data class SpecimenFeatures(
    val photosynthetic: Boolean,
    val hasVascularTissue: Boolean,
    val producesSeeds: Boolean,
    val seedsEnclosedInFruit: Boolean,
    val hasOneCotyledon: Boolean,
    val isFungus: Boolean = false
)

data class ClassificationResult(
    val identification: String?,
    val keySteps: List<String>,
    val explanation: String
)

object BiologyClassificationEngine {
    fun identify(features: SpecimenFeatures): ClassificationResult {
        val steps = mutableListOf<String>()
        if (features.isFungus && !features.photosynthetic) {
            steps += "The specimen is not photosynthetic and has fungal characteristics."
            return ClassificationResult(
                "Mushroom (fungus)",
                steps,
                "Fungi obtain nutrients by absorption and do not photosynthesise. A mushroom is the reproductive structure of some fungi; the main body is usually a network of hyphae."
            )
        }
        if (!features.photosynthetic) {
            steps += "The specimen is not photosynthetic and does not match the fungal branch in this key."
            return ClassificationResult(null, steps, "This simplified key covers selected green plants and a mushroom. More observations or a broader key are needed.")
        }

        steps += "The specimen is photosynthetic, so follow the plant branch."
        if (!features.hasVascularTissue) {
            steps += "It has no vascular tissue."
            return ClassificationResult(
                "Moss (a non-vascular plant)",
                steps,
                "Mosses lack true xylem and phloem. They reproduce by spores and are commonly found in moist places."
            )
        }
        steps += "It has vascular tissue."
        if (!features.producesSeeds) {
            steps += "It does not produce seeds."
            return ClassificationResult(
                "Fern (a seedless vascular plant)",
                steps,
                "Ferns have vascular tissue but reproduce by spores rather than seeds. Their leaves are commonly called fronds."
            )
        }
        steps += "It produces seeds."
        if (!features.seedsEnclosedInFruit) {
            steps += "Its seeds are not enclosed in a fruit."
            return ClassificationResult(
                "Pine (a conifer)",
                steps,
                "Conifers produce exposed or 'naked' seeds, commonly borne on cone scales. They do not produce flowers or fruits."
            )
        }
        steps += "Its seeds are enclosed within a fruit, so it is a flowering plant."
        return if (features.hasOneCotyledon) {
            steps += "The seed has one cotyledon."
            ClassificationResult(
                "Maize (a monocot)",
                steps,
                "Maize is a flowering monocot. Typical monocot features include one cotyledon and parallel leaf venation, although classification should use a combination of characters."
            )
        } else {
            steps += "The seed has two cotyledons."
            ClassificationResult(
                "Bean (a eudicot)",
                steps,
                "Common bean is a flowering eudicot with two cotyledons. Typical eudicot features include net-like leaf venation and floral parts often in fours or fives."
            )
        }
    }
}
