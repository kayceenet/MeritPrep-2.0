package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ClassificationKeyModelTest {
    @Test
    fun everyPlantSpecimenHasAValidDichotomousPath() {
        val expected = mapOf(
            "moss" to "Bryophyte (moss)",
            "fern" to "Pteridophyte (fern)",
            "pine" to "Gymnosperm (conifer)",
            "hibiscus" to "Angiosperm (flowering plant)"
        )
        expected.forEach { (id, result) ->
            val specimen = ClassificationKeyModel.specimen(id)
            assertNotNull(specimen)
            assertEquals(result, specimen!!.result)
            assertTrue(specimen.decisions.isNotEmpty())
            assertTrue(ClassificationKeyModel.isComplete(id, specimen.decisions.size))
            assertFalse(ClassificationKeyModel.isComplete(id, specimen.decisions.size - 1))
        }
    }

    @Test
    fun plantKeyUsesVascularTissueSeedsAndFlowersInCorrectOrder() {
        assertEquals(listOf(false), ClassificationKeyModel.specimen("moss")!!.decisions)
        assertEquals(listOf(true, false), ClassificationKeyModel.specimen("fern")!!.decisions)
        assertEquals(listOf(true, true, false), ClassificationKeyModel.specimen("pine")!!.decisions)
        assertEquals(listOf(true, true, true), ClassificationKeyModel.specimen("hibiscus")!!.decisions)
        assertEquals(3, ClassificationKeyModel.stepsFor("pine").size)
    }

    @Test
    fun animalKeyDistinguishesInvertebratesAndFiveVertebrateClasses() {
        val expected = mapOf(
            "sponge" to "Invertebrate — Porifera",
            "hydra" to "Invertebrate — Cnidaria",
            "planarian" to "Invertebrate — Platyhelminthes",
            "roundworm" to "Invertebrate — Nematoda",
            "earthworm" to "Invertebrate — Annelida",
            "grasshopper" to "Invertebrate — Arthropoda",
            "snail" to "Invertebrate — Mollusca",
            "starfish" to "Invertebrate — Echinodermata",
            "pigeon" to "Vertebrate — bird (Aves)",
            "bat" to "Vertebrate — mammal (Mammalia)",
            "tilapia" to "Vertebrate — fish",
            "frog" to "Vertebrate — amphibian (Amphibia)",
            "lizard" to "Vertebrate — reptile (Reptilia)"
        )
        expected.forEach { (id, result) ->
            assertEquals(result, ClassificationKeyModel.specimen(id)!!.result)
        }
        assertEquals(9, ClassificationKeyModel.stepsFor("earthworm").size)
        assertEquals(5, ClassificationKeyModel.stepsFor("pigeon").size)
    }


    @Test
    fun specimenLabelsDoNotGiveAwayTheIdentification() {
        ClassificationKeyModel.specimens(ClassificationKeyTrack.PLANTS).forEach {
            assertTrue(it.name.startsWith("Plant specimen"))
        }
        ClassificationKeyModel.specimens(ClassificationKeyTrack.ANIMALS).forEach {
            assertTrue(it.name.startsWith("Animal specimen"))
        }
    }

    @Test
    fun wrongDecisionCannotProduceAnOrganismIdentification() {
        val correctPlantPath = ClassificationKeyModel.specimen("pine")!!.decisions
        assertEquals("Gymnosperm (conifer)", ClassificationKeyModel.identify("pine", correctPlantPath))
        assertEquals(null, ClassificationKeyModel.identify("pine", listOf(true, false)))
        assertEquals(null, ClassificationKeyModel.identify("pine", listOf(true, true)))
        assertEquals(true, ClassificationKeyModel.isCorrectDecision("pine", 1, true))
        assertEquals(false, ClassificationKeyModel.isCorrectDecision("pine", 1, false))
    }

    @Test
    fun everySpecimenDecisionMatchesAnAvailableQuestion() {
        ClassificationKeyTrack.entries.forEach { track ->
            ClassificationKeyModel.specimens(track).forEach { specimen ->
                val steps = ClassificationKeyModel.stepsFor(specimen.id)
                assertTrue(specimen.decisions.size <= steps.size)
                specimen.decisions.forEachIndexed { index, expected ->
                    assertEquals(expected, ClassificationKeyModel.expectedDecision(specimen.id, index))
                }
            }
        }
    }
}
