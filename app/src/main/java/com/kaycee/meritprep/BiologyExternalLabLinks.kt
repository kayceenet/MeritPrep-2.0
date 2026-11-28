package com.kaycee.meritprep

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

internal data class BiologyLabResource(
    val title: String,
    val description: String,
    val url: String
)

@Composable
internal fun BiologyExternalLabLinks(
    heading: String,
    resources: List<BiologyLabResource>
) {
    val uriHandler = LocalUriHandler.current
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(heading, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Free browser-based learning resources. They open outside MeritPrep and may require an internet connection.", style = MaterialTheme.typography.bodySmall)
            resources.forEach { resource ->
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(resource.title, fontWeight = FontWeight.SemiBold)
                    Text(resource.description, style = MaterialTheme.typography.bodySmall)
                    TextButton(onClick = { uriHandler.openUri(resource.url) }) {
                        Text("Open virtual lab")
                    }
                }
                if (resource != resources.last()) HorizontalDivider()
            }
        }
    }
}

internal val plantAnatomyLabResources = listOf(
    BiologyLabResource(
        "NC State: Plant Anatomy WebXR Dissections",
        "Free browser-based 3D plant anatomy modules released under CC BY 4.0.",
        "https://vrplants.cals.ncsu.edu/pb250/"
    ),
    BiologyLabResource(
        "Visualize Biology: Plant Tissue Virtual Lab",
        "Compare monocot and dicot root and stem sections and investigate plant tissues.",
        "https://visualizebiology.com/tools/plant-tissues-stem-root/"
    ),
    BiologyLabResource(
        "Visualize Biology: Virtual Microscope",
        "Practise focusing, changing magnification and examining leaf stomata and root-tip slides.",
        "https://visualizebiology.com/tools/virtual-microscope/"
    )
)

internal val photosynthesisLabResources = listOf(
    BiologyLabResource(
        "Harvard LabXchange: Photosynthesis and Cellular Respiration",
        "A free drag-and-drop virtual lab using a BTB indicator and spectrophotometer to investigate photosynthesis and respiration.",
        "https://about.labxchange.org/sim-landing-page/photosynthesis-and-cellular-respiration"
    ),
    BiologyLabResource(
        "Donat: Rate of Photosynthesis Virtual Lab",
        "A free pondweed investigation that changes lamp distance and records oxygen bubble production.",
        "https://www.donatstem.co.rw/biology/simulations"
    ),
    BiologyLabResource(
        "SciSim: Photosynthesis Simulation",
        "An interactive photosynthesis lab in a collection of free browser-based science simulations.",
        "https://scisim.org/"
    )
)
