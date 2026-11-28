package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** JAMB Biology Topic 9: nervous coordination, sense organs, hormonal control and homeostasis. */
internal object JambBiologyCoordinationContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Explain the structure and functions of the central nervous system in coordinating body activities.",
            listOf(
                JambDeepPoint("Central nervous system: brain and spinal cord",
                    """The central nervous system (CNS) consists of the brain and spinal cord. It receives information from receptors, integrates and interprets that information, and coordinates responses through motor pathways. The brain is protected by the skull, meninges and cerebrospinal fluid. The spinal cord is protected by the vertebral column and also contains pathways that carry impulses between the brain and the rest of the body.

                    The cerebrum is involved in conscious perception, memory, learning, language and voluntary movement. The cerebellum coordinates muscle activity, posture and balance. The medulla oblongata contains centres that regulate vital involuntary functions such as breathing, heart rate and blood vessel diameter. The hypothalamus contributes to temperature, hunger, thirst and endocrine regulation. These regions work together; avoid assigning every body function to one isolated brain part.""",
                    "When a person catches a ball, sensory information is processed and motor commands coordinate the muscles; the cerebellum helps refine timing and accuracy.",
                    "CNS = brain and spinal cord. Link each named brain region to its principal function."),
                JambDeepPoint("Grey matter, white matter and protection",
                    """Grey matter contains many neuron cell bodies, dendrites and synapses; white matter contains bundles of axons, many of which are myelinated. In the cerebrum, grey matter forms much of the outer cortex and white matter lies deeper. In the spinal cord, grey matter is centrally located in a butterfly-shaped region, surrounded by white matter. This arrangement is useful when interpreting diagrams, but the exact distribution differs among CNS regions.

                    The meninges are protective membranes around the brain and spinal cord. Cerebrospinal fluid cushions the CNS, provides buoyancy and helps maintain a stable chemical environment. The blood-brain barrier restricts the movement of many substances from blood into brain tissue, although it does not block every chemical or drug. Damage to different CNS regions produces different effects because functions are distributed across specialised but interconnected networks.""",
                    "A spinal cord cross-section shows central grey matter surrounded by white matter; a brain section shows cortical grey matter externally.",
                    "Identify grey and white matter by their main contents and recognise that their arrangement differs in brain and spinal cord."),
                JambDeepPoint("Coordination pathway and effectors",
                    """A coordinated response begins when a receptor detects a stimulus and transduces it into an electrical signal. Sensory neurones carry information towards the CNS. Interneurones and networks within the CNS integrate information, and motor neurones carry commands to effectors. Effectors are muscles or glands that produce the response. The response may be rapid and automatic, or it may involve conscious processing and voluntary control.

                    The CNS does not act alone: receptors and peripheral nerves connect it to the rest of the body. A sensory pathway carries information towards the CNS (afferent), while a motor pathway carries commands away from it (efferent). A lesion in the spinal cord can interrupt communication between the brain and regions below the injury, and may also affect reflexes at the damaged level.""",
                    "Touching a hot surface activates skin receptors; sensory impulses reach the spinal cord and motor output activates arm muscles.",
                    "Trace receptor → sensory pathway → CNS integration → motor pathway → effector.")
            ),
            listOf("The CNS consists of the brain and spinal cord; nerves outside them belong to the PNS.", "The cerebellum coordinates movement; the medulla regulates vital involuntary functions.", "Grey matter is rich in cell bodies and synapses; white matter is rich in axons."),
            listOf(
                JambDeepCheck("bio-coord-01","The central nervous system consists of the",listOf("brain and spinal cord","cranial and spinal nerves only","sense organs and muscles","heart and spinal nerves"),0,"The CNS is made up of the brain and spinal cord."),
                JambDeepCheck("bio-coord-02","Which brain region is especially important for coordination of posture and balance?",listOf("Cerebellum","Medulla oblongata","Pituitary gland","Cerebral ventricle"),0,"The cerebellum coordinates muscular activity, posture and balance."),
                JambDeepCheck("bio-coord-03","The medulla oblongata helps regulate",listOf("hair colour","breathing and heart rate","formation of pollen","bone length only"),1,"The medulla contains centres controlling vital involuntary functions such as breathing and cardiovascular activity.")
            )
        ),
        JambDeepObjective(
            "Identify the components and functions of the peripheral nervous system and explain transmission of nerve impulses.",
            listOf(
                JambDeepPoint("Peripheral nerves and neurone types",
                    """The peripheral nervous system (PNS) consists of nerves and ganglia outside the brain and spinal cord. Cranial nerves arise from the brain; spinal nerves arise from the spinal cord. The PNS connects receptors and effectors to the CNS. Sensory (afferent) neurones carry impulses from receptors towards the CNS. Motor (efferent) neurones carry impulses from the CNS to effectors. Relay neurones are located within the CNS and connect pathways.

                    The motor division includes the somatic nervous system, which controls skeletal muscles, and the autonomic nervous system, which regulates smooth muscle, cardiac muscle and glands. The autonomic system includes sympathetic and parasympathetic divisions. Their effects often oppose one another: sympathetic activity prepares the body for action, while parasympathetic activity supports rest, digestion and energy conservation.""",
                    "A spinal nerve is a mixed nerve because it contains sensory and motor fibres.",
                    "Afferent = towards CNS; efferent = away from CNS; cranial and spinal nerves are PNS structures."),
                JambDeepPoint("Structure of a neurone",
                    """A typical neurone has a cell body containing the nucleus, dendrites that receive signals, and an axon that conducts impulses away from the cell body. The axon may be surrounded by a myelin sheath formed by Schwann cells in many peripheral nerves. Gaps in the sheath are nodes of Ranvier. Myelin electrically insulates the axon and allows impulses to travel faster by saltatory conduction, in which the action potential is regenerated at nodes.

                    Sensory neurones often have a long dendron and a shorter axon, while motor neurones commonly have many dendrites and one long axon. Neurone shapes vary, so identify their functional direction and labelled parts rather than assuming every diagram has the same geometry. A nerve is a bundle of many axons enclosed in connective tissue, not a single neurone.""",
                    "A myelinated motor axon conducts impulses rapidly from the spinal cord to a skeletal muscle.",
                    "Know cell body, dendrites, axon, myelin sheath and nodes of Ranvier; myelin increases conduction speed."),
                JambDeepPoint("Action potentials and synaptic transmission",
                    """A resting neurone maintains a voltage difference across its membrane through selective permeability and the sodium-potassium pump. When a stimulus brings the membrane to threshold, voltage-gated sodium channels open and sodium ions enter, causing depolarisation. Sodium channels then inactivate and potassium channels open; potassium leaves, repolarising the membrane. A brief refractory period helps ensure one-way propagation. The action potential is regenerated along the axon and does not gradually fade like a passive signal.

                    At a chemical synapse, an action potential reaches the presynaptic terminal and triggers calcium entry. Synaptic vesicles release neurotransmitter into the synaptic cleft; the transmitter binds receptors on the postsynaptic membrane and changes its electrical state. The neurotransmitter is then removed by breakdown, diffusion or reuptake. Synapses allow one-way communication and integration, but transmission across them introduces a small delay.""",
                    "At a neuromuscular junction, acetylcholine is released from a motor neurone and binds receptors on the muscle membrane.",
                    "Depolarisation involves sodium entry; repolarisation mainly involves potassium exit; chemical synapses use neurotransmitters.")
            ),
            listOf("A nerve is a bundle of axons; a neurone is one specialised nerve cell.", "Afferent impulses travel towards the CNS; efferent impulses travel away.", "Myelin speeds conduction; neurotransmitters cross chemical synapses."),
            listOf(
                JambDeepCheck("bio-coord-04","A sensory neurone carries impulses",listOf("from the CNS to a muscle","from a receptor towards the CNS","from one gland to another","only within the brain"),1,"Sensory or afferent neurones carry information from receptors towards the CNS."),
                JambDeepCheck("bio-coord-05","The main effect of a myelin sheath is to",listOf("slow all impulses","increase the speed of impulse conduction","produce hormones","digest neurotransmitters"),1,"Myelin insulates the axon and allows faster saltatory conduction between nodes of Ranvier."),
                JambDeepCheck("bio-coord-06","During depolarisation of a typical neurone, there is a rapid influx of",listOf("sodium ions","calcium carbonate","large proteins","chloride-free water"),0,"Voltage-gated sodium channels open at threshold, allowing sodium ions to enter the neurone.")
            )
        ),
        JambDeepObjective(
            "Illustrate reflex actions and differentiate reflex, voluntary and conditioned responses.",
            listOf(
                JambDeepPoint("Reflex action and reflex arc",
                    """A reflex action is a rapid, automatic response to a stimulus. It helps protect the body and maintain posture. A typical spinal reflex arc includes a receptor, sensory neurone, relay neurone in the spinal cord, motor neurone and effector. The receptor detects the stimulus; the sensory neurone carries the impulse to the CNS; the motor neurone carries the response to a muscle or gland. The brain may receive information about the stimulus, but conscious decision-making is not required before the initial withdrawal response.

                    Some reflexes involve the brainstem rather than the spinal cord, such as aspects of blinking and pupil responses. Not every reflex arc contains the same number of synapses. A monosynaptic stretch reflex connects sensory and motor neurones directly in the spinal cord, while a withdrawal reflex is polysynaptic and uses interneurones.""",
                    "The knee-jerk reflex follows a tap on the patellar tendon: stretch receptors activate a spinal pathway that contracts the quadriceps.",
                    "Trace the reflex arc in order and identify the receptor and effector."),
                JambDeepPoint("Reflex versus voluntary action",
                    """A reflex action is involuntary, rapid and stereotyped; it occurs without conscious planning of the initial response. A voluntary action is consciously initiated or controlled and usually involves processing in the cerebral cortex. Both use nervous pathways and skeletal muscles may act as effectors. Reflexes are not necessarily simple or unimportant: they protect the body, stabilise posture and help regulate internal functions.

                    Withdrawal of a hand from a hot object is a protective spinal reflex. Choosing to pick up a cup is a voluntary action. The brain can modify many reflex responses, and a person may consciously suppress some reflexes for a short time, but the basic reflex pathway remains automatic.""",
                    "Blinking when an object approaches the eye is a protective reflex; deliberately raising a hand to answer a question is voluntary.",
                    "Reflex = automatic and rapid; voluntary = consciously initiated or directed."),
                JambDeepPoint("Conditioned reflexes and learning",
                    """A conditioned reflex is a learned response formed by association between a previously neutral stimulus and a stimulus that naturally produces a response. In classical conditioning, repeated pairing can cause the neutral stimulus to trigger the learned response. Pavlov's experiments showed dogs learning to salivate in response to a sound associated with food. Human examples include salivation at the smell or sound associated with a familiar meal, or learned automatic actions such as riding a bicycle after practice.

                    Conditioned responses are acquired through experience and can weaken if the association is no longer reinforced. They differ from unconditioned reflexes, which are present without learning, such as the knee jerk or withdrawal from a painful stimulus. Riding a bicycle involves learned motor coordination and is often used as an example of conditioned or acquired response, although complex skills also involve voluntary control and practice.""",
                    "A dog learns to salivate when a bell repeatedly predicts food; a learner develops automatic balance corrections while cycling.",
                    "Unconditioned reflexes are innate; conditioned responses are acquired through learning and association.")
            ),
            listOf("The reflex response can begin before conscious awareness.", "A reflex arc includes receptor, sensory pathway, CNS integration, motor pathway and effector.", "Conditioned responses are learned; the knee jerk is an unconditioned reflex."),
            listOf(
                JambDeepCheck("bio-coord-07","Which sequence best represents a simple reflex arc?",listOf("Effector → receptor → sensory neurone → brain","Receptor → sensory neurone → CNS → motor neurone → effector","Motor neurone → receptor → effector → CNS","CNS → receptor → sensory neurone → gland"),1,"A reflex arc carries information from receptor to CNS and then motor output to the effector."),
                JambDeepCheck("bio-coord-08","The knee-jerk response is an example of a",listOf("conditioned response","voluntary action","unconditioned reflex","hormonal response"),2,"The knee jerk is a rapid, innate stretch reflex mediated through the spinal cord."),
                JambDeepCheck("bio-coord-09","A response learned by associating a sound with food is",listOf("a conditioned response","a simple innate reflex","a tropism","a hormonal gland"),0,"A conditioned response is acquired through learning and association.")
            )
        ),
        JambDeepObjective(
            "Relate the structures of the skin, nose, tongue, eye and ear to the stimuli they detect and explain selected defects.",
            listOf(
                JambDeepPoint("Skin: touch, pressure, temperature and pain",
                    """The skin is a sense organ containing specialised receptors in the epidermis and dermis. Mechanoreceptors detect touch, pressure, vibration and skin stretch; thermoreceptors detect changes in temperature; nociceptors detect potentially damaging stimuli and contribute to pain. Different receptor endings occur at different depths and body regions, so sensitivity varies across the skin. Sensory impulses travel through peripheral nerves to the spinal cord and brain, where sensations are interpreted.

                    The skin also protects the body, reduces water loss and contributes to temperature regulation. It is not accurate to say that one single receptor detects every skin sensation. Receptor adaptation also differs: some receptors respond strongly to a change but reduce firing during a constant stimulus, while others continue signalling.""",
                    "Fingertips have high tactile sensitivity because of dense sensory innervation and small receptive fields.",
                    "Skin receptors detect touch, pressure, temperature and pain; receptor type and location influence sensitivity."),
                JambDeepPoint("Nose and tongue: chemical senses",
                    """Olfactory receptors are located in the olfactory epithelium high in the nasal cavity. Airborne chemicals dissolve in mucus and bind receptor proteins on sensory cilia. Signals travel through the olfactory nerve to the olfactory bulb and brain. The sense of smell contributes strongly to flavour perception and can detect many volatile chemicals. Nasal congestion can reduce smell because odorant molecules have difficulty reaching the receptors.

                    Taste receptors are grouped in taste buds, many of which occur in papillae on the tongue. Dissolved chemicals stimulate taste receptor cells. The main taste qualities commonly taught are sweet, salty, sour, bitter and umami. The tongue also has tactile and temperature receptors. Flavour depends on taste, smell, texture and temperature; it is not detected by taste buds alone.""",
                    "During a cold, food may seem less flavourful because reduced smell limits the combined perception of flavour.",
                    "Smell detects airborne chemicals at the olfactory epithelium; taste detects dissolved chemicals at taste buds."),
                JambDeepPoint("Eye: light detection and common defects",
                    """Light enters through the cornea, passes through the aqueous humour and pupil, and is focused by the lens through the vitreous humour onto the retina. The iris controls pupil size. The retina contains rods, which are highly sensitive in dim light and support monochrome vision, and cones, which provide colour vision and high visual acuity in bright light. The fovea has a high density of cones; the optic nerve carries signals from retinal ganglion cells to the brain.

                    Myopia (short-sightedness) occurs when distant images focus in front of the retina, commonly because the eyeball is too long or refractive power is too great; concave lenses help correct it. Hypermetropia (long-sightedness) occurs when near images tend to focus behind the retina; convex lenses help. Astigmatism involves unequal curvature of refractive surfaces and is corrected with cylindrical lenses. Cataract is clouding of the lens and may require surgery; it is not corrected simply by a concave or convex lens.""",
                    "A myopic student sees distant writing blurred; a concave lens diverges incoming light so the image focuses on the retina.",
                    "Know the pathway of light, rods versus cones, and the lens used for myopia and hypermetropia."),
                JambDeepPoint("Ear: hearing and balance",
                    """The external ear collects sound waves and directs them through the auditory canal to the tympanic membrane. Vibrations pass through the ossicles (malleus, incus and stapes) to the oval window, producing pressure waves in cochlear fluid. Hair cells in the organ of Corti convert mechanical vibration into nerve signals carried by the auditory nerve to the brain. The Eustachian tube helps equalise air pressure across the eardrum.

                    The semicircular canals detect rotational movements of the head; the utricle and saccule detect linear acceleration and head position relative to gravity. These vestibular structures contribute to balance and posture. Hearing loss may arise from damage to hair cells, auditory pathways or the sound-conduction system. Loud noise can damage hair cells and may cause permanent hearing loss; objects should never be inserted into the ear canal.""",
                    "The cochlea is essential for hearing; semicircular canals contribute to detection of rotational head movement.",
                    "Trace sound through the ear and distinguish cochlear hearing structures from vestibular balance structures.")
            ),
            listOf("The eye detects light; the ear detects sound and contributes to balance.", "Rods support dim-light vision; cones support colour and detailed vision.", "Myopia is corrected with concave lenses; hypermetropia with convex lenses."),
            listOf(
                JambDeepCheck("bio-coord-10","The receptor cells responsible for colour vision are",listOf("rods","cones","hair cells","olfactory cells"),1,"Cones provide colour vision and high acuity, especially in bright light."),
                JambDeepCheck("bio-coord-11","Myopia is commonly corrected using a",listOf("convex lens","concave lens","plane mirror","prism only"),1,"A concave lens diverges light so distant images can focus on the retina."),
                JambDeepCheck("bio-coord-12","The semicircular canals are mainly involved in",listOf("hearing pitch","balance and rotational movement","taste detection","smell detection"),1,"The semicircular canals detect rotational movements of the head and contribute to balance.")
            )
        ),
        JambDeepObjective(
            "Locate the major endocrine glands and relate their hormones to their functions.",
            listOf(
                JambDeepPoint("Pituitary, thyroid and parathyroid glands",
                    """The pituitary gland lies at the base of the brain and is connected to the hypothalamus. The anterior pituitary secretes hormones including growth hormone (GH), thyroid-stimulating hormone (TSH), adrenocorticotropic hormone (ACTH), FSH, LH and prolactin. The posterior pituitary stores and releases oxytocin and antidiuretic hormone (ADH), which are synthesised in the hypothalamus. The pituitary is often called the master gland, but its activity is controlled by the hypothalamus and feedback from target glands.

                    The thyroid gland lies in the neck in front of the trachea. It produces thyroxine (T4) and triiodothyronine (T3), which influence metabolic rate, growth and development. Thyroid C cells produce calcitonin, which can lower blood calcium in some contexts. The parathyroid glands are usually located on the posterior surface of the thyroid and secrete parathyroid hormone (PTH), which raises blood calcium by acting on bone, kidneys and indirectly the intestine.""",
                    "Low thyroid hormone can reduce metabolic rate; PTH helps restore blood calcium when it falls.",
                    "Pituitary regulates several endocrine functions; thyroid hormones influence metabolism; PTH raises blood calcium."),
                JambDeepPoint("Adrenal glands and pancreas",
                    """The adrenal glands sit above the kidneys. The adrenal cortex produces steroid hormones including cortisol, aldosterone and adrenal androgens. Cortisol helps regulate metabolism and the stress response; aldosterone increases sodium reabsorption and potassium secretion in the distal nephron, supporting blood volume and electrolyte balance. The adrenal medulla releases adrenaline and noradrenaline, which prepare the body for rapid action by increasing heart rate, changing blood flow and mobilising energy.

                    The pancreas has endocrine islets of Langerhans as well as exocrine digestive tissue. Beta cells secrete insulin, which lowers blood glucose by promoting uptake and storage. Alpha cells secrete glucagon, which raises blood glucose by promoting glycogen breakdown and glucose production, especially in the liver. Do not confuse the endocrine role of the pancreatic islets with the exocrine secretion of digestive enzymes into the duodenum.""",
                    "During a fight-or-flight response, adrenaline increases heart rate; after a meal, insulin helps lower blood glucose.",
                    "Adrenal cortex makes steroid hormones; adrenal medulla releases catecholamines; pancreatic islets regulate glucose."),
                JambDeepPoint("Gonads and hormone action",
                    """The testes are male gonads and produce testosterone, which supports sperm production, male reproductive function and secondary sexual characteristics. The ovaries are female gonads and produce oestrogens and progesterone. Oestrogens contribute to development of female secondary sexual characteristics and growth of the uterine lining; progesterone helps maintain the endometrium for possible pregnancy. FSH and LH from the anterior pituitary regulate gonadal activity.

                    Hormones are chemical messengers transported in blood or tissue fluid. A hormone affects target cells that possess suitable receptors; not every cell responds to every hormone. Steroid hormones commonly bind intracellular receptors and influence gene expression, while peptide hormones commonly bind cell-surface receptors and activate signalling pathways. The response depends on hormone concentration, receptor number and the physiological state of the target tissue.""",
                    "Testosterone supports male reproductive function; progesterone helps maintain the uterine lining after ovulation.",
                    "Identify the gonad, its hormone and a target function; hormones act on cells with matching receptors.")
            ),
            listOf("ADH is released by the posterior pituitary but synthesised in the hypothalamus.", "Insulin lowers blood glucose; glucagon raises it.", "Adrenal cortex and adrenal medulla produce different classes of hormones."),
            listOf(
                JambDeepCheck("bio-coord-13","Which hormone lowers blood glucose after a meal?",listOf("Glucagon","Adrenaline","Insulin","Thyroxine"),2,"Insulin promotes glucose uptake and storage, lowering blood glucose."),
                JambDeepCheck("bio-coord-14","Parathyroid hormone mainly acts to",listOf("lower blood calcium","raise blood calcium","lower body temperature","increase blood oxygen"),1,"PTH raises blood calcium through actions on bone and kidneys and indirectly the intestine."),
                JambDeepCheck("bio-coord-15","The adrenal medulla secretes",listOf("adrenaline","insulin","thyroxine","progesterone"),0,"The adrenal medulla releases adrenaline and noradrenaline during rapid stress responses.")
            )
        ),
        JambDeepObjective(
            "Explain the effects of auxins, gibberellins, cytokinins and ethylene on plant growth and development.",
            listOf(
                JambDeepPoint("Auxins",
                    """Auxins are plant growth regulators, with indole-3-acetic acid (IAA) being a major natural auxin. They are produced mainly in growing shoot tips and young tissues and are transported to other regions. Auxins promote cell elongation in shoots, contribute to apical dominance, influence root initiation and mediate tropic responses. Unequal auxin distribution causes differential growth: in shoots, the shaded side often elongates faster during phototropism; in roots, higher auxin concentrations can inhibit elongation.

                    Synthetic auxins are used in rooting powders and as selective herbicides at suitable doses. Their effects depend on concentration, tissue and species. Do not state that auxin always promotes growth in every tissue: a concentration that promotes shoot elongation may inhibit root elongation.""",
                    "A shoot bends towards one-sided light because auxin-driven elongation is greater on the shaded side.",
                    "Auxin: cell elongation, apical dominance, root initiation and tropic responses; effect depends on concentration and organ."),
                JambDeepPoint("Gibberellins and cytokinins",
                    """Gibberellins promote stem and internode elongation, can break dormancy and stimulate germination in some seeds by promoting enzyme production that mobilises stored food. They can influence flowering and fruit development in certain species. In cereal grains, gibberellin from the embryo stimulates the aleurone layer to produce enzymes such as amylase, which break down starch in the endosperm.

                    Cytokinins promote cell division and are produced in actively growing tissues, including roots and developing seeds. They interact with auxins in controlling shoot and root formation in tissue culture. Cytokinins can delay leaf senescence by maintaining some metabolic activity. Their effects depend on the balance with other hormones and the developmental stage of the plant.""",
                    "Gibberellin stimulates alpha-amylase production during cereal seed germination; cytokinins can delay yellowing of detached leaves.",
                    "Gibberellin: stem elongation and germination; cytokinin: cell division and delayed senescence."),
                JambDeepPoint("Ethylene and coordinated hormone action",
                    """Ethylene is a gaseous plant hormone involved in fruit ripening, leaf and flower senescence, abscission and responses to mechanical stress. It can accelerate ripening in climacteric fruits such as banana and tomato, promoting colour change, softening and conversion of stored starch to sugars. Commercial fruit handlers use ethylene or ethylene-releasing compounds to coordinate ripening, while ventilation and temperature control can slow it.

                    Plant hormones interact. Auxin from young leaves can inhibit abscission-zone activity; as auxin levels decline and ethylene sensitivity rises, leaves may detach. Hormone responses vary by tissue and species, and a single hormone rarely controls an entire developmental process alone. The correct examination approach is to connect each hormone to a characteristic effect and an example.""",
                    "Ethylene is used to ripen bananas after harvest; increased ethylene activity also contributes to leaf abscission.",
                    "Ethylene: fruit ripening, senescence and abscission; hormones interact and effects are context-dependent.")
            ),
            listOf("Auxin can inhibit root elongation at concentrations that promote shoot elongation.", "Gibberellins can promote alpha-amylase production during cereal germination.", "Ethylene is gaseous and promotes fruit ripening and abscission."),
            listOf(
                JambDeepCheck("bio-coord-16","The plant hormone most closely associated with fruit ripening is",listOf("Auxin","Ethylene","Cytokinin","Abscisic acid only"),1,"Ethylene promotes ripening in many climacteric fruits."),
                JambDeepCheck("bio-coord-17","Gibberellin promotes seed germination in cereals partly by stimulating",listOf("alpha-amylase production","stomatal closure only","pollen transfer","root cap formation"),0,"Gibberellin stimulates the aleurone layer to produce enzymes such as alpha-amylase."),
                JambDeepCheck("bio-coord-18","Cytokinins are especially associated with",listOf("cell division and delayed senescence","blood clotting","nerve conduction","urine formation"),0,"Cytokinins promote cell division and can delay leaf senescence.")
            )
        ),
        JambDeepObjective(
            "Explain how hormonal feedback contributes to homeostasis, including blood glucose and water balance.",
            listOf(
                JambDeepPoint("Homeostasis and negative feedback",
                    """Homeostasis is the regulation of the internal environment within limits that allow cells and organs to function. Variables include body temperature, blood glucose, water potential, ion concentrations and pH. A control system includes receptors or sensors, a coordinating centre and effectors. Negative feedback occurs when a response opposes the initial change, bringing the variable back towards its normal range. It does not mean that the value never changes; it means deviations trigger corrective responses.

                    Most homeostatic systems operate around a set point or acceptable range. Receptors detect a deviation, the control centre compares the information with the required range and effectors act. When the deviation decreases, the corrective response is reduced. Positive feedback is different: it amplifies a change, as in oxytocin-driven uterine contractions during labour, and is used in specific processes rather than routine homeostatic stabilisation.""",
                    "When body temperature rises, sweating increases heat loss; as temperature falls, the stimulus for sweating reduces.",
                    "Define homeostasis and explain negative feedback as a response that opposes the original change."),
                JambDeepPoint("Blood glucose regulation",
                    """After a carbohydrate-rich meal, blood glucose rises. Pancreatic beta cells detect the increase and release insulin. Insulin promotes glucose uptake by muscle and adipose tissue, glycogen synthesis in liver and muscle, and other storage pathways. Blood glucose then falls towards the normal range, reducing the stimulus for insulin release. When blood glucose falls, pancreatic alpha cells release glucagon, which promotes glycogen breakdown and glucose production in the liver.

                    Insulin and glucagon have opposing effects and form a negative-feedback system. Diabetes mellitus involves impaired insulin secretion, insulin action or both, leading to persistently elevated blood glucose if untreated. Do not confuse diabetes mellitus with diabetes insipidus, which is related to ADH and water balance.""",
                    "After a meal, insulin secretion rises; during fasting, glucagon helps maintain blood glucose.",
                    "Insulin lowers blood glucose; glucagon raises it; the pancreas is the coordinating endocrine organ."),
                JambDeepPoint("Water and salt balance: ADH and aldosterone",
                    """When blood water potential falls or osmotic concentration rises, osmoreceptors in the hypothalamus stimulate thirst and increase ADH release from the posterior pituitary. ADH makes collecting ducts more permeable to water, increasing water reabsorption and producing a smaller volume of more concentrated urine. When excess water is consumed, ADH release falls and a larger volume of dilute urine is produced. The kidneys adjust water output to help restore the internal environment.

                    Aldosterone from the adrenal cortex increases sodium reabsorption and potassium secretion in parts of the distal nephron. Water may follow sodium, helping regulate extracellular fluid volume and blood pressure. The renin-angiotensin-aldosterone system responds to reduced renal perfusion or sodium delivery. ADH mainly controls water permeability; aldosterone mainly controls sodium and potassium handling. Both contribute to fluid balance but act by different mechanisms.""",
                    "During dehydration, increased ADH helps conserve water; aldosterone supports sodium retention when blood volume or pressure is low.",
                    "ADH increases water reabsorption; aldosterone increases sodium reabsorption and potassium secretion."),
            ),
            listOf("Negative feedback opposes the initial deviation; it does not keep every variable perfectly constant.", "ADH is mainly about water permeability; aldosterone is mainly about sodium and potassium handling.", "Diabetes mellitus concerns insulin/glucose; diabetes insipidus concerns ADH/water regulation."),
            listOf(
                JambDeepCheck("bio-coord-19","Negative feedback is a process in which the response",listOf("amplifies the original change","opposes the original change","stops all metabolism","always increases hormone secretion"),1,"Negative feedback reduces the original deviation and helps return a variable towards its normal range."),
                JambDeepCheck("bio-coord-20","When blood glucose rises after a meal, the pancreas normally releases more",listOf("glucagon","insulin","ADH","thyroxine"),1,"Pancreatic beta cells release insulin to promote glucose uptake and storage."),
                JambDeepCheck("bio-coord-21","ADH increases water reabsorption mainly by acting on the",listOf("collecting ducts of the kidney","alveoli","stomach wall","cornea"),0,"ADH increases the water permeability of collecting ducts, conserving body water.")
            )
        ),
        JambDeepObjective(
            "Explain the regulation of body temperature and relate salt and water regulation to homeostasis.",
            listOf(
                JambDeepPoint("Thermoregulation when the body is too hot",
                    """The hypothalamus coordinates temperature regulation by comparing information from central and skin thermoreceptors with the body's regulated range. When body temperature rises, skin arterioles dilate, increasing blood flow near the surface and promoting heat loss by radiation and convection. Sweat glands secrete sweat; evaporation removes heat from the skin. Hairs lie flatter, reducing the insulating air layer. Behavioural responses include seeking shade, reducing activity and drinking water.

                    Evaporative cooling becomes less effective when relative humidity is high because the air is already moist. Sweating also causes water and salt loss, so fluid replacement may be necessary during prolonged heat exposure. The body does not cool simply because sweat is produced; heat is removed when sweat evaporates.""",
                    "On a hot day, skin blood vessels dilate and sweat evaporates, increasing heat loss.",
                    "Heat loss when hot: vasodilation, sweating and behavioural cooling; evaporation is essential for sweat cooling."),
                JambDeepPoint("Thermoregulation when the body is too cold",
                    """When body temperature falls, skin arterioles constrict, reducing blood flow near the surface and limiting heat loss. Sweating decreases, hairs may stand more upright in mammals with substantial body hair, and skeletal muscles may shiver. Shivering is rapid involuntary muscle contraction that increases metabolic heat production. Hormonal responses, including thyroid hormone and adrenaline effects, can influence metabolic heat production over longer periods.

                    Behaviour also matters: curling up, adding clothing, moving to shelter and increasing activity reduce heat loss or increase heat production. In humans, piloerection has limited insulating effect because body hair is sparse, but it is more useful in furred mammals. Thermoregulation is a negative-feedback system involving sensors, hypothalamic integration and effectors.""",
                    "In cold conditions, vasoconstriction reduces heat loss and shivering increases heat production.",
                    "Cold responses: vasoconstriction, reduced sweating and shivering; link the response to heat conservation or production."),
                JambDeepPoint("Salt and water regulation in organisms",
                    """The kidneys regulate water and dissolved ions by filtration, selective reabsorption and secretion. ADH adjusts water permeability in collecting ducts; aldosterone adjusts sodium reabsorption and potassium secretion. Thirst and access to water also influence balance. In aquatic and terrestrial animals, osmoregulatory strategies reflect the osmotic conditions of their habitats. Freshwater fish tend to gain water by osmosis and lose salts, so they excrete large volumes of dilute urine and actively take up ions at the gills. Marine bony fish tend to lose water and gain salts, so they drink seawater, excrete excess salts through gill cells and produce smaller volumes of urine.

                    Plants regulate water through root uptake, stomatal opening and closing, vacuoles and tissue structure. Guard cells change turgor to alter stomatal aperture. The animal kidney and plant stomata are different structures, but both contribute to regulation of water balance in their respective organisms.""",
                    "Freshwater fish excrete abundant dilute urine; marine bony fish drink seawater and actively excrete excess salts.",
                    "Relate osmotic challenge to the direction of water movement and the organism's response; ADH and aldosterone act in mammals.")
            ),
            listOf("Sweat cools by evaporation, not merely by being secreted.", "Vasodilation increases heat loss; vasoconstriction reduces heat loss.", "Freshwater and marine bony fish face opposite osmotic challenges."),
            listOf(
                JambDeepCheck("bio-coord-22","Which response helps a human lose heat when body temperature rises?",listOf("Vasoconstriction","Reduced sweating","Vasodilation and sweating","Shivering"),2,"Vasodilation increases heat transfer to the skin and sweating allows evaporative cooling."),
                JambDeepCheck("bio-coord-23","Shivering helps a cold mammal by",listOf("reducing muscle activity","increasing heat production through muscle contractions","increasing water loss","lowering metabolic rate"),1,"Shivering involves rapid muscle contractions that increase heat production."),
                JambDeepCheck("bio-coord-24","Freshwater bony fish generally produce",listOf("large volumes of dilute urine","small volumes of highly concentrated urine only","no urine","uric acid crystals only"),0,"Freshwater fish gain water osmotically and lose salts, so they remove excess water as dilute urine.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-coordination") objectives else emptyList()
}

@Composable
internal fun ReflexArcInvestigation() {
    var response by remember { mutableStateOf("Hot object") }
    val path = when (response) {
        "Hot object" -> listOf("Skin pain/temperature receptor detects harmful heat", "Sensory neurone carries impulse to spinal cord", "Relay neurone links sensory and motor pathways", "Motor neurone activates arm flexor muscles", "Hand withdraws; brain also becomes aware of pain")
        "Knee tap" -> listOf("Muscle spindle detects stretch in quadriceps", "Sensory impulse enters spinal cord", "A direct excitatory synapse activates a motor neurone", "Quadriceps contracts", "Lower leg extends in the knee-jerk response")
        else -> listOf("Corneal receptors detect an approaching object or irritation", "Sensory pathway carries information to brainstem circuits", "Motor output activates facial muscles", "Eyelids close rapidly", "Blink protects and moistens the eye surface")
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("REFLEX ARC INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Trace the response pathway", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Hot object", "Knee tap", "Blink").forEach { option ->
                FilterChip(selected = response == option, onClick = { response = option }, label = { Text(option) })
            }
            path.forEachIndexed { i, step ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.small) {
                        Text((i + 1).toString(), Modifier.padding(horizontal = 9.dp, vertical = 5.dp), fontWeight = FontWeight.Bold)
                    }
                    Text(step, Modifier.weight(1f))
                }
            }
            Text("Compare: which parts of the pathway are shared, and which CNS region coordinates the response?", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
internal fun EndocrineFeedbackInvestigation() {
    var condition by remember { mutableStateOf("After a meal") }
    val explanation = when (condition) {
        "After a meal" -> "Blood glucose rises. Pancreatic beta cells release insulin. Cells take up more glucose and the liver and muscles store more as glycogen; blood glucose falls towards its normal range."
        "Fasting" -> "Blood glucose falls. Pancreatic alpha cells release glucagon. The liver breaks down glycogen and produces/releases glucose; blood glucose rises towards its normal range."
        "Dehydration" -> "Blood water potential falls. Hypothalamic osmoreceptors stimulate thirst and ADH release from the posterior pituitary. Collecting ducts reabsorb more water and urine becomes more concentrated."
        else -> "Blood water potential rises after excess water intake. ADH release falls, collecting ducts reabsorb less water and a larger volume of dilute urine is produced."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("HORMONAL FEEDBACK INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Predict the endocrine response", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("After a meal", "Fasting", "Dehydration", "Excess water intake").forEach { option ->
                FilterChip(selected = condition == option, onClick = { condition = option }, label = { Text(option) })
            }
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium) {
                Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Feedback pathway", fontWeight = FontWeight.Bold)
                    Text(explanation)
                }
            }
            Text("Identify the variable, receptor or sensor, hormone, target organ and corrective response. The model is qualitative and does not calculate clinical hormone concentrations.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
internal fun SenseOrganDefectLab() {
    var condition by remember { mutableStateOf("Myopia") }
    val explanation = when (condition) {
        "Myopia" -> "Distant objects focus in front of the retina. A concave (diverging) lens moves the focal point back onto the retina."
        "Hypermetropia" -> "Near objects tend to focus behind the retina. A convex (converging) lens helps bring the focus forward onto the retina."
        "Astigmatism" -> "Unequal curvature of the cornea or lens causes different focal powers in different meridians. A cylindrical or toric lens can correct the refractive difference."
        else -> "The lens becomes cloudy and scatters light. Cataract is not corrected by ordinary concave or convex glasses; surgery may replace the cloudy lens."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("VISION DEFECT INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Match the defect to its correction", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Myopia", "Hypermetropia", "Astigmatism", "Cataract").forEach { option ->
                FilterChip(selected = condition == option, onClick = { condition = option }, label = { Text(option) })
            }
            Text(explanation)
            Text("Exam task: state where the image forms relative to the retina and name the corrective lens or treatment.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
