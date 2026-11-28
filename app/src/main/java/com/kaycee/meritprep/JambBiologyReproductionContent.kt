package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** JAMB Biology Topic 7: reproduction in unicellular organisms, plants and mammals. */
internal object JambBiologyReproductionContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Differentiate between asexual and sexual reproduction.",
            listOf(
                JambDeepPoint("Asexual reproduction",
                    """Asexual reproduction produces offspring from one parent without fusion of male and female gametes. The offspring are usually genetically very similar to the parent because they arise through mitotic cell division or related processes. Asexual reproduction can be rapid and does not require a mate, so it is useful when conditions are stable and a successful genotype is already present. However, low genetic variation can make a population vulnerable when the environment changes or a new disease appears.

                    In unicellular organisms, asexual reproduction may involve binary fission. A Paramecium duplicates its genetic material and organelles, then divides transversely into two daughter cells. In yeast, a small outgrowth forms on the parent cell, the nucleus divides and one daughter nucleus enters the bud; the bud grows and separates or may remain attached in a chain.""",
                    "Paramecium reproduces by binary fission; yeast commonly reproduces asexually by budding.",
                    "One parent, no gamete fusion, usually little genetic variation; identify the specific method and organism."),
                JambDeepPoint("Sexual reproduction",
                    """Sexual reproduction involves the production of haploid gametes and their fusion during fertilisation to form a diploid zygote. In animals, sperm and egg are the gametes; in flowering plants, the male gametes are delivered in a pollen tube to the embryo sac. Meiosis is involved in producing haploid gametes or spores in the life cycle, while fertilisation restores the diploid chromosome number.

                    Sexual reproduction generates genetic variation through meiosis, recombination and random fertilisation. Variation increases the chance that some offspring will survive environmental change, although reproduction may require more time and energy and often depends on finding a mate or a pollination partner.""",
                    "Fusion of a haploid sperm nucleus and a haploid egg nucleus produces a diploid zygote.",
                    "Sexual reproduction involves gametes and fertilisation; variation is a major advantage."),
                JambDeepPoint("Compare the two modes",
                    """Asexual reproduction is generally faster and can produce many offspring from one parent, but offspring are usually genetically similar. Sexual reproduction requires gamete formation and fertilisation and often takes more time, but it creates new gene combinations. Neither method is universally superior: the reproductive strategy that succeeds depends on the organism and environment.

                    Do not define sexual reproduction merely as reproduction by two parents. Some organisms can self-fertilise, and plants may have both male and female reproductive organs in one flower. The defining event is fusion of gametes, not the number of visible parents.""",
                    "A hermaphroditic organism may self-fertilise, but the process is still sexual because gametes fuse.",
                    "The key distinction is gamete fusion, not simply whether one or two adult organisms are present.")
            ),
            listOf("Asexual reproduction does not involve gamete fusion.", "Sexual reproduction is defined by fusion of gametes, not simply by two parents.", "Asexual offspring are usually similar, not necessarily perfectly identical in every circumstance."),
            listOf(
                JambDeepCheck("bio-repro-01","Which event defines sexual reproduction?",listOf("Budding","Binary fission","Fusion of gametes","Vegetative propagation"),2,"Sexual reproduction involves fusion of male and female gametes to form a zygote."),
                JambDeepCheck("bio-repro-02","A major advantage of asexual reproduction is that it",listOf("always creates high genetic variation","can produce offspring rapidly without a mate","requires fertilisation","always produces different offspring"),1,"Asexual reproduction can be rapid and requires only one parent."),
                JambDeepCheck("bio-repro-03","A hermaphroditic organism that self-fertilises is still reproducing sexually because",listOf("it has two bodies","gametes fuse","it produces buds","mitosis never occurs"),1,"The defining feature of sexual reproduction is gamete fusion, even when self-fertilisation occurs.")
            )
        ),
        JambDeepObjective(
            "Explain fission in Paramecium and budding in yeast as examples of asexual reproduction.",
            listOf(
                JambDeepPoint("Binary fission in Paramecium",
                    """Paramecium is a unicellular ciliate protozoan. Under favourable conditions it reproduces asexually by transverse binary fission. The micronucleus divides by mitosis, while the macronucleus elongates and divides amitotically in the usual textbook description. Organelles and cell contents are distributed, a transverse constriction develops across the cell and the parent separates into two daughter individuals. Each daughter receives the structures needed to function independently.

                    Fission increases population size quickly when food and environmental conditions are suitable. It is not the same as fragmentation of a multicellular body, and it is not budding because the cell divides into two broadly similar-sized daughter cells.""",
                    "A Paramecium divides across its short axis, producing two daughter cells.",
                    "Remember transverse binary fission and distinguish it from budding and longitudinal fission in other organisms."),
                JambDeepPoint("Budding in yeast",
                    """Yeast is a unicellular fungus. During budding, a small bulge develops on the parent cell. The nucleus divides by mitosis; one daughter nucleus moves into the bud. The bud enlarges, receives cytoplasm and organelles, and a septum or constriction forms at its base. It may detach as a new cell or remain attached to form a chain or cluster. A bud scar may remain on the parent cell.

                    Budding is unequal division: the daughter begins smaller than the parent. Under favourable conditions, yeast can multiply rapidly. Yeast also reproduces sexually under certain conditions by forming spores, but the familiar JAMB example of budding is asexual.""",
                    "A small daughter cell develops from the side of a yeast cell and may separate after reaching a suitable size.",
                    "Budding produces an initially smaller outgrowth; one daughter nucleus enters the bud."),
                JambDeepPoint("Practical comparison",
                    """To compare these processes, identify the starting organism, the pattern of division and the relative size of the offspring. In Paramecium, the whole cell divides transversely into two similar-sized daughter cells. In yeast, a smaller bud grows from the parent and may detach. Both processes increase the number of individuals without gamete fusion.

                    In a microscope practical, use a prepared slide or safe educational image, adjust magnification, focus on the cell boundary and identify a constriction or bud. Do not claim that every observed round yeast cell is budding: look for an attached outgrowth and, where visible, a bud scar.""",
                    "A micrograph with a small outgrowth attached to a larger yeast cell is evidence of budding.",
                    "Use visible morphology to identify the process; do not infer reproduction from cell shape alone.")
            ),
            listOf("Paramecium undergoes transverse binary fission; yeast commonly buds.", "Budding is unequal at first; binary fission produces two broadly similar daughter cells.", "The yeast example in this syllabus is asexual budding, although yeast also has sexual stages."),
            listOf(
                JambDeepCheck("bio-repro-04","Paramecium commonly reproduces asexually by",listOf("budding","transverse binary fission","pollination","fragmentation of hyphae"),1,"Paramecium divides transversely into two daughter cells by binary fission."),
                JambDeepCheck("bio-repro-05","During budding in yeast, the daughter cell",listOf("forms as a small outgrowth on the parent","develops from a fertilised ovule","is always larger than the parent","forms by pollination"),0,"A bud develops as a small outgrowth; a daughter nucleus enters and the bud grows."),
                JambDeepCheck("bio-repro-06","Which observation best identifies budding in yeast?",listOf("Two equal cells separated by a transverse furrow","A small outgrowth attached to a larger cell","A pollen tube entering an ovule","A seed coat splitting"),1,"An attached small outgrowth is characteristic of yeast budding.")
            )
        ),
        JambDeepObjective(
            "Apply natural vegetative propagation in crop production and multiplication.",
            listOf(
                JambDeepPoint("Vegetative propagation using stems",
                    """Natural vegetative propagation is a form of asexual reproduction in which new plants develop from vegetative organs rather than from seeds. Modified stems store food and contain buds that can grow into shoots. A potato tuber is a swollen underground stem; its eyes are buds that can sprout. Ginger spreads by a rhizome, a horizontal underground stem with nodes and buds. Onion forms a bulb with a short stem and fleshy food-storing leaf bases. Cocoyam and some other plants propagate through corms, which are swollen underground stems.

                    These structures allow crops to survive unfavourable seasons and regenerate when conditions improve. Because the offspring arise from vegetative tissue, they tend to retain the desirable characteristics of the parent variety.""",
                    "Potato tubers are planted with viable eyes; ginger rhizomes are cut into pieces containing buds.",
                    "Tuber, rhizome, bulb and corm are modified stems; identify the bud-bearing structure."),
                JambDeepPoint("Other natural vegetative structures",
                    """Runners or stolons are horizontal stems that grow along the soil surface and form new plantlets at nodes; strawberry is a familiar example. Suckers are shoots arising from the base or underground parts of a parent plant; banana and plantain produce suckers. Some plants form plantlets on leaf margins, as in Bryophyllum (Kalanchoe), where small buds develop into miniature plants. Some grasses spread by stolons or rhizomes.

                    The new plant becomes independent after roots and shoots develop. These structures are useful in agriculture because they multiply a selected plant quickly and can establish a crop without waiting for seed production. However, vegetative propagation can also transfer pathogens from an infected parent plant to its offspring.""",
                    "Strawberry spreads by runners; banana is commonly multiplied by suckers; Bryophyllum forms plantlets on leaf margins.",
                    "Learn the plant example and the vegetative organ from which the new individual develops."),
                JambDeepPoint("Agricultural value and limitations",
                    """Vegetative propagation is useful when a crop has poor or unreliable seed production, when seedlings do not retain the desired variety, or when farmers need plants to mature and fruit earlier. It can preserve desirable traits such as fruit quality, yield and uniformity. It also allows multiplication of seedless crops such as some banana varieties. Farmers select healthy, disease-free planting materials and ensure that buds or growing points remain viable.

                    A major limitation is low genetic variation among the propagated plants. A disease or environmental stress that affects one clone may affect many plants similarly. Bulky tubers, rhizomes and suckers may also be difficult to store and transport. Certified disease-free planting materials and tissue culture can help reduce pathogen transfer and supply uniform seedlings.""",
                    "Farmers plant healthy cassava stem cuttings or banana suckers to establish crops with known characteristics.",
                    "State both advantages and limitations: rapid uniform multiplication, but low variation and possible disease transmission.")
            ),
            listOf("Potato tubers are stems because the eyes are buds; they are not roots.", "Onion bulb is a short stem with fleshy leaf bases.", "Vegetative offspring are usually clones and may inherit pathogens from the parent."),
            listOf(
                JambDeepCheck("bio-repro-07","The eyes of a potato tuber are",listOf("root hairs","buds","stomata","flowers"),1,"Potato tubers are modified stems; the eyes are buds capable of producing shoots."),
                JambDeepCheck("bio-repro-08","Strawberry commonly spreads naturally by",listOf("runners","bulbs","seeds only","corms"),0,"Runners are horizontal stems that form new plantlets at nodes."),
                JambDeepCheck("bio-repro-09","A major limitation of repeated vegetative propagation is",listOf("high genetic variation","possible transfer of pathogens and low genetic variation","inability to produce mature plants","mandatory gamete fusion"),1,"Clonal multiplication preserves traits but can also preserve infections and reduces genetic variation.")
            )
        ),
        JambDeepObjective(
            "Explain artificial vegetative propagation, including grafting, budding and layering, in agricultural practice.",
            listOf(
                JambDeepPoint("Cuttings and layering",
                    """A cutting is a piece of stem, leaf or root placed under suitable conditions so that it develops roots and shoots into a new plant. Stem cuttings are widely used in cassava, sugar cane, hibiscus and many ornamental plants. A viable cutting must contain appropriate nodes or buds and be kept in conditions that reduce excessive water loss while roots develop.

                    In layering, a branch is induced to form roots while it is still attached to the parent plant. In simple layering, a flexible branch is bent to the ground, a section is covered with moist soil and the tip remains exposed. Once roots develop, the rooted section is cut from the parent. Air layering encourages roots on an elevated stem branch wrapped with moist material. Layering is useful for plants whose cuttings root poorly.""",
                    "Cassava stem cuttings are planted with viable nodes; a guava branch may be air-layered to produce a rooted plant.",
                    "Cutting is detached before rooting; layering forms roots while the branch remains attached to the parent."),
                JambDeepPoint("Grafting",
                    """Grafting joins a shoot piece (the scion) from a desired plant to a rooted plant (the rootstock). The cambial regions of the scion and rootstock must be aligned so that their vascular tissues can unite as a graft union. The scion contributes the desired shoot, fruit or flower characteristics; the rootstock may contribute disease resistance, tolerance of soil conditions, vigour or size control. Grafting is common in fruit trees such as citrus, mango and avocado.

                    Successful grafting requires compatible plants, clean cuts, close contact between cambial tissues and protection against drying and infection. A graft is not fertilisation and does not mix the genetic material of the two plants into a hybrid embryo. The tissues unite and continue growing as one composite plant.""",
                    "A desirable citrus scion can be grafted onto a rootstock selected for disease resistance or soil tolerance.",
                    "Scion = desired shoot; rootstock = rooted base. Cambial contact is essential for a successful graft."),
                JambDeepPoint("Budding and comparison of methods",
                    """In horticultural budding, a single bud from a desired plant is inserted beneath the bark of a compatible rootstock and secured until the tissues unite. It is a form of grafting that uses one bud rather than a longer shoot piece. Rose and citrus varieties are commonly propagated by budding. Do not confuse horticultural budding with yeast budding: one is an artificial plant-propagation technique, while the other is a natural asexual cell-division process.

                    Artificial vegetative methods preserve desirable varieties and can combine useful rootstock and scion traits. They require skill, compatible plant material and careful aftercare. Grafting and budding usually require close cambial contact; layering requires the branch to remain attached until roots form; cuttings root after being separated.""",
                    "A single eye from a selected rose variety can be inserted into a rootstock by budding.",
                    "Distinguish horticultural budding from yeast budding and state when the new plant becomes independent.")
            ),
            listOf("Grafting joins a scion to a rootstock; it is not cross-pollination.", "In layering, roots form before the branch is detached.", "Horticultural budding uses a plant bud; yeast budding is cellular reproduction."),
            listOf(
                JambDeepCheck("bio-repro-10","The shoot piece joined to a rootstock during grafting is the",listOf("scion","radicle","cotyledon","stigma"),0,"The scion is the shoot piece from the desired variety."),
                JambDeepCheck("bio-repro-11","In layering, roots develop",listOf("after the branch is detached","while the branch remains attached to the parent","inside a pollen grain","only after fertilisation"),1,"Layering encourages roots to form while the branch remains attached."),
                JambDeepCheck("bio-repro-12","Horticultural budding differs from yeast budding because it",listOf("uses a single plant bud inserted into a rootstock","requires gamete fusion","occurs only in unicellular fungi","forms by binary fission"),0,"Plant budding is a propagation method using a bud joined to a rootstock.")
            )
        ),
        JambDeepObjective(
            "Relate the parts of a flower to their functions and explain pollination and fertilisation.",
            listOf(
                JambDeepPoint("Structure of a typical flower",
                    """A typical complete flower has four main whorls. Sepals form the calyx and protect the flower bud. Petals form the corolla and may attract pollinators through colour, scent and nectar guides. The male reproductive organs are stamens, each usually consisting of a filament and anther. The anther contains pollen sacs in which pollen grains develop. The female reproductive organ is the carpel or pistil, consisting of stigma, style and ovary. The ovary contains ovules attached to a placenta.

                    A flower may be bisexual (containing stamens and carpels) or unisexual (containing one sex of reproductive organ). A complete flower has all four whorls; an incomplete flower lacks one or more. Do not assume every flower is both complete and bisexual.""",
                    "A hibiscus flower has prominent petals, stamens and a stigma; maize bears separate male and female flowers on the same plant.",
                    "Identify each whorl and reproductive structure; ovules are inside the ovary, pollen develops in anthers."),
                JambDeepPoint("Pollination and pollen-tube growth",
                    """Pollination is the transfer of pollen grains from an anther to a stigma. Self-pollination occurs when pollen reaches the stigma of the same flower or another flower on the same plant; cross-pollination transfers pollen between flowers on different plants of the same species. Agents include wind, insects, birds, bats and water in some plants. Wind-pollinated flowers often have exposed anthers, feathery stigmas and abundant light pollen; insect-pollinated flowers often have showy petals, scent or nectar and sticky pollen.

                    After a compatible pollen grain lands on the stigma, it may germinate and form a pollen tube. The tube grows down the style towards an ovule, carrying the male gametes. Pollination is not fertilisation: pollen transfer occurs first, and fusion of gamete nuclei occurs later inside the ovule.""",
                    "A bee transfers pollen from an anther to a stigma; the pollen grain then germinates and produces a tube through the style.",
                    "Pollination = pollen transfer; fertilisation = fusion of gamete nuclei. State the agent and adaptations where relevant."),
                JambDeepPoint("Fertilisation and products",
                    """In flowering plants, the pollen tube enters the ovule, usually through the micropyle, and releases two male gametes into the embryo sac. One male gamete fuses with the egg cell to form a diploid zygote. The other fuses with the two polar nuclei (or the secondary nucleus) to form a typically triploid primary endosperm nucleus. This is double fertilisation, a characteristic feature of angiosperms.

                    The zygote develops into the embryo; the endosperm provides nourishment. The ovule develops into a seed and its integuments form the seed coat. The ovary develops into the fruit, while the ovary wall forms the pericarp. Other flower parts may wither or persist depending on the species. A seed is not the same as a fruit: the seed develops from the ovule, while the fruit develops mainly from the ovary.""",
                    "After fertilisation, a bean ovule becomes a seed and the ovary develops into the pod.",
                    "Remember the developmental relationships: ovule → seed; ovary → fruit; zygote → embryo; endosperm → food reserve.")
            ),
            listOf("Pollination is pollen transfer; fertilisation is gamete fusion.", "The ovule becomes the seed; the ovary becomes the fruit.", "Double fertilisation produces a zygote and endosperm in flowering plants."),
            listOf(
                JambDeepCheck("bio-repro-13","The pollen sacs of a typical flower are found in the",listOf("stigma","anther","ovary wall","style"),1,"The anther contains pollen sacs where pollen grains develop."),
                JambDeepCheck("bio-repro-14","The transfer of pollen from anther to stigma is called",listOf("fertilisation","germination","pollination","placentation"),2,"Pollination is the transfer of pollen from anther to stigma."),
                JambDeepCheck("bio-repro-15","In a flowering plant, the ovule normally develops into the",listOf("fruit","seed","petal","stigma"),1,"After fertilisation, the ovule develops into a seed; the ovary develops into the fruit.")
            )
        ),
        JambDeepObjective(
            "State advantages of cross-pollination and relate placentation and flower structure to fruit formation.",
            listOf(
                JambDeepPoint("Advantages and limitations of cross-pollination",
                    """Cross-pollination can increase genetic variation by combining alleles from different plants of the same species. It may produce offspring with greater adaptability, improved vigour or useful combinations of traits. It can reduce the expression of some harmful recessive alleles in a population and may help maintain variation in crops. Cross-pollination is often promoted by mechanisms such as separate male and female flowers, different maturation times of anthers and stigmas (dichogamy), spatial separation of reproductive parts (herkogamy) and self-incompatibility.

                    Cross-pollination depends on agents such as insects or wind and may be less reliable if pollinators are scarce or plants are widely separated. Self-pollination can provide reproductive assurance when mates or pollinators are limited, but tends to reduce genetic variation over generations.""",
                    "A bee moving between genetically different plants of the same species can promote cross-pollination and variation in offspring.",
                    "Advantages include variation and potential adaptability; do not claim every cross-pollinated offspring is automatically superior."),
                JambDeepPoint("Placentation patterns",
                    """Placentation describes how ovules are arranged within the ovary. In marginal placentation, ovules are attached along one margin of a single carpel, as in pea. In axile placentation, ovules are attached to a central axis in a multilocular ovary, as in tomato, hibiscus or orange. In parietal placentation, ovules are attached to the inner wall of a usually unilocular ovary, as in cucumber. In free-central placentation, ovules are borne on a free central column without septa reaching the ovary wall, as in Primula. In basal placentation, one or a few ovules are attached at the base of the ovary, as in sunflower.

                    The number and arrangement of carpels and locules help identify the pattern. Placentation is not the same as fruit type: it describes ovule attachment before fertilisation, while fruit categories describe how the fruit develops from the ovary or inflorescence.""",
                    "Pea has marginal placentation; tomato has axile; cucumber has parietal; sunflower has basal placentation.",
                    "Use the ovary cross-section: central axis, inner wall, free central column, base or one carpel margin."),
                JambDeepPoint("Simple, aggregate, multiple and succulent fruits",
                    """A simple fruit develops from the single ovary of one flower, whether that ovary has one carpel or fused carpels; tomato, mango and pea pod are examples. An aggregate fruit develops from several free carpels (apocarpous gynoecium) of one flower; the separate ovaries form a cluster of small fruitlets, as in strawberry, raspberry or custard apple. A multiple or composite fruit develops from the ovaries of many flowers in an inflorescence that fuse or form a collective structure, as in pineapple and jackfruit.

                    Succulent or fleshy fruits have a soft, juicy pericarp at maturity; examples include tomato, mango and orange. Succulent describes texture, not the number of flowers or ovaries involved. A fruit may be simple and succulent. Do not treat placentation and fruit type as interchangeable classifications.""",
                    "Tomato is a simple fleshy fruit; strawberry is aggregate; pineapple is multiple.",
                    "Simple = one flower/ovary; aggregate = several free ovaries in one flower; multiple = many flowers in an inflorescence; succulent = fleshy pericarp.")
            ),
            listOf("Cross-pollination can increase variation but does not guarantee that every offspring is better.", "Placentation is ovule arrangement inside the ovary; fruit type is a separate classification.", "A fruit can be both simple and succulent."),
            listOf(
                JambDeepCheck("bio-repro-16","A major advantage of cross-pollination is that it",listOf("always prevents fertilisation","increases genetic variation","produces only identical offspring","eliminates the need for pollen"),1,"Cross-pollination combines genetic material from different plants and can increase variation."),
                JambDeepCheck("bio-repro-17","In axile placentation, ovules are attached",listOf("to a central axis in a multilocular ovary","only to one margin of a carpel","to the outer surface of petals","at the base of a naked stem"),0,"Axile placentation has ovules attached to a central axis, typically with septa forming multiple locules."),
                JambDeepCheck("bio-repro-18","Pineapple is commonly classified as a",listOf("simple fruit","aggregate fruit","multiple fruit","dry indehiscent seed"),2,"Pineapple develops from an inflorescence; the fruits of many flowers form a multiple fruit.")
            )
        ),
        JambDeepObjective(
            "Identify male and female mammalian reproductive organs and relate their structures to their functions.",
            listOf(
                JambDeepPoint("Male reproductive system",
                    """The testes lie in the scrotum, where a temperature slightly below core body temperature supports sperm production. Seminiferous tubules are the sites of spermatogenesis. Sertoli cells support developing sperm, while Leydig cells between tubules produce testosterone. Sperm pass through the epididymis, where they mature and are stored, then travel through the vas deferens. Accessory glands add fluids: seminal vesicles contribute fructose-rich fluid, the prostate adds fluid that supports sperm function, and bulbourethral glands secrete mucus-like fluid for lubrication. The urethra carries semen out through the penis.

                    The scrotum, testes, ducts, glands and penis have different functions. The testes produce sperm and testosterone; the epididymis matures sperm; the vas deferens transports sperm; accessory glands contribute seminal fluid. Semen is sperm mixed with glandular secretions.""",
                    "Sperm are produced in seminiferous tubules, mature in the epididymis and travel through the vas deferens.",
                    "Trace the sperm pathway and distinguish sperm production from maturation and transport."),
                JambDeepPoint("Female reproductive system",
                    """The ovaries produce ova and secrete oestrogen and progesterone. During the ovarian cycle, a follicle develops and usually releases an oocyte at ovulation. The oviduct (Fallopian tube) receives the oocyte; fertilisation commonly occurs in its ampulla. Cilia and muscular contractions help move the oocyte or early embryo towards the uterus. The uterus has a thick muscular wall (myometrium) and an inner endometrium that changes during the menstrual cycle and supports implantation.

                    The cervix is the narrow lower part of the uterus and opens into the vagina. The vagina receives semen during intercourse, provides a passage for menstrual flow and forms the birth canal. The external genitalia protect the openings. The ovaries are not the site where the embryo normally implants; implantation occurs in the endometrium of the uterus.""",
                    "Fertilisation usually occurs in the oviduct; implantation occurs in the uterine endometrium.",
                    "Trace the route: ovary releases oocyte → oviduct → uterus; distinguish fertilisation from implantation."),
                JambDeepPoint("Gamete formation and hormonal control",
                    """Spermatogenesis occurs in the seminiferous tubules and produces haploid sperm from diploid germ cells through meiosis. Oogenesis begins before birth; primary oocytes remain arrested for long periods, and usually one secondary oocyte is released during each cycle after puberty. The secondary oocyte completes meiosis only if fertilisation occurs. Sperm are small and motile, with a flagellum and mitochondria in the midpiece; the ovum is larger, contains cytoplasm and nutrient reserves and is non-motile.

                    Follicle-stimulating hormone (FSH) supports follicle development in females and spermatogenesis in males. Luteinising hormone (LH) triggers ovulation and supports corpus luteum function in females; in males it stimulates Leydig cells to produce testosterone. Oestrogen helps rebuild the endometrium; progesterone maintains it for possible implantation.""",
                    "The LH surge triggers ovulation; progesterone from the corpus luteum helps maintain the uterine lining.",
                    "Know the site and outcome of gametogenesis and the broad roles of FSH, LH, oestrogen and progesterone.")
            ),
            listOf("Fertilisation usually occurs in the oviduct, not the uterus.", "Sperm mature in the epididymis; they are produced in seminiferous tubules.", "The endometrium is the uterine lining where implantation occurs."),
            listOf(
                JambDeepCheck("bio-repro-19","Sperm cells are produced in the",listOf("epididymis","seminiferous tubules","prostate gland","vas deferens"),1,"Spermatogenesis takes place in the seminiferous tubules of the testes."),
                JambDeepCheck("bio-repro-20","In humans, fertilisation most commonly occurs in the",listOf("uterus","vagina","oviduct","cervix"),2,"Fertilisation commonly occurs in the ampulla of the oviduct."),
                JambDeepCheck("bio-repro-21","The uterine lining where implantation normally occurs is the",listOf("myometrium","endometrium","perimetrium","cervix"),1,"The blastocyst implants in the endometrium of the uterus.")
            )
        ),
        JambDeepObjective(
            "Explain fertilisation, embryonic development, maternal influences and modern methods of regulating reproduction.",
            listOf(
                JambDeepPoint("Fertilisation and early development",
                    """Fertilisation is the fusion of a haploid sperm nucleus with a haploid secondary oocyte nucleus to form a diploid zygote. In humans, sperm capacitation and acrosomal enzymes help sperm penetrate the egg coverings. Fusion triggers changes that help prevent polyspermy. The zygote undergoes repeated mitotic divisions called cleavage, forming a morula and then a blastocyst. The blastocyst reaches the uterus and implants in the endometrium.

                    The inner cell mass forms the embryo, while the trophoblast contributes to extra-embryonic tissues including the fetal part of the placenta. The placenta allows exchange of oxygen, nutrients and wastes between maternal and fetal blood without normally mixing the two blood supplies directly. The umbilical cord connects the fetus to the placenta.""",
                    "A fertilised human egg divides into a morula, then a blastocyst, which implants in the uterine lining.",
                    "Fertilisation forms a zygote; cleavage forms a blastocyst; implantation occurs in the endometrium."),
                JambDeepPoint("Embryonic and fetal development",
                    """After implantation, the embryo undergoes gastrulation and organ formation. The three primary germ layers are ectoderm, mesoderm and endoderm. Ectoderm forms structures including the nervous system and epidermis; mesoderm forms muscles, bones, blood and many internal organs; endoderm forms the lining of the digestive and respiratory tracts and associated organs. Development proceeds from an early embryo to a fetus as organs grow and mature.

                    The placenta performs exchange and has endocrine functions, producing hormones such as human chorionic gonadotropin (hCG) early in pregnancy and later supporting progesterone and oestrogen production. The amnion surrounds the embryo/fetus in amniotic fluid, which cushions against mechanical shock and allows movement. The placenta is not a complete barrier: some drugs, alcohol, nicotine and infectious agents can cross it.""",
                    "Amniotic fluid cushions the developing fetus; the placenta transfers oxygen and nutrients and removes fetal wastes.",
                    "Relate each extra-embryonic structure to its function and remember that the placenta is selective, not an absolute barrier."),
                JambDeepPoint("Maternal health, nutrition and harmful exposures",
                    """Maternal nutrition supplies energy and materials for fetal growth. Adequate folate before conception and in early pregnancy reduces the risk of neural tube defects; iron supports maternal blood expansion and fetal development; iodine supports thyroid hormone production and brain development; protein and other micronutrients support tissue formation. Poor nutrition, severe illness and uncontrolled chronic disease can impair development and pregnancy outcomes.

                    Alcohol can cause fetal alcohol spectrum disorders; smoking and nicotine exposure increase risks including low birth weight and placental complications. Some medicines and recreational drugs can harm embryonic development, but the risk depends on the substance, dose and timing. Pregnant people should not stop prescribed treatment abruptly; they should seek qualified clinical advice about medication safety. The JAMB point is to understand that indiscriminate drug use and harmful exposures can interfere with normal development.""",
                    "Folate is particularly important before conception and during early pregnancy because the neural tube forms very early.",
                    "Explain the link between maternal exposure and developmental risk without claiming every medicine is harmful or advising unsupervised discontinuation."),
                JambDeepPoint("IVF and birth-control methods",
                    """In vitro fertilisation (IVF) is an assisted reproductive technique in which eggs are collected from the ovaries and fertilised with sperm in a laboratory. One or more resulting embryos may be transferred into the uterus. IVF can help some people with infertility, but success depends on age, cause of infertility, embryo quality and clinical factors; it does not guarantee pregnancy. Other assisted methods may include intracytoplasmic sperm injection (ICSI), in which a sperm is injected into an egg.

                    Birth-control methods prevent pregnancy in different ways. Barrier methods such as condoms reduce the chance of sperm reaching the egg and condoms also reduce transmission of many sexually transmitted infections. Hormonal methods may suppress ovulation or alter cervical mucus and the endometrium. Intrauterine devices act within the uterus; copper IUDs impair sperm function and hormonal IUDs thicken cervical mucus and thin the endometrium. Natural methods rely on identifying fertile periods and avoiding unprotected intercourse during them. Sterilisation is intended to be permanent. No method except abstinence is completely effective, and condoms are the method in this list that also reduces STI transmission.""",
                    "During IVF, fertilisation occurs in a laboratory before an embryo is transferred to the uterus; a condom acts as a barrier method.",
                    "Describe the basic principle of IVF and distinguish contraception from protection against sexually transmitted infections.")
            ),
            listOf("Fertilisation is not implantation; fertilisation usually occurs in the oviduct and implantation in the uterus.", "The placenta allows exchange but is not an absolute barrier to harmful substances.", "IVF does not guarantee pregnancy; condoms also reduce STI transmission."),
            listOf(
                JambDeepCheck("bio-repro-22","The immediate product of fertilisation is a",listOf("blastocyst","zygote","fetus","placenta"),1,"Fusion of haploid gamete nuclei produces a diploid zygote."),
                JambDeepCheck("bio-repro-23","The structure that cushions the developing fetus is the",listOf("amnion and amniotic fluid","vas deferens","epididymis","corpus luteum only"),0,"Amniotic fluid within the amnion cushions the fetus and permits movement."),
                JambDeepCheck("bio-repro-24","Which birth-control method also reduces transmission of many sexually transmitted infections?",listOf("Copper IUD","Condom","Calendar method","Hormonal implant"),1,"Condoms provide a physical barrier that reduces pregnancy risk and transmission of many STIs.")
            )
        )
    )

    fun forTopic(topicId:String):List<JambDeepObjective> =
        if(topicId=="bio-reproduction") objectives else emptyList()
}

