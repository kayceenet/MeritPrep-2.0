package com.kaycee.meritprep

enum class CellDivisionType { MITOSIS, MEIOSIS }

data class CellDivisionStage(
    val title: String,
    val chromosomeSetsPerCell: String,
    val chromosomeCountPerCell: Int,
    val chromatidCountPerCell: Int,
    val daughterCellsAtEnd: Int,
    val description: String,
    val keyEvent: String,
    val dnaContentPerCell: String
)

data class CellDivisionLesson(
    val type: CellDivisionType,
    val startingDiploidNumber: Int,
    val stages: List<CellDivisionStage>,
    val finalDaughterCellCount: Int,
    val finalChromosomeNumberPerDaughter: Int,
    val finalPloidy: String,
    val summary: String
)

object CellDivisionSimulation {
    fun lesson(type: CellDivisionType, diploidNumber: Int = 4): CellDivisionLesson {
        require(diploidNumber > 0 && diploidNumber % 2 == 0) {
            "The starting diploid chromosome number must be a positive even number."
        }
        require(diploidNumber <= Int.MAX_VALUE / 2) {
            "The starting chromosome number is too large for this model."
        }
        val haploid = diploidNumber / 2
        val stages = when (type) {
            CellDivisionType.MITOSIS -> listOf(
                stage("Interphase: before DNA replication", "Diploid (2n)", diploidNumber, diploidNumber, 0,
                    "The cell grows and carries out normal functions. Chromosomes have not yet been replicated.",
                    "Each chromosome consists of one chromatid.", "2C"),
                stage("After S phase: DNA replicated", "Diploid (2n)", diploidNumber, diploidNumber * 2, 0,
                    "DNA replication makes two sister chromatids per chromosome. The chromosome number does not double.",
                    "DNA amount and chromatid number double; centromere-counted chromosome number stays 2n.", "4C"),
                stage("Prophase", "Diploid (2n)", diploidNumber, diploidNumber * 2, 0,
                    "Chromosomes condense, the spindle forms and the nuclear envelope breaks down.",
                    "Each visible replicated chromosome has two sister chromatids.", "4C"),
                stage("Metaphase", "Diploid (2n)", diploidNumber, diploidNumber * 2, 0,
                    "Replicated chromosomes line up individually at the cell equator.",
                    "Spindle fibres attach to sister chromatids from opposite poles.", "4C"),
                stage("Anaphase", "4n total chromosomes before cytokinesis", diploidNumber * 2, diploidNumber * 2, 0,
                    "Centromeres divide and sister chromatids separate. Each separated chromatid is now counted as a chromosome.",
                    "Sister chromatids move to opposite poles.", "4C total in the dividing cell; 2C at each pole"),
                stage("Telophase and cytokinesis", "Diploid (2n) per daughter", diploidNumber, diploidNumber, 2,
                    "New nuclei form and the cytoplasm divides, producing two daughter cells.",
                    "Each daughter receives the same chromosome number as the parent cell.", "2C per daughter")
            )
            CellDivisionType.MEIOSIS -> listOf(
                stage("Interphase: before DNA replication", "Diploid (2n)", diploidNumber, diploidNumber, 0,
                    "The diploid germ cell grows before meiosis. DNA replication occurs once, before meiosis I.",
                    "Each chromosome initially has one chromatid.", "2C"),
                stage("After S phase: DNA replicated", "Diploid (2n)", diploidNumber, diploidNumber * 2, 0,
                    "Each chromosome now consists of two sister chromatids. There is no DNA replication between meiosis I and meiosis II.",
                    "Chromosome number remains 2n; chromatid number doubles.", "4C"),
                stage("Prophase I", "Diploid (2n)", diploidNumber, diploidNumber * 2, 0,
                    "Homologous chromosomes pair to form bivalents. Non-sister chromatids may exchange corresponding segments.",
                    "Synapsis and crossing over create new allele combinations.", "4C"),
                stage("Metaphase I", "Diploid (2n)", diploidNumber, diploidNumber * 2, 0,
                    "Homologous pairs line up at the equator in a random orientation.",
                    "Independent assortment contributes to genetic variation.", "4C"),
                stage("Anaphase I", "Diploid (2n) in the dividing cell", diploidNumber, diploidNumber * 2, 0,
                    "Homologous chromosomes move to opposite poles. Centromeres do not divide.",
                    "Homologous chromosomes separate; sister chromatids remain joined.", "4C total in the dividing cell; 2C at each pole"),
                stage("Telophase I and cytokinesis", "Haploid (n) per cell", haploid, haploid * 2, 2,
                    "The cell divides into two haploid cells. Each chromosome is still replicated.",
                    "Chromosome number is halved. No DNA replication follows.", "2C per haploid cell"),
                stage("Prophase II and metaphase II", "Haploid (n)", haploid, haploid * 2, 0,
                    "A new spindle forms in each cell and chromosomes line up individually at the equator.",
                    "The two haploid cells prepare for the second division.", "2C per haploid cell"),
                stage("Anaphase II", "2n total chromosomes before cytokinesis", haploid * 2, haploid * 2, 0,
                    "Centromeres divide and sister chromatids separate towards opposite poles.",
                    "This resembles the chromatid separation of mitotic anaphase.", "2C total in each dividing cell; 1C at each pole"),
                stage("Telophase II and cytokinesis", "Haploid (n) per daughter", haploid, haploid, 4,
                    "Both cells divide, producing four haploid daughter cells.",
                    "Each product has one chromosome set and is usually genetically distinct.", "1C per haploid product")
            )
        }
        return CellDivisionLesson(
            type, diploidNumber, stages,
            if (type == CellDivisionType.MITOSIS) 2 else 4,
            if (type == CellDivisionType.MITOSIS) diploidNumber else haploid,
            if (type == CellDivisionType.MITOSIS) "Diploid (2n)" else "Haploid (n)",
            if (type == CellDivisionType.MITOSIS)
                "One division produces two daughter cells. The chromosome number is maintained."
            else
                "Two divisions follow one round of DNA replication, producing four haploid cells. Crossing over and independent assortment generate variation."
        )
    }

    private fun stage(
        title: String,
        sets: String,
        chromosomes: Int,
        chromatids: Int,
        daughters: Int,
        description: String,
        keyEvent: String,
        dnaContent: String
    ) = CellDivisionStage(title, sets, chromosomes, chromatids, daughters, description, keyEvent, dnaContent)
}
