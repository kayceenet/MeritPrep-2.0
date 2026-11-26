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
            assertEquals(specimen.decisions.size, specimen.decisions.distinct().size.coerceAtLeast(1).let { specimen.decisions.size })
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
        assertEquals(3, ClassificationKeyModel.steps(ClassificationKeyTrack.PLANTS).size)
    }

    @Test
    fun animalKeyDistinguishesInvertebratesAndFiveVertebrateClasses() {
        val expected = mapOf(
            "earthworm" to "Invertebrate",
            "pigeon" to "Vertebrate — bird (Aves)",
            "bat" to "Vertebrate — mammal (Mammalia)",
            "tilapia" to "Vertebrate — fish",
            "frog" to "Vertebrate — amphibian (Amphibia)",
            "lizard" to "Vertebrate — reptile (Reptilia)"
        )
        expected.forEach { (id, result) ->
            assertEquals(result, ClassificationKeyModel.specimen(id)!!.result)
        }
        assertEquals(5, ClassificationKeyModel.steps(ClassificationKeyTrack.ANIMALS).size)
    }

    @Test
    fun everySpecimenDecisionMatchesAnAvailableQuestion() {
        ClassificationKeyTrack.entries.forEach { track ->
            val steps = ClassificationKeyModel.steps(track)
            ClassificationKeyModel.specimens(track).forEach { specimen ->
                assertTrue(specimen.decisions.size <= steps.size)
                specimen.decisions.forEachIndexed { index, expected ->
                    assertEquals(expected, ClassificationKeyModel.expectedDecision(specimen.id, index))
                }
            }
        }
    }
}
