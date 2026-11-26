package com.kaycee.meritprep

import com.kaycee.meritprep.data.AssessmentQuestion

/**
 * A fixed science-subject practice combination: Use of English (60), Biology (40),
 * Chemistry (40) and Physics (40). All items are original MeritPrep practice,
 * not official JAMB past questions.
 */
object UtmeComboQuestionBank {
    private fun balancedByTopic(pool: List<AssessmentQuestion>, count: Int): List<AssessmentQuestion> {
        require(pool.size >= count)
        val buckets = pool.groupBy { it.topicId }.toSortedMap().mapValues { (_, items) -> items.shuffled() }
        val topicOrder = buckets.keys.toList()
        val selected = mutableListOf<AssessmentQuestion>()
        var round = 0
        while (selected.size < count) {
            var added = false
            topicOrder.forEach { topic ->
                buckets.getValue(topic).getOrNull(round)?.let {
                    if (selected.size < count) {
                        selected.add(it)
                        added = true
                    }
                }
            }
            if (!added) break
            round++
        }
        check(selected.size == count)
        return selected
    }

    val english = EnglishQuestionBank.all
    val biology = balancedByTopic(BiologyQuestionBank.all, 40)
    val chemistry = balancedByTopic(ChemistryQuestionBank.all, 40)
    val physics = balancedByTopic(PhysicsQuestionBank.all, 40)

    val all: List<AssessmentQuestion> = english + biology + chemistry + physics

    init {
        require(english.size == 60) { "The science UTME combination requires 60 English questions." }
        require(all.size == 180) { "The science UTME combination must contain exactly 180 questions." }
        require(all.map { it.id }.distinct().size == all.size) { "Question IDs must be unique across the combined paper." }
    }
}
