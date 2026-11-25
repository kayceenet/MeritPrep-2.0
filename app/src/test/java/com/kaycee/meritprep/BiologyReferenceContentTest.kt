package com.kaycee.meritprep

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BiologyReferenceContentTest {
    @Test
    fun originalObjectiveQuestionBankHasValidAnswersAndSyllabusLinks() {
        val questions = BiologyQuestionBank.all
        val lessonIds = BiologyCurriculum.units.mapNotNull { it.lessonId }.toSet()

        assertTrue("The Biology bank should contain a substantial examination practice set.", questions.size >= 200)
        assertEquals("Question IDs must be unique across all topic banks.", questions.size, questions.map { it.id }.toSet().size)

        questions.forEach { question ->
            assertTrue("${question.id}: prompt is required", question.prompt.isNotBlank())
            assertTrue("${question.id}: at least four options are expected for this objective bank", question.options.size >= 4)
            assertTrue("${question.id}: correct answer index must be valid", question.correctOptionIndex in question.options.indices)
            assertTrue("${question.id}: answer explanation is required", question.explanation.isNotBlank())
            assertTrue("${question.id}: source provenance must be explicit", question.source.startsWith("MeritPrep original practice item"))
            assertTrue("${question.id}: topic must map to the Biology syllabus", question.topicId in lessonIds)
            assertTrue("${question.id}: original items must not be labelled as independently verified", !question.isVerified)
        }
    }

    @Test
    fun cellDivisionPracticeCoversTheCoreMitosisAndMeiosisConcepts() {
        val questions = BiologyQuestionBank.all.associateBy { it.id }
        val requiredIds = (11..22).map { "bio-grow-${it.toString().padStart(3, '0')}" }

        assertTrue("The bank must include a dedicated set of cell-division questions.", requiredIds.all { it in questions })
        val prompts = requiredIds.mapNotNull { questions[it]?.prompt }.joinToString(" ").lowercase()
        assertTrue(prompts.contains("mitosis"))
        assertTrue(prompts.contains("meiosis"))
        assertTrue(prompts.contains("sister chromatids"))
        assertTrue(prompts.contains("crossing over"))
        assertTrue(prompts.contains("fertilisation"))
    }

    @Test
    fun aboPracticeIncludesGenotypeAndOffspringProbabilityQuestions() {
        val questions = BiologyQuestionBank.all.associateBy { it.id }
        val ids = (11..14).map { "bio-var-${it.toString().padStart(3, '0')}" }
        assertTrue(ids.all { it in questions })
        val content = ids.mapNotNull { questions[it]?.prompt + " " + questions[it]?.explanation }.joinToString(" ").lowercase()
        assertTrue(content.contains("blood group o"))
        assertTrue(content.contains("25%"))
        assertTrue(content.contains("iᴬi"))
        assertTrue(content.contains("iᴮi"))
        assertTrue(content.contains("genotype iᴬiᴮ"))
    }

    @Test
    fun glossaryDefinesCellDivisionAndABOInheritanceTerms() {
        val entries = BiologyGlossary.entries.associateBy { it.term }
        val required = listOf(
            "Cell cycle", "Interphase", "Sister chromatids", "Homologous chromosomes",
            "Crossing over", "Haploid", "Diploid", "Codominance", "Multiple alleles", "ABO blood group",
            "X-linked inheritance", "Carrier"
        )
        assertTrue(required.all { it in entries })
        assertTrue(entries.getValue("Crossing over").definition.contains("prophase I"))
        assertTrue(entries.getValue("Codominance").example.contains("IᴬIᴮ"))
        assertTrue(entries.getValue("Multiple alleles").definition.contains("diploid individual"))
    }

    @Test
    fun glossaryEntriesAreCompleteUniqueAndLinkedToLessons() {
        val entries = BiologyGlossary.entries
        val lessonIds = BiologyCurriculum.units.mapNotNull { it.lessonId }.toSet()
        val normalisedTerms = entries.map { it.term.trim().lowercase() }

        assertTrue("The Biology glossary should contain useful reference coverage.", entries.size >= 30)
        assertEquals("Glossary terms must be unique, ignoring case and surrounding spaces.", entries.size, normalisedTerms.toSet().size)

        entries.forEach { entry ->
            assertTrue("Glossary term is required", entry.term.isNotBlank())
            assertTrue("${entry.term}: definition is required", entry.definition.isNotBlank())
            assertTrue("${entry.term}: practical example is required", entry.example.isNotBlank())
            assertTrue("${entry.term}: lesson link must be valid", entry.topicId in lessonIds)
        }
    }
}
