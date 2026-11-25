package com.kaycee.meritprep

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

internal enum class ApparatusDiagram {
    MICROSCOPE, PLANT_LIGHT, RESPIROMETER, KIDNEY, QUADRAT, TITRATION, FILTRATION, ELECTROLYSIS,
    SPRING, TROLLEY, FLUID_COLUMN, CIRCUIT, WAVE_TANK, GAS_SYRINGE
}

internal fun apparatusFor(topicId: String): ApparatusDiagram? = when (topicId) {
    "bio-cell" -> ApparatusDiagram.MICROSCOPE
    "bio-nutrition", "bio-plant-anatomy" -> ApparatusDiagram.PLANT_LIGHT
    "bio-respiration" -> ApparatusDiagram.RESPIROMETER
    "bio-excretion" -> ApparatusDiagram.KIDNEY
    "bio-populations", "bio-habitats", "bio-biomes", "bio-environment", "bio-interactions" -> ApparatusDiagram.QUADRAT
    "chem-acids-bases", "chem-redox" -> ApparatusDiagram.TITRATION
    "chem-separation" -> ApparatusDiagram.FILTRATION
    "chem-electrolysis" -> ApparatusDiagram.ELECTROLYSIS
    "phy-elasticity" -> ApparatusDiagram.SPRING
    "phy-motion" -> ApparatusDiagram.TROLLEY
    "phy-pressure", "phy-liquids-rest" -> ApparatusDiagram.FLUID_COLUMN
    "phy-current-electricity", "phy-cells", "phy-electrical-power" -> ApparatusDiagram.CIRCUIT
    "phy-waves", "phy-sound-propagation", "phy-sound-characteristics" -> ApparatusDiagram.WAVE_TANK
    "phy-gas-laws", "phy-kinetic-theory" -> ApparatusDiagram.GAS_SYRINGE
    else -> null
}

