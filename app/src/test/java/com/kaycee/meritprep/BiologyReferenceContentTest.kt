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