@Composable
internal fun FlowerStructureAndPollinationLab() {
    var part by remember { mutableStateOf("Anther") }
    val function = when (part) {
        "Sepal" -> "Protects the developing flower bud before it opens."
        "Petal" -> "Often attracts pollinators through colour, scent and nectar guides."
        "Anther" -> "Contains pollen sacs where pollen grains develop."
        "Stigma" -> "Receives pollen; a compatible pollen grain may germinate here."
        "Style" -> "Connects stigma to ovary and provides a pathway for pollen-tube growth."
        else -> "Contains ovules; after fertilisation the ovary develops into the fruit."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("FLOWER DISSECTION PRACTICAL", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Identify each floral part", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Select a structure, state its function and connect it to pollination or seed and fruit formation.")
            listOf("Sepal","Petal","Anther","Stigma","Style","Ovary").forEach {
                FilterChip(selected = part == it, onClick = { part = it }, label = { Text(it) })
            }
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(part, fontWeight = FontWeight.Bold)
                    Text(function)
                }
            }
            Text("Practical task: use a safe fresh flower such as hibiscus or lily. Remove the whorls carefully, observe anther and stigma with a hand lens, then cut the ovary lengthwise to locate ovules. Draw and label what you observe. Do not taste or ingest plant material.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
internal fun FertilisationAndDevelopmentSequenceLab() {
    var stage by remember { mutableIntStateOf(0) }
    val stages = listOf(
        "Fertilisation: sperm and secondary oocyte nuclei fuse in the oviduct, forming a diploid zygote.",
        "Cleavage: the zygote undergoes repeated mitotic divisions without a large increase in overall size, forming a morula.",
        "Blastocyst: a fluid-filled structure forms with an inner cell mass and outer trophoblast.",
        "Implantation: the blastocyst attaches to and embeds in the endometrium of the uterus.",
        "Embryonic development: germ layers form and organs begin developing; the placenta and membranes support exchange and protection.",
        "Fetal development: organs grow and mature until birth."
    )
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("MAMMALIAN DEVELOPMENT MODEL", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Follow development in sequence", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Move through the stages and distinguish fertilisation, cleavage and implantation.")
            Text("Stage ${stage + 1} of ${stages.size}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            LinearProgressIndicator(progress = (stage + 1) / stages.size.toFloat(), modifier = Modifier.fillMaxWidth())
            Text(stages[stage], style = MaterialTheme.typography.bodyLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { stage = (stage - 1).coerceAtLeast(0) }, enabled = stage > 0) { Text("Previous") }
                Button(onClick = { stage = (stage + 1).coerceAtMost(stages.lastIndex) }, enabled = stage < stages.lastIndex) { Text("Next") }
                TextButton(onClick = { stage = 0 }) { Text("Reset") }
            }
        }
    }
}
