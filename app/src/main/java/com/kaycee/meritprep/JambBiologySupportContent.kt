package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** JAMB syllabus-aligned support and movement: plant tissues, plant responses and animal skeletons. */
internal object JambBiologySupportContent {
    private val objectives = listOf(
        JambDeepObjective(
            "Explain why living organisms need support and movement.",
            listOf(
                JambDeepPoint("Support maintains form and position",
                    """Support prevents the body or its parts from collapsing under their own weight, helps maintain shape, protects delicate structures and positions organs so they can work effectively. In plants, support keeps leaves exposed to light and holds flowers and fruits in suitable positions. In animals, the skeleton supports soft tissues, provides attachment points for muscles and protects organs such as the brain, spinal cord, heart and lungs.

                    The method of support depends on body design. Herbaceous plants rely heavily on turgid cells, cellulose cell walls and supporting tissues. Woody plants add extensive lignified tissues. Earthworms use a hydrostatic skeleton in which fluid pressure works with body-wall muscles. Arthropods have a chitinous exoskeleton, while vertebrates have an internal skeleton of cartilage and bone.""",
                    "A well-watered herbaceous stem remains firm because cells are turgid; a woody tree trunk is strengthened by lignified secondary tissues.",
                    "Explain support by naming the structure, the force or pressure it resists, and the body part it maintains."),
                JambDeepPoint("Movement supports survival",
                    """Movement allows organisms to obtain resources, avoid danger, find mates, disperse offspring and respond to changing conditions. Animals often move the whole body using muscles acting against a skeleton. Plants are usually fixed in one place, but their organs can change position through growth or changes in cell turgor. A shoot growing towards light can improve photosynthesis; roots growing towards water can improve water uptake.

                    Not every movement is locomotion. Locomotion is movement of an organism from one place to another. A sunflower shoot bending towards light is a movement of a plant part, not locomotion of the whole plant. Some movements are rapid and reversible, while growth movements are generally slower and may be permanent.""",
                    "A fish swims away from a predator; a tendril coils around a support; a root grows towards a moist region.",
                    "Distinguish movement of a body part from locomotion of the whole organism."),
                JambDeepPoint("Support and movement are linked",
                    """In many animals, muscles contract and pull on bones across joints. Bones act as levers, joints provide movable connections and muscles provide the pulling force. Tendons attach muscle to bone; ligaments connect bone to bone and help stabilise joints. In plants, the cell wall resists deformation, turgor pressure supports soft tissues, and differential growth changes organ direction.

                    A structure may serve more than one purpose. The rib cage supports the thoracic wall, protects the heart and lungs, and moves during ventilation. The vertebral column supports the head and trunk, protects the spinal cord and provides attachment for muscles.""",
                    "During breathing, ribs move at joints between the vertebrae and sternum; intercostal muscles alter thoracic volume.",
                    "When describing a movement, identify the force-producing tissue, supporting structure and movable connection.")
            ),
            listOf("Support does not always require a rigid skeleton; turgor and hydrostatic pressure can provide support.", "Movement is broader than locomotion.", "Muscles pull; they do not push bones directly."),
            listOf(
                JambDeepCheck("bio-supp-01","Which is a major function of support in an organism?",listOf("To prevent all metabolism","To maintain body shape and position of organs","To stop response to stimuli","To prevent growth"),1,"Support maintains form, protects structures and positions body parts for effective function."),
                JambDeepCheck("bio-supp-02","A shoot bending towards light is best described as",listOf("locomotion of the whole plant","movement of a plant part","egestion","muscular contraction"),1,"The shoot changes direction through growth; the whole plant does not move from place to place."),
                JambDeepCheck("bio-supp-03","In a typical mammalian limb, the structure that attaches a muscle to a bone is a",listOf("ligament","tendon","cartilage disc","synovial membrane"),1,"Tendons connect muscle to bone; ligaments connect bone to bone.")
            )
        ),
        JambDeepObjective(
            "Identify collenchyma, sclerenchyma, xylem fibres and phloem fibres as supporting tissues in plants.",
            listOf(
                JambDeepPoint("Collenchyma: flexible support in growing organs",
                    """Collenchyma consists of living, elongated cells with unevenly thickened primary walls, commonly thickened at the corners. The wall contains cellulose and pectin and is not heavily lignified. Because the cells remain living and can stretch, collenchyma provides flexible support to young stems, petioles and leaf midribs while these organs continue to grow.

                    Collenchyma is commonly found beneath the epidermis of young dicot stems and along leaf veins or margins. Its flexible support allows a young shoot to bend in wind without easily snapping. It is not the main strengthening tissue of mature woody stems.""",
                    "Celery strings and strands in young herbaceous stems illustrate the supportive role of collenchyma-rich tissues.",
                    "Living cells, uneven corner thickening, cellulose/pectin walls and flexible support are key identifiers."),
                JambDeepPoint("Sclerenchyma: rigid strengthening",
                    """Sclerenchyma cells generally have thick, lignified secondary walls and are dead at maturity. Lignin makes the walls hard and resistant to compression. Two common forms are fibres, which are long and narrow, and sclereids, which are shorter and irregularly shaped. Fibres occur around vascular bundles and in many mature stems; sclereids contribute to the gritty texture of pear flesh and the hard shell of some seeds and nuts.

                    Sclerenchyma provides strong mechanical support after organs have stopped elongating. Unlike collenchyma, its thick lignified walls make it relatively rigid. Do not identify every thick-walled cell as a vessel: xylem vessels conduct water, while sclerenchyma fibres mainly strengthen tissues.""",
                    "The hard stone cells in a pear are sclereids; fibres in a vascular bundle strengthen the stem.",
                    "Sclerenchyma is usually dead at maturity, with thick lignified walls; distinguish fibres from sclereids."),
                JambDeepPoint("Xylem and phloem fibres",
                    """Xylem tissue contains vessels or tracheids for water and mineral transport, together with fibres and parenchyma. Xylem fibres have thick lignified walls and strengthen the plant while the conducting elements transport water. Phloem transports organic assimilates, especially sucrose, through sieve tube elements assisted by companion cells. Phloem fibres, also called bast fibres, are sclerenchymatous fibres associated with phloem and provide tensile strength.

                    Commercial plant fibres such as flax and jute are obtained from phloem fibres in particular plants. Xylem and phloem are complex vascular tissues, not single cell types. Their fibres are supporting components, but the main transport roles belong to xylem conducting elements and phloem sieve tubes.""",
                    "Jute and flax fibres are obtained from phloem-associated bast fibres; xylem fibres strengthen wood.",
                    "Xylem fibres strengthen water-conducting tissue; phloem fibres strengthen food-conducting tissue.")
            ),
            listOf("Collenchyma is living and flexible; sclerenchyma is generally dead at maturity and rigid.", "Xylem vessels conduct water; xylem fibres mainly strengthen.", "Phloem sieve tubes transport assimilates; phloem fibres provide support."),
            listOf(
                JambDeepCheck("bio-supp-04","Which plant tissue gives flexible support to a young growing stem?",listOf("Collenchyma","Sclerenchyma","Epidermal cuticle","Cork"),0,"Collenchyma cells have unevenly thickened primary walls and can support organs that are still growing."),
                JambDeepCheck("bio-supp-05","The rigidity of sclerenchyma is mainly due to",listOf("chlorophyll","lignified thick secondary walls","large central vacuoles","thin cellulose walls only"),1,"Lignin strengthens the thick secondary walls of sclerenchyma cells."),
                JambDeepCheck("bio-supp-06","Jute fibres are associated mainly with",listOf("phloem fibres","root hairs","guard cells","xylem vessels"),0,"Jute is a bast fibre obtained from phloem-associated sclerenchyma fibres.")
            )
        ),
        JambDeepObjective(
            "Describe the distribution of supporting tissues in roots, stems and leaves.",
            listOf(
                JambDeepPoint("Distribution in stems",
                    """In young dicot stems, collenchyma commonly occurs just beneath the epidermis, often in ridges or strands, where it supports the growing stem. Sclerenchyma fibres may occur around vascular bundles or as caps that strengthen the stem. Xylem lies towards the inner side of each vascular bundle and phloem towards the outer side in a typical dicot stem; xylem fibres contribute to support. In woody stems, secondary xylem accumulates as wood and provides major mechanical strength.

                    Monocot stems commonly have vascular bundles scattered through the ground tissue, often with sclerenchyma bundle sheaths. Their support is therefore distributed differently from the ring arrangement typical of many dicot stems. Use the plant type and cross-section before deciding where a tissue lies.""",
                    "A transverse section of a young dicot stem may show subepidermal collenchyma and vascular bundles arranged in a ring.",
                    "Relate position to function: peripheral support resists bending; lignified vascular tissues strengthen the stem."),
                JambDeepPoint("Distribution in roots",
                    """Roots require support against pulling forces and must maintain conducting pathways while growing through soil. In many roots, xylem occupies a central position in the stele, with phloem located between or around the xylem arms. Xylem vessels and associated fibres provide strength as well as water conduction. Collenchyma is generally less prominent in roots than in young aerial stems because roots are not usually exposed to the same bending stresses.

                    The exact arrangement differs between monocot and dicot roots. A typical dicot root often has a central star-shaped xylem with phloem between the arms; a typical monocot root has a central pith with xylem and phloem arranged around it. Root tissues should be identified from a labelled cross-section rather than assuming all vascular bundles look alike.""",
                    "A typical dicot root cross-section shows central star-shaped xylem and phloem between the arms.",
                    "Xylem provides both conduction and support; distinguish the central root stele from the outer cortex."),
                JambDeepPoint("Distribution in leaves",
                    """Leaf support is concentrated along the midrib and veins, which contain vascular bundles. Xylem is generally positioned towards the upper (adaxial) side of a typical leaf vascular bundle, while phloem is towards the lower (abaxial) side. Sclerenchyma fibres or bundle-sheath extensions may strengthen major veins. Collenchyma can support the midrib and petiole in many leaves. The lamina is supported by turgid mesophyll cells and its network of veins.

                    This arrangement keeps the leaf blade spread out for light capture while allowing it to bend without tearing. Leaf anatomy varies among species, so the relative positions of xylem and phloem should be interpreted from the stated cross-section and its orientation.""",
                    "In a typical dorsiventral leaf, the xylem in the midrib vascular bundle faces the upper epidermis and phloem faces the lower side.",
                    "In a leaf vein, xylem is usually uppermost and phloem lower; veins also strengthen the lamina.")
            ),
            listOf("A typical dicot stem has vascular bundles in a ring; a typical monocot stem has scattered bundles.", "Do not apply the dicot root star-shaped xylem pattern to a monocot root.", "In a typical leaf vein, xylem faces the upper side and phloem the lower side."),
            listOf(
                JambDeepCheck("bio-supp-07","In a typical young dicot stem, collenchyma is commonly found",listOf("beneath the epidermis","only in the root cap","inside the pith only","in the seed coat"),0,"Subepidermal collenchyma supports young stems while they continue to elongate."),
                JambDeepCheck("bio-supp-08","A typical dicot root has",listOf("a central star-shaped xylem","scattered vascular bundles throughout the cortex","no vascular tissue","xylem only in the epidermis"),0,"Many dicot roots have central star-shaped xylem with phloem between the arms."),
                JambDeepCheck("bio-supp-09","In a typical leaf vascular bundle, xylem is positioned towards the",listOf("lower epidermis","upper epidermis","stomatal pore","root cap"),1,"Xylem is generally on the adaxial or upper side of a leaf vascular bundle.")
            )
        ),
        JambDeepObjective(
            "Distinguish tropic, tactic, nastic and sleep movements and explain plant responses to light, water, gravity and touch.",
            listOf(
                JambDeepPoint("Tropic movements are directional growth responses",
                    """A tropism is a directional growth response in which the direction of growth depends on the direction of a stimulus. Positive tropism is growth towards the stimulus; negative tropism is growth away from it. Phototropism is response to light, geotropism (gravitropism) to gravity, hydrotropism to water and thigmotropism to touch. Roots commonly show positive gravitropism and positive hydrotropism; shoots commonly show positive phototropism and negative gravitropism.

                    Tropic responses often involve unequal growth on opposite sides of an organ. The curvature may persist because growth changes the organ's structure. The stimulus direction matters: a root growing downwards in response to gravity is positively geotropic, while a shoot growing upwards against gravity is negatively geotropic.""",
                    "A shoot bends towards a one-sided light source: the shoot shows positive phototropism.",
                    "Tropism is directional growth; use positive for towards and negative for away from the stimulus."),
                JambDeepPoint("Tactic movements are directional locomotion",
                    """Taxis is movement of a whole motile cell or organism towards or away from a stimulus. Phototaxis is movement in response to light; chemotaxis is movement in response to chemicals. The organism moves through its environment, rather than a fixed plant organ simply growing in a new direction. Some algae and motile reproductive cells show tactic responses.

                    A positive taxis response moves towards the stimulus; a negative taxis response moves away. This differs from tropism because taxis involves locomotion of the whole organism or cell and does not depend on unequal growth of a plant organ.""",
                    "A motile Chlamydomonas cell swimming towards light shows positive phototaxis.",
                    "Taxis = directional locomotion; tropism = directional growth."),
                JambDeepPoint("Nastic and sleep movements",
                    """A nastic movement is a plant response whose direction is not determined by the direction of the stimulus. It may depend on stimulus intensity or the structure of the organ. Many nastic movements result from changes in turgor pressure in specialised cells and can be reversible. The rapid folding of Mimosa pudica leaflets after touch is thigmonasty or seismonasty. Opening and closing of some flowers in response to light or temperature are photonastic or thermonastic responses.

                    Sleep movements, or nyctinastic movements, are daily changes in the position of leaves or leaflets, often associated with day-night cycles. They are commonly controlled by changes in turgor in a pulvinus at the base of a leaf or leaflet. Sleep movement is a form of nastic movement; it is not the same as a plant being asleep or stopping respiration.""",
                    "Mimosa leaflets fold after touch; some legume leaflets lower at night and rise during the day.",
                    "Nastic direction is independent of stimulus direction; sleep movement is commonly nyctinastic.")
            ),
            listOf("Tropism is directional growth, whereas taxis is directional locomotion.", "Nastic movement direction does not depend on the direction from which the stimulus comes.", "Sleep movement is a form of nastic movement, often linked to daily rhythms."),
            listOf(
                JambDeepCheck("bio-supp-10","A root growing towards a source of water shows",listOf("positive hydrotropism","negative phototropism","positive phototaxis","nyctinasty"),0,"Hydrotropism is a directional growth response to water; growth towards water is positive hydrotropism."),
                JambDeepCheck("bio-supp-11","A motile alga swimming towards light demonstrates",listOf("phototropism","positive phototaxis","thigmonasty","geotropism"),1,"Phototaxis is locomotion in response to light; movement towards it is positive."),
                JambDeepCheck("bio-supp-12","The folding of Mimosa leaflets after touch is an example of",listOf("positive phototropism","thigmonasty","negative hydrotropism","geotaxis"),1,"Mimosa responds to touch with a nastic movement whose direction is not set by the direction of the stimulus.")
            )
        ),
        JambDeepObjective(
            "Identify root and shoot growth regions and explain the role of auxins in tropic responses.",
            listOf(
                JambDeepPoint("Where roots and shoots grow",
                    """Primary length growth occurs near the tips of roots and shoots. The apical meristem contains actively dividing cells. Behind it, newly formed cells elongate in the zone of elongation; farther from the tip, cells differentiate and specialise in the zone of maturation. In roots, the root cap protects the delicate apical meristem as the root pushes through soil. Root hairs develop in the maturation region and increase the surface area for absorption.

                    In shoots, the shoot apical meristem produces new leaves, nodes and internodes. Intercalary meristems at the bases of leaves or internodes in some plants, especially grasses, allow regrowth after grazing or cutting. Lateral meristems such as vascular cambium contribute to increase in thickness rather than primary length.""",
                    "Grass can regrow after mowing because intercalary meristems remain near the base of leaves and internodes.",
                    "Apical meristems drive primary length growth; lateral meristems drive secondary thickening; elongation occurs behind the root tip."),
                JambDeepPoint("Auxin distribution and phototropism",
                    """Auxins are plant growth regulators, with indole-3-acetic acid (IAA) being a major natural auxin. They are produced in growing shoot tips and young tissues and are transported to other regions. In a shoot receiving light from one side, auxin becomes distributed unevenly, accumulating more on the shaded side. In shoots, a higher auxin concentration generally stimulates cell elongation, so the shaded side grows faster and the shoot bends towards the light.

                    The classic school-level explanation is that light causes auxin to move to the shaded side. Modern plant physiology describes more complex light perception and directional auxin transport, but the uneven growth model explains the bending tested at this level. Do not assume auxin has the same growth effect in every organ: roots are generally more sensitive to auxin, and high concentrations can inhibit root elongation.""",
                    "A shoot illuminated from the left grows more on its shaded right side and curves left towards the light.",
                    "In shoots, auxin promotes elongation on the shaded side, producing positive phototropism."),
                JambDeepPoint("Gravitropism, hydrotropism and touch responses",
                    """Gravity is sensed in specialised cells, including statocytes containing dense starch-rich amyloplasts that settle in response to gravity. This helps redirect auxin transport. In shoots, greater auxin on the lower side promotes elongation, causing the shoot to curve upwards. In roots, greater auxin on the lower side inhibits elongation relative to the upper side, so the root curves downwards. The opposite growth effects in roots and shoots are a common examination point.

                    Hydrotropism allows roots to grow towards regions with greater water availability. Thigmotropism is a directional growth response to contact; tendrils of climbing plants coil around supports after touching them. Distinguish this from rapid thigmonastic movement such as Mimosa leaflet folding: one is growth direction determined by contact, the other is a nastic response.""",
                    "A tendril coils around a stake through thigmotropic growth; a root curves downwards through positive gravitropism.",
                    "Auxin promotes elongation in shoots but can inhibit elongation in roots at concentrations that promote shoot growth.")
            ),
            listOf("Cell division occurs in meristems; elongation occurs behind the root tip.", "Auxin promotes shoot elongation but may inhibit root elongation at comparable concentrations.", "A tendril coiling around a support is thigmotropism; Mimosa folding is thigmonasty."),
            listOf(
                JambDeepCheck("bio-supp-13","The zone chiefly responsible for cell division near a root tip is the",listOf("root hair zone","apical meristem","mature xylem","root cap only"),1,"The apical meristem contains actively dividing cells; the root cap protects it."),
                JambDeepCheck("bio-supp-14","In a shoot exposed to one-sided light, auxin accumulation on the shaded side usually causes",listOf("less elongation on the shaded side","greater elongation on the shaded side","immediate cell death","the root cap to form"),1,"Auxin promotes elongation in shoots, so the shaded side grows faster and the shoot bends towards light."),
                JambDeepCheck("bio-supp-15","A key difference between root and shoot gravitropism is that",listOf("auxin has no effect in roots","lower-side auxin promotes elongation equally in both","lower-side auxin inhibits root elongation but promotes shoot elongation","roots do not respond to gravity"),2,"Roots are more sensitive to auxin; higher auxin on the lower side inhibits elongation, while in shoots it promotes elongation.")
            )
        ),
        JambDeepObjective(
            "Relate the locations and properties of chitin, cartilage and bone to their supporting functions.",
            listOf(
                JambDeepPoint("Chitin and the arthropod exoskeleton",
                    """Chitin is a tough nitrogen-containing polysaccharide found in the exoskeleton of arthropods, including insects, spiders and crustaceans. The exoskeleton provides an external framework, protects soft tissues, reduces water loss in terrestrial insects and provides sites for muscle attachment. In crustaceans, the cuticle may be strengthened with calcium salts. The exoskeleton is non-living and cannot expand continuously, so arthropods must moult (ecdysis) to grow.

                    The new cuticle is initially soft and expands before hardening. During this vulnerable period, the animal may be more exposed to predators and water loss. Chitin is not the same as cellulose: both are structural polysaccharides, but chitin contains nitrogen and is characteristic of arthropods and fungal cell walls.""",
                    "A grasshopper's chitinous cuticle protects the body and provides attachment for muscles; growth requires moulting.",
                    "Chitin is in the arthropod exoskeleton; moulting permits growth because the rigid cuticle cannot expand continuously."),
                JambDeepPoint("Cartilage: flexible support and smooth joint surfaces",
                    """Cartilage is a firm but flexible connective tissue. Chondrocytes lie in spaces called lacunae within an extracellular matrix rich in collagen and proteoglycans. Cartilage has no blood vessels, so nutrients diffuse through the matrix. It occurs at the ends of bones in synovial joints, in the nose, external ear, tracheal rings, larynx, intervertebral discs and parts of the respiratory system.

                    Articular cartilage reduces friction and distributes compressive forces at movable joints. Cartilage in the trachea helps keep the airway open while allowing flexibility. Intervertebral discs contain fibrocartilage that resists compression and absorbs shock. Cartilage repairs slowly because it is avascular.""",
                    "Hyaline cartilage covers the ends of bones in a synovial joint; rings of cartilage help prevent the trachea from collapsing.",
                    "Cartilage is flexible, avascular and found at joint surfaces, airways, ear, nose and intervertebral discs."),
                JambDeepPoint("Bone: rigid living connective tissue",
                    """Bone is a hard, living connective tissue with cells embedded in a mineralised matrix. Collagen provides tensile strength and flexibility, while calcium phosphate salts, mainly hydroxyapatite, provide hardness and resistance to compression. Osteocytes occupy lacunae; osteoblasts build bone and osteoclasts resorb it. Compact bone is dense and organised into osteons in many mammalian long bones; spongy bone contains trabeculae and spaces that may contain marrow.

                    Bone supports the body, protects organs, provides levers for muscles, stores calcium and phosphate, and contains marrow where blood cells are formed. It is vascular and continually remodelled. Do not describe bone as an inert mineral; it is living tissue that responds to mechanical loading and hormones.""",
                    "The skull protects the brain; the femur supports body weight and acts as a lever during walking.",
                    "Collagen gives bone tensile strength; mineral salts give hardness; marrow supports blood-cell formation.")
            ),
            listOf("Chitin forms an arthropod exoskeleton and growth requires moulting.", "Cartilage is avascular and repairs slowly; articular cartilage reduces friction.", "Bone is living, vascular tissue containing collagen and mineral salts."),
            listOf(
                JambDeepCheck("bio-supp-16","The main structural material in the arthropod exoskeleton is",listOf("cellulose","chitin","keratin","calcium carbonate only"),1,"Chitin is a tough structural polysaccharide in arthropod cuticle."),
                JambDeepCheck("bio-supp-17","Cartilage at the ends of bones in a synovial joint mainly",listOf("produces red blood cells","reduces friction and absorbs compression","forms the tendon","secretes bile"),1,"Articular cartilage provides a smooth low-friction surface and distributes compressive loads."),
                JambDeepCheck("bio-supp-18","The hardness of bone is mainly due to",listOf("glycogen","mineral salts such as calcium phosphate","haemoglobin","chitin"),1,"Mineral salts, especially calcium phosphate, mineralise the collagen matrix and make bone hard.")
            )
        ),
        JambDeepObjective(
            "Relate the general layout of the mammalian skeleton to support, locomotion and respiration.",
            listOf(
                JambDeepPoint("Axial and appendicular skeleton",
                    """The mammalian skeleton is commonly divided into the axial and appendicular skeleton. The axial skeleton includes the skull, vertebral column, ribs and sternum. It supports the head and trunk and protects the brain, spinal cord, heart and lungs. The appendicular skeleton includes the pectoral and pelvic girdles and the bones of the forelimbs and hindlimbs. It attaches the limbs to the axial skeleton and supports movement.

                    The pectoral girdle consists mainly of scapulae and clavicles and permits a wide range of upper-limb movement. The pelvic girdle is stronger and transfers the weight of the trunk to the hindlimbs. In humans, the pelvis also supports abdominal and pelvic organs and is adapted to bipedal posture.""",
                    "The vertebral column belongs to the axial skeleton; the humerus and femur belong to the appendicular skeleton.",
                    "Axial skeleton supports and protects the central body; appendicular skeleton supports limb attachment and movement."),
                JambDeepPoint("Vertebral column and rib cage",
                    """The vertebral column is made of vertebrae separated by intervertebral discs. It supports the head and trunk, protects the spinal cord, allows controlled bending and provides attachment for muscles and ribs. The cervical, thoracic, lumbar, sacral and coccygeal regions have different structures and functions. The vertebral canal encloses the spinal cord; openings between adjacent vertebrae allow spinal nerves to pass.

                    The rib cage consists of thoracic vertebrae, ribs, costal cartilages and sternum. It protects the heart and lungs. Ribs articulate with thoracic vertebrae and, directly or indirectly, with the sternum. Their movement during ventilation changes thoracic volume. The first seven pairs are true ribs, the next three pairs are false ribs, and the last two pairs are floating ribs in the typical human arrangement.""",
                    "The thoracic cage protects the lungs and heart while moving during inspiration and expiration.",
                    "The vertebral column protects the spinal cord; the rib cage protects thoracic organs and assists ventilation."),
                JambDeepPoint("Limb bones and locomotion",
                    """Long bones such as the humerus, radius, ulna, femur, tibia and fibula act as levers. Skeletal muscles attach to bones by tendons and contract to pull bones across joints. The arrangement of bones, joints and muscles determines the range and direction of movement. The femur is the major weight-bearing bone of the thigh; the tibia bears much of the load in the lower leg, while the fibula provides muscle attachment and lateral stability.

                    The pectoral girdle allows the upper limb considerable mobility for manipulation. The pelvic girdle sacrifices some mobility for stability and weight transfer. Locomotion depends on coordinated action of muscles, bones, joints, nerves and energy supply; the skeleton alone does not generate movement.""",
                    "At the elbow, biceps contraction flexes the forearm while triceps contraction extends it.",
                    "Bones act as levers, joints are movable connections and skeletal muscles generate pulling force.")
            ),
            listOf("Axial skeleton includes skull, vertebral column and thoracic cage.", "The appendicular skeleton includes girdles and limbs.", "The skeleton provides levers but muscles generate the force for movement."),
            listOf(
                JambDeepCheck("bio-supp-19","Which structure belongs to the axial skeleton?",listOf("Femur","Scapula","Vertebral column","Humerus"),2,"The vertebral column is part of the axial skeleton."),
                JambDeepCheck("bio-supp-20","The pelvic girdle is especially important for",listOf("attaching the hindlimbs and transferring body weight","forming the skull","producing digestive enzymes","filtering blood"),0,"The pelvic girdle attaches the hindlimbs and transfers trunk weight to them."),
                JambDeepCheck("bio-supp-21","During locomotion, bones mainly act as",listOf("digestive glands","levers pulled by contracting muscles","sources of nerve impulses","air sacs"),1,"Muscles pull on bones across joints; bones act as levers.")
            )
        ),
        JambDeepObjective(
            "Differentiate types of joints using appropriate examples.",
            listOf(
                JambDeepPoint("Fibrous and cartilaginous joints",
                    """A joint is a place where two or more bones or skeletal elements meet. Fibrous joints are held together by dense connective tissue and permit little or no movement. Sutures between skull bones are examples; they protect the brain by forming a strong, relatively immovable case. A gomphosis anchors a tooth in its socket.

                    Cartilaginous joints connect bones by cartilage and allow limited movement or absorb compression. The joints between vertebral bodies contain intervertebral discs of fibrocartilage. The pubic symphysis is another cartilaginous joint. These joints provide strength while allowing small movements or deformation under load.""",
                    "Skull sutures are fibrous and essentially immovable; joints between vertebral bodies are cartilaginous and slightly movable.",
                    "Fibrous joints are strongly bound by connective tissue; cartilaginous joints use cartilage and usually permit limited movement."),
                JambDeepPoint("Synovial joints and their features",
                    """Synovial joints are freely movable joints. The ends of articulating bones are covered with smooth articular cartilage and enclosed by a joint capsule. The synovial membrane lines the capsule and secretes synovial fluid, which lubricates the joint and helps nourish articular cartilage. Ligaments strengthen the capsule and limit excessive movement. Some joints contain menisci or bursae that improve load distribution or reduce friction.

                    Synovial joints include hinge, ball-and-socket, pivot, gliding (plane), saddle and condyloid types. Their shapes determine the movements they allow. Do not confuse synovial fluid with cartilage: fluid lubricates the joint cavity, while cartilage covers the bone ends.""",
                    "The knee is a modified hinge synovial joint; the shoulder is a ball-and-socket synovial joint.",
                    "Synovial joints have a cavity containing fluid, articular cartilage and a capsule."),
                JambDeepPoint("Joint types and movement",
                    """A hinge joint mainly permits flexion and extension in one plane, as at the elbow and interphalangeal joints. A ball-and-socket joint permits movement in several planes, including rotation; examples are shoulder and hip. A pivot joint permits rotation around an axis, as between the atlas and axis in the neck. Gliding joints allow relatively small sliding movements between flat surfaces, as in some wrist and ankle bones. A saddle joint, such as the carpometacarpal joint at the base of the thumb, permits movement in two planes and helps opposition. Condyloid joints, such as the wrist (radiocarpal) joint, allow flexion, extension, abduction and adduction but limited rotation.

                    Joint classification is based on structure and the movements permitted. A joint can allow several movements but still have a characteristic type. The shoulder is more mobile but less stable than the hip because of differences in socket depth and surrounding structures.""",
                    "The shoulder is ball-and-socket; the elbow is hinge; the atlas-axis joint is pivot; the wrist is condyloid.",
                    "Match joint shape with permitted movement and a correct anatomical example.")
            ),
            listOf("Sutures are fibrous joints; intervertebral discs form cartilaginous joints.", "Synovial fluid lubricates; articular cartilage covers bone ends.", "The shoulder and hip are ball-and-socket joints; the elbow is hinge; atlas-axis is pivot."),
            listOf(
                JambDeepCheck("bio-supp-22","The joints between most skull bones are",listOf("hinge joints","fibrous sutures","ball-and-socket joints","pivot joints"),1,"Skull sutures are fibrous joints that permit little or no movement."),
                JambDeepCheck("bio-supp-23","Which joint allows rotation of the head from side to side?",listOf("Hinge","Pivot","Saddle","Gliding"),1,"The atlanto-axial pivot joint permits rotation of the head."),
                JambDeepCheck("bio-supp-24","The shoulder joint is classified as",listOf("ball-and-socket","fibrous","hinge","cartilaginous"),0,"The shoulder is a synovial ball-and-socket joint that permits movement in several planes.")
            )
        ),
        JambDeepObjective(
            "Apply the protective, supportive, locomotive and respiratory functions of the skeleton to animal wellbeing.",
            listOf(
                JambDeepPoint("Protection and support",
                    """The skull protects the brain and major sense organs. The vertebral column encloses and protects the spinal cord. The rib cage protects the heart and lungs, while the pelvic girdle helps protect pelvic organs. The skeleton also supports soft tissues, maintains posture and provides a framework to which muscles and ligaments attach. Bone marrow within certain bones forms blood cells, while bone stores calcium and phosphate that are needed for normal body function.

                    Protection is achieved by placing vulnerable organs within strong bony structures. The design must balance strength with movement: ribs protect the thoracic organs but are flexible enough to move during breathing. Vertebrae protect the spinal cord while allowing the trunk to bend and rotate within limits.""",
                    "A fractured rib can compromise protection of thoracic organs and make breathing painful.",
                    "Name the protected organ and the skeletal part that surrounds or supports it."),
                JambDeepPoint("Locomotion and leverage",
                    """Skeletal muscles attach to bones through tendons. When a muscle contracts, it pulls on a bone and produces movement around a joint. Antagonistic muscle pairs are needed because muscles can pull but cannot actively push: at the elbow, biceps flexes the forearm while triceps extends it. Joint shape determines the possible range of movement, while ligaments and muscles stabilise the joint.

                    The skeleton also provides attachment for muscles and acts as a system of levers. A first-class lever has the fulcrum between effort and load, a second-class lever has the load between fulcrum and effort, and a third-class lever has the effort between fulcrum and load. Many limb movements are third-class levers, favouring speed and range rather than force multiplication.""",
                    "During elbow flexion, the biceps contracts and the triceps relaxes; during extension, the roles reverse.",
                    "Muscles pull on bones; antagonistic pairs produce opposite movements around joints."),
                JambDeepPoint("Respiration and animal wellbeing",
                    """The rib cage contributes to ventilation by moving with the intercostal muscles. During inspiration, external intercostal muscles contract, lifting the ribs and sternum; the diaphragm contracts and flattens. Thoracic volume increases, pressure falls and air enters the lungs. During quiet expiration, the muscles relax and elastic recoil reduces thoracic volume, pushing air out. The skeletal framework therefore participates in respiratory movement while protecting the lungs.

                    The skeleton supports overall wellbeing through posture, movement, organ protection, mineral storage and blood-cell formation. Injury or disease affecting bones, joints or muscles can reduce mobility, impair ventilation or expose organs to damage. Exercise, adequate nutrition and safe movement help maintain musculoskeletal health.""",
                    "Paralysis of intercostal muscles can impair chest-wall movement and reduce the effectiveness of ventilation.",
                    "Ribs and sternum move during ventilation; the diaphragm is skeletal muscle, not a bone.")
            ),
            listOf("The rib cage both protects the heart and lungs and participates in ventilation.", "The skeleton does not move itself; muscles contract and pull on bones.", "Bone marrow forms blood cells; bone stores minerals."),
            listOf(
                JambDeepCheck("bio-supp-25","The vertebral column protects the",listOf("heart","spinal cord","liver","stomach"),1,"The vertebral canal encloses the spinal cord and protects it from injury."),
                JambDeepCheck("bio-supp-26","Which pair of muscles produces opposite movements at the elbow?",listOf("Biceps and triceps","Diaphragm and deltoid","Masseter and temporalis","Quadriceps and hamstrings"),0,"Biceps flexes the elbow while triceps extends it; they act as an antagonistic pair."),
                JambDeepCheck("bio-supp-27","During inspiration, the rib cage",listOf("moves down and in as thoracic volume falls","moves up and out as thoracic volume increases","remains fixed","closes the trachea"),1,"External intercostal contraction lifts the ribs up and out, increasing thoracic volume.")
            )
        )
    )