@Composable
fun InvestigationApparatusIllustration(topicId: String) {
    val diagram = apparatusFor(topicId) ?: return
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.tertiary
    val outline = MaterialTheme.colorScheme.onSurfaceVariant
    val title = when (diagram) {
        ApparatusDiagram.MICROSCOPE -> "Microscope and prepared slide"
        ApparatusDiagram.PLANT_LIGHT -> "Plant material and light source"
        ApparatusDiagram.RESPIROMETER -> "Respirometer for measuring oxygen uptake"
        ApparatusDiagram.KIDNEY -> "Kidney and nephron filtration pathway"
        ApparatusDiagram.QUADRAT -> "Quadrat sampling frame"
        ApparatusDiagram.TITRATION -> "Burette, clamp and conical flask"
        ApparatusDiagram.FILTRATION -> "Filter funnel and receiving vessel"
        ApparatusDiagram.ELECTROLYSIS -> "Electrolytic cell and DC supply"
        ApparatusDiagram.SPRING -> "Spring, clamp and mass hanger"
        ApparatusDiagram.TROLLEY -> "Dynamics trolley and runway"
        ApparatusDiagram.FLUID_COLUMN -> "Liquid column and pressure probe"
        ApparatusDiagram.CIRCUIT -> "Battery, resistor and measuring circuit"
        ApparatusDiagram.WAVE_TANK -> "Ripple tank and wave source"
        ApparatusDiagram.GAS_SYRINGE -> "Gas syringe and temperature bath"
    }
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Apparatus layout · schematic", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Canvas(Modifier.fillMaxWidth().height(150.dp)) {
                val w = size.width
                val h = size.height
                fun line(x1: Float, y1: Float, x2: Float, y2: Float, color: Color = outline, width: Float = 3f) {
                    drawLine(color, Offset(x1, y1), Offset(x2, y2), strokeWidth = width)
                }
                when (diagram) {
                    ApparatusDiagram.MICROSCOPE -> {
                        line(w*.25f,h*.83f,w*.72f,h*.83f,primary,5f)
                        line(w*.42f,h*.82f,w*.42f,h*.38f,primary,5f)
                        line(w*.42f,h*.4f,w*.57f,h*.2f,primary,5f)
                        line(w*.57f,h*.2f,w*.7f,h*.25f,primary,5f)
                        line(w*.7f,h*.25f,w*.72f,h*.38f,primary,5f)
                        line(w*.48f,h*.43f,w*.73f,h*.43f,secondary,4f)
                        line(w*.56f,h*.43f,w*.56f,h*.56f,secondary,4f)
                        line(w*.72f,h*.38f,w*.76f,h*.48f,primary,4f)
                        line(w*.36f,h*.62f,w*.7f,h*.62f,outline,4f)
                        line(w*.53f,h*.57f,w*.53f,h*.62f,outline,3f)
                        drawCircle(secondary, 10f, Offset(w*.48f,h*.73f))
                    }
                    ApparatusDiagram.PLANT_LIGHT -> {
                        line(w*.48f,h*.82f,w*.48f,h*.35f,primary,5f)
                        val leaf = Path().apply { moveTo(w*.48f,h*.55f); cubicTo(w*.24f,h*.3f,w*.18f,h*.6f,w*.48f,h*.62f); close() }
                        drawPath(leaf, secondary.copy(alpha=.8f))
                        val leaf2 = Path().apply { moveTo(w*.49f,h*.45f); cubicTo(w*.72f,h*.18f,w*.84f,h*.42f,w*.5f,h*.52f); close() }
                        drawPath(leaf2, primary.copy(alpha=.75f))
                        line(w*.34f,h*.83f,w*.63f,h*.83f,outline,4f)
                        line(w*.75f,h*.2f,w*.75f,h*.38f,secondary,5f)
                        line(w*.69f,h*.2f,w*.81f,h*.2f,secondary,5f)
                        line(w*.75f,h*.38f,w*.68f,h*.48f,secondary,4f)
                        line(w*.75f,h*.38f,w*.82f,h*.48f,secondary,4f)
                        line(w*.7f,h*.52f,w*.8f,h*.52f,secondary,3f)
                    }
                    ApparatusDiagram.RESPIROMETER -> {
                        line(w*.12f,h*.24f,w*.36f,h*.24f,outline,4f)
                        line(w*.12f,h*.24f,w*.12f,h*.36f,outline,4f)
                        drawRect(primary,Offset(w*.36f,h*.18f),Size(w*.38f,h*.2f),style=Stroke(4f))
                        line(w*.74f,h*.28f,w*.88f,h*.28f,primary,4f)
                        line(w*.88f,h*.28f,w*.88f,h*.62f,primary,4f)
                        line(w*.82f,h*.62f,w*.94f,h*.62f,secondary,4f)
                        drawCircle(secondary,8f,Offset(w*.88f,h*.62f))
                        drawRect(secondary.copy(alpha=.18f),Offset(w*.18f,h*.48f),Size(w*.64f,h*.32f))
                        drawRect(secondary,Offset(w*.18f,h*.48f),Size(w*.64f,h*.32f),style=Stroke(2f))
                        drawCircle(primary,12f,Offset(w*.5f,h*.64f),style=Stroke(3f))
                    }
                    ApparatusDiagram.KIDNEY -> {
                        val kidney=Path().apply {
                            moveTo(w*.48f,h*.12f); cubicTo(w*.18f,h*.05f,w*.16f,h*.45f,w*.34f,h*.62f)
                            cubicTo(w*.47f,h*.76f,w*.64f,h*.58f,w*.58f,h*.43f)
                            cubicTo(w*.54f,h*.33f,w*.68f,h*.22f,w*.48f,h*.12f); close()
                        }
                        drawPath(kidney,primary.copy(alpha=.16f))
                        drawPath(kidney,primary,style=Stroke(4f))
                        line(w*.58f,h*.42f,w*.76f,h*.52f,secondary,4f)
                        line(w*.76f,h*.52f,w*.76f,h*.9f,secondary,4f)
                        line(w*.36f,h*.58f,w*.66f,h*.72f,outline,3f)
                        line(w*.66f,h*.72f,w*.66f,h*.9f,outline,3f)
                        line(w*.44f,h*.23f,w*.54f,h*.34f,secondary,3f)
                    }
                    ApparatusDiagram.QUADRAT -> {
                        val left=w*.27f; val top=h*.13f; val side=h*.72f
                        drawRect(primary, Offset(left,top), Size(side,side), style=Stroke(5f))
                        for (i in 1..3) {
                            val x=left+side*i/4f; val y=top+side*i/4f
                            line(x,top,x,top+side,secondary,2f)
                            line(left,y,left+side,y,secondary,2f)
                        }
                        for (i in 0..9) drawCircle(secondary,4f,Offset(left+12f+(i%4)*25f,top+16f+(i/4)*30f))
                    }
                    ApparatusDiagram.TITRATION -> {
                        line(w*.28f,h*.1f,w*.28f,h*.85f,outline,4f)
                        line(w*.28f,h*.18f,w*.64f,h*.18f,outline,4f)
                        line(w*.64f,h*.1f,w*.64f,h*.57f,primary,5f)
                        line(w*.61f,h*.1f,w*.67f,h*.1f,primary,4f)
                        line(w*.64f,h*.57f,w*.64f,h*.64f,secondary,3f)
                        line(w*.6f,h*.64f,w*.68f,h*.64f,secondary,3f)
                        val flask=Path().apply { moveTo(w*.52f,h*.67f); lineTo(w*.76f,h*.67f); lineTo(w*.82f,h*.9f); lineTo(w*.46f,h*.9f); close() }
                        drawPath(flask,primary,style=Stroke(3f))
                        line(w*.49f,h*.83f,w*.79f,h*.83f,secondary,3f)
                        line(w*.28f,h*.18f,w*.18f,h*.18f,outline,4f)
                    }
                    ApparatusDiagram.FILTRATION -> {
                        line(w*.32f,h*.15f,w*.68f,h*.15f,outline,4f)
                        val funnel=Path().apply { moveTo(w*.3f,h*.18f); lineTo(w*.7f,h*.18f); lineTo(w*.53f,h*.48f); lineTo(w*.47f,h*.48f); close() }
                        drawPath(funnel,primary,style=Stroke(3f))
                        line(w*.5f,h*.48f,w*.5f,h*.7f,primary,4f)
                        val beaker=Path().apply { moveTo(w*.35f,h*.72f); lineTo(w*.65f,h*.72f); lineTo(w*.62f,h*.92f); lineTo(w*.38f,h*.92f); close() }
                        drawPath(beaker,secondary,style=Stroke(3f))
                        line(w*.39f,h*.84f,w*.61f,h*.84f,secondary,3f)
                    }
                    ApparatusDiagram.ELECTROLYSIS -> {
                        drawRect(primary,Offset(w*.25f,h*.27f),Size(w*.5f,h*.58f),style=Stroke(4f))
                        line(w*.36f,h*.1f,w*.36f,h*.66f,secondary,5f)
                        line(w*.64f,h*.1f,w*.64f,h*.66f,secondary,5f)
                        line(w*.27f,h*.65f,w*.73f,h*.65f,primary,3f)
                        line(w*.36f,h*.1f,w*.36f,h*.03f,outline,3f)
                        line(w*.64f,h*.1f,w*.64f,h*.03f,outline,3f)
                        line(w*.36f,h*.03f,w*.64f,h*.03f,outline,3f)
                        line(w*.36f,h*.03f,w*.36f,h*.0f,outline,3f)
                    }
                    ApparatusDiagram.SPRING -> {
                        line(w*.35f,h*.12f,w*.65f,h*.12f,outline,5f)
                        val spring=Path().apply { moveTo(w*.5f,h*.12f); for(i in 1..12) lineTo(w*(if(i%2==0) .44f else .56f),h*(.12f+i*.045f)); lineTo(w*.5f,h*.68f) }
                        drawPath(spring,primary,style=Stroke(3f))
                        line(w*.5f,h*.68f,w*.5f,h*.75f,secondary,3f)
                        drawRect(secondary,Offset(w*.42f,h*.75f),Size(w*.16f,h*.14f))
                    }
                    ApparatusDiagram.TROLLEY -> {
                        line(w*.12f,h*.78f,w*.88f,h*.78f,outline,5f)
                        drawRect(primary,Offset(w*.32f,h*.45f),Size(w*.28f,h*.22f),style=Stroke(4f))
                        drawCircle(secondary,8f,Offset(w*.38f,h*.7f))
                        drawCircle(secondary,8f,Offset(w*.54f,h*.7f))
                        line(w*.16f,h*.73f,w*.84f,h*.73f,secondary,2f)
                        line(w*.18f,h*.2f,w*.75f,h*.2f,outline,2f)
                        line(w*.75f,h*.2f,w*.69f,h*.16f,outline,2f)
                        line(w*.75f,h*.2f,w*.69f,h*.24f,outline,2f)
                    }
                    ApparatusDiagram.FLUID_COLUMN -> {
                        drawRect(primary,Offset(w*.34f,h*.1f),Size(w*.32f,h*.8f),style=Stroke(4f))
                        line(w*.35f,h*.35f,w*.65f,h*.35f,secondary,3f)
                        line(w*.34f,h*.35f,w*.66f,h*.35f,secondary,2f)
                        line(w*.67f,h*.5f,w*.82f,h*.5f,outline,4f)
                        line(w*.82f,h*.5f,w*.82f,h*.7f,outline,4f)
                        line(w*.82f,h*.7f,w*.72f,h*.7f,outline,4f)
                        line(w*.72f,h*.7f,w*.72f,h*.5f,outline,4f)
                    }
                    ApparatusDiagram.CIRCUIT -> {
                        line(w*.2f,h*.25f,w*.8f,h*.25f,primary,4f)
                        line(w*.8f,h*.25f,w*.8f,h*.76f,primary,4f)
                        line(w*.8f,h*.76f,w*.2f,h*.76f,primary,4f)
                        line(w*.2f,h*.76f,w*.2f,h*.25f,primary,4f)
                        line(w*.42f,h*.25f,w*.42f,h*.13f,secondary,4f)
                        line(w*.48f,h*.25f,w*.48f,h*.17f,secondary,4f)
                        val resistor=Path().apply { moveTo(w*.58f,h*.25f); for(i in 0..6) lineTo(w*(.58f+i*.035f),h*(if(i%2==0).18f else .32f)); lineTo(w*.82f,h*.25f) }
                        drawPath(resistor,secondary,style=Stroke(3f))
                        drawCircle(secondary,6f,Offset(w*.5f,h*.76f))
                    }
                    ApparatusDiagram.WAVE_TANK -> {
                        drawRect(primary,Offset(w*.12f,h*.22f),Size(w*.76f,h*.56f),style=Stroke(4f))
                        for(i in 0..4) {
                            val x=w*(.25f+i*.12f)
                            drawCircle(secondary,12f+i*2f,Offset(x,h*.5f),style=Stroke(2f))
                        }
                        line(w*.18f,h*.15f,w*.18f,h*.82f,outline,4f)
                    }
                    ApparatusDiagram.GAS_SYRINGE -> {
                        line(w*.18f,h*.32f,w*.78f,h*.32f,primary,5f)
                        line(w*.18f,h*.25f,w*.18f,h*.39f,primary,4f)
                        line(w*.78f,h*.22f,w*.78f,h*.42f,primary,4f)
                        line(w*.78f,h*.32f,w*.88f,h*.32f,secondary,4f)
                        line(w*.88f,h*.32f,w*.88f,h*.5f,secondary,4f)
                        drawRect(outline,Offset(w*.32f,h*.22f),Size(w*.06f,h*.2f),style=Stroke(2f))
                        drawRect(secondary.copy(alpha=.16f),Offset(w*.2f,h*.48f),Size(w*.6f,h*.34f))
                        drawRect(secondary,Offset(w*.2f,h*.48f),Size(w*.6f,h*.34f),style=Stroke(2f))
                    }
                }
            }
            Text("Schematic only: apparatus dimensions and arrangement are not to scale. Follow the written method, local risk assessment and teacher instructions.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
