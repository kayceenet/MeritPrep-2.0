package com.kaycee.meritprep

/** Third beginner-learning block for JAMB Physics. Draft content pending independent review. */
object PhysicsCourseExtension3 {
    val topics: List<LearningTopic> = listOf(
        LearningTopic(
            "phy-refraction", "Refraction of light through plane and curved surfaces", "PHY",
            "A straight straw can look bent when it is partly inside a glass of water. The straw has not changed shape. Light from the submerged part changes direction as it passes from water into air, so the part under water appears to come from a different position. This change in direction is refraction.",
            listOf(
                "Explain refraction in terms of a change in light speed between media",
                "State and apply Snell's law using angles measured from the normal",
                "Calculate refractive index and apparent depth in simple cases",
                "Explain critical angle and total internal reflection",
                "Relate refraction to lenses, prisms and optical fibres"
            ),
            listOf(
                "Why light changes direction" to "Light travels at different speeds in different transparent media. When a ray enters a medium at an angle, one part of the wavefront changes speed before the other, so the ray changes direction. Entering a medium with a higher refractive index bends the ray towards the normal; entering a lower-index medium bends it away from the normal. A ray travelling along the normal changes speed but does not change direction.",
                "Refractive index and Snell's law" to "The absolute refractive index of a medium is n = c/v, where c is the speed of light in vacuum and v is its speed in the medium. For two transparent media, n₁ sin i = n₂ sin r. The angles i and r are measured between each ray and the normal to the boundary. For light entering glass from air, n is usually greater than one and the refracted angle is smaller than the incident angle.",
                "Worked example: refraction into glass" to "A ray travels from air into glass of refractive index 1.50 at an incidence angle of 30°. Using n₁ sin i = n₂ sin r, sin r = (1.00 × sin 30°)/1.50 = 0.333. Therefore r is approximately 19.5°. The ray bends towards the normal. Keep the calculator in degree mode when the angle is stated in degrees.",
                "Apparent depth" to "When an object under water is viewed nearly vertically from air, it usually appears shallower than it really is. For a plane boundary and near-normal viewing, apparent depth ≈ real depth ÷ refractive index of the liquid relative to air. This is an approximation for the stated viewing geometry; do not apply it indiscriminately to oblique viewing or curved surfaces.",
                "Critical angle and total internal reflection" to "Total internal reflection occurs only when light travels from a medium of higher refractive index to one of lower refractive index and the incidence angle in the denser medium exceeds the critical angle. At the critical angle, the refracted ray travels along the boundary at 90° to the normal. For a denser medium to air, sin C = 1/n. Optical fibres guide light by repeated total internal reflection when the core and cladding are selected appropriately.",
                "Lenses and curved surfaces" to "A converging (convex) lens brings parallel rays towards a principal focus; a diverging (concave) lens spreads them as if they came from a virtual focus. Refraction at the two curved surfaces produces the lens action. Ray diagrams should show the principal axis, optical centre, focal points and at least two principal rays. Use the sign convention specified in the question when applying a lens formula."
            ),
            "A ray enters glass of refractive index 1.50 from air at 30° to the normal. What is the approximate angle of refraction?",
            listOf("19.5°", "30.0°", "41.8°", "60.0°"), 0,
            "Snell's law gives sin r = sin 30°/1.50 = 0.333, so r ≈ 19.5°. The ray bends towards the normal."
        ),
        LearningTopic(
            "phy-optical-instruments", "Optical instruments", "PHY",
            "A camera, a microscope and the human eye all use lenses to form images. The lens does not simply make an object bigger; it redirects light so that an image forms at a particular position. Understanding focal length, lens power and image formation helps explain how optical instruments work and how common vision defects are corrected.",
            listOf(
                "Distinguish converging and diverging lenses",
                "Use focal length and lens power in simple calculations",
                "Describe image formation in a camera and the human eye",
                "Explain myopia and hyperopia and their corrective lenses",
                "Identify the basic roles of objective and eyepiece lenses in microscopes and telescopes"
            ),
            listOf(
                "Converging and diverging lenses" to "A convex lens is thicker at the centre and converges parallel rays towards a real principal focus. A concave lens is thinner at the centre and diverges parallel rays as if they came from a virtual focus. The focal length is the distance from the optical centre to the principal focus in the thin-lens model.",
                "Lens power" to "Lens power P = 1/f when focal length f is measured in metres. The unit is the dioptre (D), equivalent to m⁻¹. A converging lens has positive power under the usual convention; a diverging lens has negative power. For example, a converging lens of focal length 0.50 m has power +2.0 D.",
                "Image formation and the camera" to "A simple camera uses a converging lens to form a real, inverted image on a sensor or film. Focusing changes the lens-to-sensor separation or an internal lens arrangement so the image is sharp. A real image can be formed on a screen; a virtual image cannot be projected onto a screen in the same way.",
                "The human eye" to "The cornea and crystalline lens refract light to focus an image on the retina. The ciliary muscles change the curvature of the lens during accommodation. The near point is the closest distance at which the eye can focus comfortably; the far point is the most distant point seen clearly without accommodation. For a normal young adult, a conventional near-point value is about 25 cm, but it varies with age and individual.",
                "Common sight defects" to "Myopia (short-sightedness) makes distant objects appear blurred because the image tends to form in front of the retina; a diverging lens is used for correction. Hyperopia (long-sightedness) makes near objects difficult to focus because the image tends to form behind the retina; a converging lens is used. Astigmatism involves unequal focusing in different meridians and is commonly corrected with a cylindrical lens.",
                "Microscopes and telescopes" to "A compound microscope uses an objective lens to form an enlarged intermediate image and an eyepiece to magnify that image for the eye. An astronomical refracting telescope uses an objective lens to form an image of a distant object and an eyepiece to view it. Instruments are designed around focal lengths, aperture, magnification and image clarity; magnification alone does not guarantee a better image."
            ),
            "A converging lens has a focal length of 0.50 m. What is its power?",
            listOf("0.5 D", "1.0 D", "2.0 D", "5.0 D"), 2,
            "P = 1/f = 1/0.50 = +2.0 dioptres. The positive sign indicates a converging lens under the usual convention."
        ),
        LearningTopic(
            "phy-dispersion", "Dispersion of light and colours", "PHY",
            "A glass prism can spread white light into a band of colours. The prism does not create the colours; white light already contains a range of visible wavelengths. The glass refracts different wavelengths by slightly different amounts, separating them into a spectrum. This is called dispersion.",
            listOf(
                "Explain dispersion using wavelength-dependent refractive index",
                "Identify the order of colours in a visible spectrum",
                "Compare the deviation of red and violet light in a glass prism",
                "Distinguish additive and subtractive colour mixing",
                "Explain how filters and coloured objects affect observed colour"
            ),
            listOf(
                "Why a prism separates colours" to "The refractive index of glass depends slightly on the wavelength of light. Different wavelengths therefore travel at different speeds in the glass and refract by different amounts at the prism surfaces. This separation of white light into its component colours is dispersion. A triangular prism produces a visible spectrum when white light enters it under suitable conditions.",
                "Order and deviation of colours" to "The visible spectrum is commonly listed as red, orange, yellow, green, blue, indigo and violet (ROYGBIV). In ordinary glass under normal dispersion, violet light is refracted and deviated more than red light. Red is deviated least. The exact spread depends on the material and prism geometry.",
                "A spectrum is continuous" to "A prism can spread white light into a continuous range of visible wavelengths. The colours blend into one another; the familiar colour names divide the continuum into convenient regions. A line spectrum from an excited gas is different: it contains discrete wavelengths characteristic of the atoms or ions present.",
                "Additive colour mixing" to "Additive mixing combines light. Red, green and blue are the primary additive colours used in displays. Combining red and green light gives yellow; combining all three at suitable intensities gives white. The result depends on the relative intensities and the display or viewing conditions.",
                "Subtractive mixing, filters and objects" to "Subtractive mixing involves pigments or filters that absorb selected wavelengths and reflect or transmit the rest. A red filter mainly transmits red light and absorbs much of the other visible light. A red object appears red in white light because it reflects red wavelengths more strongly; under blue illumination it may appear very dark because little red light is available to reflect.",
                "Common misconceptions" to "A prism does not split white light because each colour has a different brightness; it separates wavelengths because the refractive index varies with wavelength. Violet is not the fastest colour in glass: in ordinary dispersive glass it travels more slowly than red. The observed colour of an object depends on the light illuminating it as well as the wavelengths the object reflects or transmits."
            ),
            "In ordinary glass, which visible colour is deviated most by a prism?",
            listOf("Red", "Yellow", "Green", "Violet"), 3,
            "Violet light has a shorter wavelength and is refracted more strongly than red light in ordinary glass under normal dispersion."
        )
    )
}