    fun forTopic(topicId: String): List<JambDeepObjective> =
        if (topicId == "bio-support") objectives else emptyList()
}

@Composable
internal fun PlantSupportTropismInvestigation() {
    var stimulus by remember { mutableStateOf("Light from one side") }
    val result = when (stimulus) {
        "Light from one side" -> "Shoot bends towards the light. Auxin accumulates on the shaded side of the shoot, where cells elongate more rapidly."
        "Gravity" -> "A root placed horizontally curves downwards (positive gravitropism); a shoot curves upwards (negative gravitropism)."
        "Moisture gradient" -> "A growing root tends towards the region with greater water availability: positive hydrotropism."
        else -> "A tendril grows around a contacted support: thigmotropism. This is directional growth, unlike the rapid nastic folding of Mimosa."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("PLANT RESPONSE INVESTIGATION", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Predict the growth response", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text("Choose the stimulus acting on a growing plant. Predict the direction and explain the mechanism.")
            listOf("Light from one side", "Gravity", "Moisture gradient", "Touch from a support").forEach { option ->
                FilterChip(selected = stimulus == option, onClick = { stimulus = option }, label = { Text(option) })
            }
            Text("Expected response", fontWeight = FontWeight.Bold)
            Text(result)
            Text("Investigation design", fontWeight = FontWeight.Bold)
            Text("Use identical seedlings, change one stimulus at a time, keep water and temperature controlled, record the direction and angle of curvature at regular intervals, and include a control group. A result is an observation; the proposed hormone mechanism is an explanation.")
        }
    }
    BiologyExternalLabLinks(
        heading = "FREE INTERACTIVE PLANT AND SKELETON RESOURCES",
        resources = listOf(
            BiologyLabResource("Visualize Biology: Plant Tropisms", "Free browser simulation of phototropism, gravitropism and auxin distribution, with practical activities and quiz.", "https://visualizebiology.com/tools/plant-tropisms/"),
            BiologyLabResource("AZRS: Phototropism and Gravitropism Lab", "Move the light and rotate a seedling to predict shoot and root growth; free and designed for phones.", "https://tools.quickyla.com/quickfix/simulations/tropism-lab/"),
            BiologyLabResource("ScienceQuest: 3D Skeleton Explorer", "Explore and identify the 203 bones in an interactive browser-based model; no sign-in, about 8.1 MB on first load.", "https://sciencequest.in/tools/full-skeleton-explorer/")
        )
    )
}

@Composable
internal fun SkeletonJointMovementLab() {
    var joint by remember { mutableStateOf("Elbow") }
    val details = when (joint) {
        "Elbow" -> "Hinge joint: mainly flexion and extension. Biceps contracts to flex the forearm; triceps contracts to extend it."
        "Shoulder" -> "Ball-and-socket joint: movement in several planes, including flexion, extension, abduction, adduction and rotation. It has a wide range but depends on muscles and ligaments for stability."
        "Neck (atlas-axis)" -> "Pivot joint: the atlas rotates around the dens of the axis, allowing the head to turn from side to side."
        "Wrist" -> "Condyloid joint: flexion, extension, abduction and adduction are possible, but axial rotation is limited."
        else -> "Hip: ball-and-socket joint. Its deep socket and strong ligaments provide stability for weight-bearing while allowing movement in several planes."
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("SKELETAL MOVEMENT LAB", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Identify the joint and its movement", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            listOf("Elbow", "Shoulder", "Neck (atlas-axis)", "Wrist", "Hip").forEach { option ->
                FilterChip(selected = joint == option, onClick = { joint = option }, label = { Text(option) })
            }
            Text(details)
            Text("Think like an examiner: state the joint type, name one movement it permits and identify the bones or anatomical region involved.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
