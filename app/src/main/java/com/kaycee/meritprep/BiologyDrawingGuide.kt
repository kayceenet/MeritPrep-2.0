package com.kaycee.meritprep

data class BiologyDrawingCriterion(
    val id: String,
    val title: String,
    val rationale: String
)

object BiologyDrawingGuide {
    val criteria = listOf(
        BiologyDrawingCriterion("large", "Make the drawing large and clear", "Use enough of the page to show the important diagnostic features clearly."),
        BiologyDrawingCriterion("pencil", "Use a sharp pencil and clean lines", "Draw with single, firm lines. Avoid repeated sketching, broken outlines and unnecessary decoration."),
        BiologyDrawingCriterion("no-shading", "Do not shade or colour", "Use clear outlines rather than artistic shading. Add internal details only when they are visible and relevant."),
        BiologyDrawingCriterion("proportion", "Keep proportions accurate", "Compare the relative sizes and positions of structures instead of drawing from memory or guesswork."),
        BiologyDrawingCriterion("observed", "Draw only what you can observe", "Do not add structures simply because they are expected. The drawing should represent the specimen or image supplied."),
        BiologyDrawingCriterion("labels", "Use straight, ruled label lines", "Keep label lines horizontal where possible, avoid crossing them and make each line touch the correct structure."),
        BiologyDrawingCriterion("labels-outside", "Place labels outside the drawing", "Arrange labels neatly around the drawing so that they remain readable and do not obscure the specimen."),
        BiologyDrawingCriterion("title-scale", "Add a useful title and scale information", "Give the drawing a specific title. Include magnification or a scale bar when the task or specimen information requires it.")
    )
}
