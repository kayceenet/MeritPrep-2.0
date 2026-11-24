package com.kaycee.meritprep

/** Eight instructional units derived from the three JAMB Use of English sections. Draft content pending review. */
object EnglishCourse {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "eng-comprehension-strategies", "Comprehension strategies", "ENG",
            "Comprehension means understanding what a writer says, how the ideas connect and what the writer expects the reader to infer. A strong reader does more than locate repeated words: the reader identifies the main point, follows the argument and uses evidence from the passage to support an answer.",
            listOf(
                "Identify the main idea and supporting details",
                "Distinguish stated information from inference",
                "Use context to interpret words and expressions",
                "Recognise tone, attitude and purpose",
                "Support answers with evidence from the passage"
            ),
            listOf(
                "Read for the whole meaning" to "Read the passage once to understand its subject and overall direction before attempting individual questions. Notice the topic of each paragraph and how the paragraphs connect. A sentence may be true in isolation but still fail to answer the question being asked.",
                "Main idea and supporting details" to "The main idea is the central point the writer develops. Supporting details explain, illustrate or provide evidence for it. Topic sentences often introduce a paragraph's main point, but the main idea may also be implied or developed across several sentences.",
                "Stated information and inference" to "A stated detail appears directly in the passage. An inference is a conclusion supported by clues in the passage, even though the writer does not state it word for word. A good inference must be reasonable and text-based; it must not depend on unsupported personal assumptions.",
                "Vocabulary in context" to "Use the surrounding sentence and paragraph to interpret an unfamiliar word. Check its grammatical role, the tone of the passage and whether the writer uses the word literally or figuratively. Choose the option that preserves the meaning in that particular context, not merely a dictionary meaning that fits another context.",
                "Tone, attitude and purpose" to "Tone is the writer's manner or feeling, such as critical, sympathetic, humorous or formal. Attitude is the writer's position towards the subject. Purpose describes what the writer is trying to do, such as inform, explain, persuade or entertain. Support each answer with wording or ideas from the passage.",
                "Answering efficiently" to "Read the question carefully and identify whether it asks for a detail, inference, meaning, tone or purpose. Eliminate options contradicted by the passage. Avoid options that introduce information not present in the text or make a statement stronger than the evidence allows."
            ),
            "A conclusion that is not stated directly but is supported by clues in a passage is an:",
            listOf("Quotation", "Inference", "Heading", "Footnote"), 1,
            "An inference is a reasonable conclusion drawn from evidence in the passage."
        ),
        LearningTopic(
            "eng-passage-types", "Passage types and writer's purpose", "ENG",
            "JAMB comprehension passages may describe, narrate, explain or persuade. Recognising the type of passage helps you follow its structure and identify the writer's purpose. A passage can combine features, but one purpose is usually dominant.",
            listOf(
                "Distinguish descriptive, narrative and expository writing",
                "Identify argumentation and persuasion",
                "Recognise sequence, comparison and cause-effect organisation",
                "Identify the writer's purpose and intended audience",
                "Interpret transitions and paragraph relationships"
            ),
            listOf(
                "Description" to "Descriptive writing creates a clear picture of a person, place, object or experience. It often uses sensory details, precise adjectives and spatial organisation. Ask what is being described and which details create the picture.",
                "Narration" to "Narrative writing recounts events in a sequence. It commonly includes characters, setting, conflict and development. Chronological order is frequent, but a writer may use flashbacks or begin near the end of an event.",
                "Exposition" to "Expository writing explains a topic or gives information. It may define terms, classify ideas, compare alternatives or explain causes and effects. Look for facts, examples, definitions and logical connections.",
                "Argumentation and persuasion" to "Argumentative writing presents a position and supports it with reasons or evidence. Persuasive writing also seeks to influence the reader's beliefs or actions. Identify the claim, supporting reasons, evidence and any opposing view the writer addresses.",
                "Organisation and transitions" to "Words such as however, therefore, although, consequently and in contrast signal relationships between ideas. They may introduce contrast, result, concession or comparison. Use these links to follow the writer's reasoning.",
                "Purpose and audience" to "The intended audience affects vocabulary, detail and tone. A writer addressing specialists may use technical terms; a writer addressing the general public may explain them. Do not confuse the subject of a passage with the purpose for which it was written."
            ),
            "A passage that explains how a process works and defines its stages is mainly:",
            listOf("Narrative", "Expository", "Descriptive", "Dramatic dialogue"), 1,
            "Expository writing explains information, concepts or processes."
        ),
        LearningTopic(
            "eng-summary-synthesis", "Summary and synthesis", "ENG",
            "A summary presents the essential ideas of a longer text in fewer words. Synthesis combines related information from different parts of a passage into one coherent statement. Both require accurate understanding, selection of relevant points and clear expression in your own words.",
            listOf(
                "Identify essential points and distinguish examples from main ideas",
                "Remove repetition and unnecessary detail",
                "Paraphrase without changing meaning",
                "Combine related points into a concise statement",
                "Maintain grammatical accuracy and logical sequence"
            ),
            listOf(
                "Find the essential points" to "Identify the central claim and the information necessary to explain it. Examples, illustrations, repeated statements and decorative descriptions may support an idea without needing to appear in a short summary.",
                "Avoid adding personal opinion" to "A summary reports what the writer says. Do not insert your own judgement, outside information or a conclusion that the passage does not support. Preserve the writer's intended meaning and important qualifications.",
                "Paraphrase accurately" to "Use your own sentence structure and suitable vocabulary while preserving the original meaning. Replacing a word with an unrelated synonym can distort the point. Keep technical terms where changing them would reduce accuracy.",
                "Synthesis of ideas" to "Synthesis combines distinct pieces of information into a complete whole. Group points that express the same idea and state their shared meaning concisely. Do not simply copy separate sentences one after another.",
                "Conciseness and coherence" to "Remove repeated ideas, unnecessary modifiers and examples that do not answer the question. Arrange the remaining points logically and use appropriate connectors. A concise answer must still be complete and grammatical.",
                "Final checks" to "Check that every required point is included, no new idea has been introduced, pronouns have clear references and the response follows the requested word limit. Read the summary once without the passage to confirm that it makes sense."
            ),
            "Which action best improves a summary?",
            listOf("Copy every example", "Add personal opinions", "Combine repeated ideas and remove unnecessary detail", "Change the writer's conclusion"), 2,
            "A good summary preserves essential meaning while removing repetition and unnecessary detail."
        ),
        LearningTopic(
            "eng-vocabulary-context", "Lexis: synonyms, antonyms, homonyms and idioms", "ENG",
            "Lexis concerns vocabulary and the meanings of words and expressions. JAMB tests how words work in context, including similar and opposite meanings, homonyms, figurative language and formal idioms. The correct answer depends on the sentence and the variety of English being tested.",
            listOf(
                "Choose synonyms that preserve meaning in context",
                "Identify antonyms and distinguish degrees of opposition",
                "Distinguish homonyms and homophones",
                "Interpret figurative expressions and idioms",
                "Use formal Standard British English expressions appropriately"
            ),
            listOf(
                "Synonyms" to "Synonyms have similar meanings, but they are not always interchangeable. Select the word that fits the context, tone and grammatical role. For example, brief may mean short in duration or concise in expression; the sentence determines the intended sense.",
                "Antonyms" to "Antonyms express opposite meanings. Some opposites are gradable, such as hot and cold; others are complementary, such as present and absent. Relational opposites, such as teacher and pupil, describe opposite roles in the same relationship.",
                "Homonyms and homophones" to "Homophones sound alike but have different meanings or spellings, such as pair and pear. Homographs share spelling but may differ in pronunciation or meaning, such as lead (a metal) and lead (to guide). Read the full sentence before selecting an interpretation.",
                "Ordinary and figurative meaning" to "A word is used literally when it has its direct sense. Figurative language uses words imaginatively to create emphasis or comparison. A metaphor describes one thing as another; a simile makes an explicit comparison using like or as; personification gives human qualities to non-human things.",
                "Idioms" to "An idiom is an established expression whose meaning cannot always be worked out from the individual words. Use the accepted meaning in context and prefer formal Standard British English expressions, as required by the syllabus. Avoid inventing a literal interpretation.",
                "Word choice in context" to "Check whether the option fits the sentence's meaning, grammar and register. A word may be a synonym in one context but not another. Pay attention to collocations: words that naturally occur together, such as make a decision and heavy rain."
            ),
            "The expression 'to break the ice' means to:",
            listOf("Destroy frozen water", "Begin a friendly conversation in an awkward situation", "End a relationship", "Make a promise"), 1,
            "The idiom means to reduce social tension and make people feel more comfortable."
        ),
        LearningTopic(
            "eng-grammar-structures", "Word classes, clauses and sentence patterns", "ENG",
            "Grammar describes how words and groups of words combine to form meaningful sentences. Knowing word classes, clause functions and sentence patterns helps you identify errors and choose expressions that communicate clearly.",
            listOf(
                "Identify nouns, pronouns, verbs, adjectives, adverbs and other word classes",
                "Distinguish phrases, clauses and sentence types",
                "Identify subject, verb, object and complement patterns",
                "Apply subject-verb agreement and concord",
                "Recognise tense, aspect, mood, degree and question tags"
            ),
            listOf(
                "Word classes" to "Nouns name people, places, things or ideas; pronouns replace noun phrases; verbs express actions or states; adjectives modify nouns; adverbs modify verbs, adjectives or other adverbs. Prepositions show relationships, conjunctions link units and interjections express reactions. A word's class depends on how it functions in the sentence.",
                "Phrases and clauses" to "A phrase is a group of words functioning as a unit without a finite verb structure. A clause contains a subject and a verb. An independent clause can stand as a sentence; a dependent clause relies on another clause. Identify the clause before deciding how punctuation and conjunctions should be used.",
                "Sentence patterns" to "Common patterns include subject–verb (S–V), subject–verb–object (S–V–O), subject–verb–complement (S–V–C) and subject–verb–indirect object–direct object (S–V–IO–DO). The verb determines which elements are required to complete the meaning.",
                "Agreement and concord" to "The verb agrees with the grammatical subject, not necessarily the nearest noun. Singular subjects take singular verb forms and plural subjects take plural forms. In phrases such as the list of items, the head noun list controls agreement. Indefinite pronouns and collective nouns require attention to the intended meaning and accepted usage.",
                "Tense, aspect and mood" to "Tense locates an action in time; aspect describes how the action unfolds, such as whether it is complete or continuing. Mood expresses the speaker's attitude, such as statements, commands or hypothetical situations. Use time expressions and context to select the appropriate form.",
                "Degree and question tags" to "Adjectives and adverbs may have positive, comparative and superlative forms: tall, taller, tallest. Question tags normally use an auxiliary verb and a pronoun, with polarity usually opposite to the main clause: She is ready, isn't she? Match the auxiliary and subject carefully."
            ),
            "Choose the correct verb: 'Neither of the boys ___ present.'",
            listOf("are", "were", "is", "have"), 2,
            "Neither is treated as singular in this construction, so the correct verb is is."
        ),
        LearningTopic(
            "eng-usage-mechanics", "Punctuation, spelling and sentence completion", "ENG",
            "Mechanics make written English clear and readable. Punctuation shows how ideas are grouped, spelling distinguishes words and sentence completion tests whether a word or expression fits the grammar and meaning of the whole sentence.",
            listOf(
                "Use full stops, commas, apostrophes, colons and semicolons correctly",
                "Distinguish common spelling and homophone errors",
                "Complete sentences using grammatical and contextual clues",
                "Recognise ordinary, figurative and idiomatic usage",
                "Apply formal Standard British English conventions"
            ),
            listOf(
                "Full stops and commas" to "A full stop ends a complete declarative sentence. Commas separate items in a list, mark some introductory elements and separate clauses where grammar permits. A comma should not be used to join two independent sentences without a suitable conjunction or stronger punctuation.",
                "Apostrophes" to "An apostrophe can show possession or omitted letters. The girl's book means the book belonging to one girl; the girls' books means books belonging to several girls. Do not use an apostrophe to form an ordinary plural.",
                "Colons and semicolons" to "A colon can introduce an explanation, list or quotation after a complete clause. A semicolon can link closely related independent clauses or separate complex list items. Use punctuation to clarify the relationship between ideas.",
                "Spelling and commonly confused words" to "Check words that sound alike but differ in spelling and meaning, such as their, there and they're; its and it's; and advice and advise. The sentence context determines which form is correct.",
                "Sentence completion" to "Read the whole sentence before choosing an option. Check subject-verb agreement, tense, preposition, collocation and logical meaning. An option may fit the blank grammatically but still make the sentence illogical.",
                "Standard usage" to "Use formal, accepted Standard British English where the syllabus requires it. Avoid slang and expressions that are unsuitable for formal writing. In idiom questions, select the established expression that fits the context."
            ),
            "Which sentence uses the apostrophe correctly?",
            listOf("The girls bag is missing.", "The girl's bag is missing.", "The girls's bag is missing.", "The girl bag's is missing."), 1,
            "The singular possessive girl's shows that the bag belongs to one girl."
        ),
        LearningTopic(
            "eng-oral-sounds", "Oral forms: vowels, consonants and rhymes", "ENG",
            "Oral forms test how spoken English sounds are produced and distinguished. The spelling of a word does not always tell you its pronunciation. Practise listening to sounds, identifying their positions and comparing words that rhyme or sound alike.",
            listOf(
                "Distinguish monophthongs and diphthongs",
                "Identify vowel quality and length",
                "Distinguish consonant sounds and consonant clusters",
                "Recognise rhymes and homophones",
                "Use phonemic symbols as pronunciation guides"
            ),
            listOf(
                "Vowels" to "A vowel sound is produced without a major obstruction to the airflow through the vocal tract. A monophthong has a relatively steady vowel quality, while a diphthong glides from one vowel quality towards another within one syllable. Do not assume that the same letter always represents the same sound.",
                "Vowel quality and length" to "Vowels differ in tongue position, lip shape and duration. The vowel in ship differs from the vowel in sheep in both quality and length in many standard varieties. Use phonemic transcription to distinguish sounds rather than relying only on spelling.",
                "Consonants" to "Consonants involve some obstruction or narrowing of airflow. They can be described by place of articulation, manner of articulation and voicing. For example, /p/ and /b/ are both bilabial plosives, but /p/ is voiceless and /b/ is voiced.",
                "Consonant clusters" to "A consonant cluster contains two or more consonant sounds next to one another without an intervening vowel, as in street or texts. Pronounce each required sound clearly; inserting an extra vowel can change the syllable pattern.",
                "Rhymes and homophones" to "Words rhyme when their stressed vowel and following sounds are the same, as in light and night. Homophones sound alike but have different meanings or spellings, such as flour and flower. A rhyme depends on sound, not on matching spelling.",
                "Listening and practice" to "Say the words aloud, compare the target sound and identify whether it occurs at the beginning, middle or end. Use a reliable pronunciation dictionary and practise minimal pairs, such as ship/sheep or fan/van, to hear the contrast."
            ),
            "Which pair consists of homophones?",
            listOf("cat and cut", "flour and flower", "ship and sheep", "thin and then"), 1,
            "Flour and flower have the same pronunciation but different meanings and spellings."
        ),
        LearningTopic(
            "eng-stress-intonation", "Word stress, intonation and emphatic stress", "ENG",
            "Stress and intonation help speakers organise information and communicate attitude. A change in stress can change which part of a sentence is emphasised, while intonation describes the pitch movement across an utterance.",
            listOf(
                "Identify stressed syllables in common words",
                "Distinguish strong and weak syllables",
                "Recognise common intonation patterns",
                "Identify emphatic stress in an utterance",
                "Explain how stress can change implied meaning"
            ),
            listOf(
                "Word stress" to "Word stress is the prominence given to one syllable in a word. It may involve greater force, length, pitch movement or vowel clarity. Dictionaries often mark the primary stressed syllable with a symbol before it. Stress placement can distinguish related words, such as REcord (noun) and reCORD (verb).",
                "Strong and weak syllables" to "A stressed syllable is usually more prominent; an unstressed syllable is often shorter and may contain a reduced vowel such as schwa /ə/. Do not pronounce every syllable with equal force. Correct stress supports intelligibility.",
                "Intonation" to "Intonation is the pattern of pitch movement across a phrase or sentence. Falling intonation is common in statements and some wh-questions; rising intonation is common in many yes/no questions. Context and speaker intention can alter these patterns.",
                "Emphatic stress" to "Emphatic stress places special prominence on a word to correct, contrast or highlight information. In 'I asked for the BLUE file', blue is emphasised to distinguish it from another colour. Moving the stress to I, asked or file changes the implied contrast.",
                "Meaning and context" to "Stress may signal correction, surprise, contrast or insistence. Listen for the word receiving the strongest prominence and ask what alternative the speaker is rejecting or highlighting. The intended meaning depends on the surrounding conversation.",
                "Practice strategy" to "Mark the stressed syllable in a word and the emphasised word in a sentence. Read the sentence aloud with different stress positions and explain how the implied meaning changes. Use audio models rather than guessing from spelling."
            ),
            "In the sentence 'I wanted the BLUE pen', the emphatic stress on BLUE highlights:",
            listOf("The speaker's identity", "The colour of the pen", "The time of the request", "The number of pens"), 1,
            "The stressed word BLUE contrasts the requested colour with other possible colours."
        )
    )
}
