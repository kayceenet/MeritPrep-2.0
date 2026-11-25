package com.kaycee.meritprep
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.*

@Composable
fun PhysicsSupplementalLessonActivity(topicId: String) {
 when(topicId) {
  "phy-measurements" -> MeasurementExplorer()
  "phy-vectors" -> VectorExplorer()
  "phy-friction" -> FrictionExplorer()
  "phy-machines" -> MachineExplorer()
  "phy-pressure", "phy-liquids-rest" -> PressureExplorer()
  "phy-gas-laws", "phy-kinetic-theory" -> GasLawExplorer()
  "phy-heat-transfer" -> HeatTransferExplorer()
  "phy-magnetic-force" -> MagneticForceExplorer()
  "phy-ac-circuits" -> ACExplorer()
  "phy-modern" -> PhotonExplorer()
  "phy-temperature", "phy-heat-quantity", "phy-change-state", "phy-vapours",
  "phy-optical-instruments", "phy-dispersion", "phy-magnetism", "phy-conduction",
  "phy-electronics" -> PhysicsMissingTopicExplorer(topicId)
 }
}
@Composable private fun MeasurementExplorer() {
 var resolution by remember { mutableFloatStateOf(0.1f) }
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Measurement and instrument resolution",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Smallest scale division: " + "%.2f".format(resolution) + " cm"); Slider(resolution,{resolution=it},valueRange=0.01f..1f)
  Text("For an analogue scale, an estimated reading uncertainty is often about half the smallest division: ±" + "%.3f".format(resolution/2) + " cm.")
  Text("Resolution, precision, accuracy and uncertainty are different. Use the instrument specification and method to determine the appropriate uncertainty.")
 } }
}
@Composable private fun VectorExplorer() {
 var magnitude by remember { mutableFloatStateOf(10f) }
 var angle by remember { mutableFloatStateOf(35f) }
 val x=magnitude*cos(Math.toRadians(angle.toDouble()))
 val y=magnitude*sin(Math.toRadians(angle.toDouble()))
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Vector components",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Magnitude: " + "%.1f".format(magnitude)); Slider(magnitude,{magnitude=it},valueRange=0f..30f)
  Text("Angle above positive horizontal: " + angle.toInt() + "°"); Slider(angle,{angle=it},valueRange=0f..90f)
  Text("Horizontal component = " + "%.2f".format(x))
  Text("Vertical component = " + "%.2f".format(y))
  Text("Resultant magnitude check = " + "%.2f".format(sqrt(x*x+y*y)))
  Text("Components depend on the chosen axes and angle reference.")
 } }
}
@Composable private fun FrictionExplorer() {
 var normal by remember { mutableFloatStateOf(50f) }
 var mu by remember { mutableFloatStateOf(0.4f) }
 var applied by remember { mutableFloatStateOf(10f) }
 val limit=mu*normal
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Static friction and impending motion",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Normal reaction: " + normal.toInt() + " N"); Slider(normal,{normal=it},valueRange=5f..150f)
  Text("Coefficient of static friction: " + "%.2f".format(mu)); Slider(mu,{mu=it},valueRange=0.05f..1f)
  Text("Applied force: " + applied.toInt() + " N"); Slider(applied,{applied=it},valueRange=0f..150f)
  Text("Maximum static friction = " + "%.2f".format(limit) + " N")
  Text(if(applied<=limit) "The block remains at rest; static friction adjusts to balance the applied force." else "Applied force exceeds the static-friction limit; motion begins.")
  Text("Kinetic friction is not modelled. Assumes a horizontal surface and constant coefficient.")
 } }
}
@Composable private fun MachineExplorer() {
 var effort by remember { mutableFloatStateOf(100f) }
 var load by remember { mutableFloatStateOf(400f) }
 var vr by remember { mutableFloatStateOf(5f) }
 val ma=load/effort
 val efficiency=100*ma/vr
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Mechanical advantage and efficiency",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Effort: " + effort.toInt() + " N"); Slider(effort,{effort=it},valueRange=20f..300f)
  Text("Load: " + load.toInt() + " N"); Slider(load,{load=it},valueRange=50f..1000f)
  Text("Velocity ratio: " + "%.2f".format(vr)); Slider(vr,{vr=it},valueRange=1f..10f)
  Text("Mechanical advantage = " + "%.2f".format(ma))
  Text("Efficiency = " + "%.1f".format(efficiency) + "%")
  Text("A passive real machine cannot exceed 100% efficiency. If this idealised combination does, the selected values violate the energy constraint.")
 } }
}
@Composable private fun PressureExplorer() {
 var force by remember { mutableFloatStateOf(100f) }
 var area by remember { mutableFloatStateOf(0.02f) }
 var depth by remember { mutableFloatStateOf(2f) }
 val solid=force/area
 val liquid=1000*9.81*depth
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Pressure in solids and liquids",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Force: " + force.toInt() + " N"); Slider(force,{force=it},valueRange=10f..500f)
  Text("Contact area: " + "%.3f".format(area) + " m²"); Slider(area,{area=it},valueRange=0.005f..0.1f)
  Text("Solid pressure F/A = " + "%.0f".format(solid) + " Pa")
  Text("Liquid depth: " + "%.1f".format(depth) + " m"); Slider(depth,{depth=it},valueRange=0f..10f)
  Text("Fresh-water gauge pressure ρgh = " + "%.0f".format(liquid) + " Pa")
  Text("Liquid calculation assumes density 1000 kg m⁻³, g = 9.81 m s⁻² and excludes atmospheric pressure.")
 } }
}
@Composable private fun GasLawExplorer() {
 var temp by remember { mutableFloatStateOf(300f) }
 var volume by remember { mutableFloatStateOf(10f) }
 val index=temp/volume
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Kinetic theory: temperature and volume",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Absolute temperature: " + temp.toInt() + " K"); Slider(temp,{temp=it},valueRange=100f..800f)
  Text("Volume: " + "%.1f".format(volume)); Slider(volume,{volume=it},valueRange=2f..30f)
  Text("At fixed gas amount, pressure is proportional to T/V. Relative index = " + "%.2f".format(index))
  Text("Higher absolute temperature raises mean molecular kinetic energy. Compression increases collision frequency per unit area.")
  Text("This is a proportionality index, not pressure in pascals; real gases can deviate from ideal behaviour.")
 } }
}
@Composable private fun HeatTransferExplorer() {
 var hot by remember { mutableFloatStateOf(80f) }
 var cold by remember { mutableFloatStateOf(20f) }
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Heat transfer and temperature difference",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Hot side: " + hot.toInt() + " °C"); Slider(hot,{hot=it},valueRange=0f..100f)
  Text("Cold side: " + cold.toInt() + " °C"); Slider(cold,{cold=it},valueRange=0f..100f)
  Text("Temperature difference = " + "%.1f".format(abs(hot-cold)) + " °C")
  Text(if(hot>cold) "Net heat transfer is from the hot side to the cold side." else if(cold>hot) "Net heat transfer is from the cold side to the hot side." else "There is no temperature difference, so no net heat transfer in this simple model.")
  Text("This concept model does not calculate watts; it omits geometry, material properties and heat capacity.")
 } }
}
@Composable private fun MagneticForceExplorer() {
 var b by remember { mutableFloatStateOf(0.4f) }
 var current by remember { mutableFloatStateOf(2f) }
 var length by remember { mutableFloatStateOf(0.5f) }
 var angle by remember { mutableFloatStateOf(90f) }
 val force=b*current*length*sin(Math.toRadians(angle.toDouble()))
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Force on a current-carrying conductor",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Flux density: " + "%.2f".format(b) + " T"); Slider(b,{b=it},valueRange=0f..1.5f)
  Text("Current: " + "%.1f".format(current) + " A"); Slider(current,{current=it},valueRange=0f..10f)
  Text("Length: " + "%.2f".format(length) + " m"); Slider(length,{length=it},valueRange=0.1f..2f)
  Text("Angle: " + angle.toInt() + "°"); Slider(angle,{angle=it},valueRange=0f..180f)
  Text("F = BIL sin θ = " + "%.3f".format(force) + " N",style=MaterialTheme.typography.headlineSmall)
  Text("Force is maximum at 90° and zero for parallel/antiparallel alignment. Direction requires Fleming's left-hand rule.")
 } }
}
@Composable private fun ACExplorer() {
 var frequency by remember { mutableFloatStateOf(50f) }
 var rms by remember { mutableFloatStateOf(230f) }
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Alternating current: period and RMS",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Frequency: " + "%.1f".format(frequency) + " Hz"); Slider(frequency,{frequency=it},valueRange=1f..100f)
  Text("RMS voltage: " + "%.0f".format(rms) + " V"); Slider(rms,{rms=it},valueRange=10f..250f)
  Text("Period T = 1/f = " + "%.4f".format(1/frequency) + " s")
  Text("For a sine wave, peak voltage = √2 × RMS = " + "%.1f".format(rms*sqrt(2f)) + " V")
  Text("RMS is the equivalent DC value for the same heating effect in a resistor. Assumes a sinusoidal waveform.")
 } }
}
@Composable private fun PhotonExplorer() {
 var frequency by remember { mutableFloatStateOf(6e14f) }
 val energy=6.62607015e-34*frequency
 Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
  Text("Photon energy and frequency",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
  Text("Frequency: " + "%.2e".format(frequency) + " Hz"); Slider(frequency/1e14f,{frequency=it*1e14f},valueRange=1f..10f)
  Text("Photon energy E = hf = " + "%.3e".format(energy) + " J")
  Text("Higher-frequency photons carry more energy. At fixed frequency, greater intensity means more photons, not greater energy per photon.")
 } }
}


internal val physicsSupplementalActivityTopicIds = setOf(
 "phy-measurements", "phy-vectors", "phy-friction", "phy-machines",
 "phy-pressure", "phy-liquids-rest", "phy-gas-laws", "phy-kinetic-theory",
 "phy-heat-transfer", "phy-magnetic-force", "phy-ac-circuits", "phy-modern",
 "phy-temperature", "phy-heat-quantity", "phy-change-state", "phy-vapours",
 "phy-optical-instruments", "phy-dispersion", "phy-magnetism", "phy-conduction",
 "phy-electronics"
)

@Composable private fun PhysicsMissingTopicExplorer(topicId: String) {
 var a by remember(topicId) { mutableFloatStateOf(25f) }
 var b by remember(topicId) { mutableFloatStateOf(20f) }
 var c by remember(topicId) { mutableFloatStateOf(1f) }
 var material by remember(topicId) { mutableStateOf("Copper") }
 var phase by remember(topicId) { mutableStateOf("Fusion") }
 val title = when(topicId) {
  "phy-temperature" -> "Temperature-scale explorer"
  "phy-heat-quantity" -> "Specific heat capacity laboratory"
  "phy-change-state" -> "Latent heat laboratory"
  "phy-vapours" -> "Relative humidity explorer"
  "phy-optical-instruments" -> "Converging-lens image model"
  "phy-dispersion" -> "Dispersion and wavelength explorer"
  "phy-magnetism" -> "Magnetic field inside a solenoid"
  "phy-conduction" -> "Thermal conduction rate model"
  else -> "Semiconductor diode switch"
 }
 val formula = when(topicId) {
  "phy-temperature" -> "T(K) = \\\\theta(°C) + 273.15"
  "phy-heat-quantity" -> "Q = mc\\\\Delta T"
  "phy-change-state" -> "Q = mL"
  "phy-vapours" -> "RH = \\\\frac{p_v}{p_{sat}} \\\\times 100\\\\%"
  "phy-optical-instruments" -> "\\\\frac{1}{f} = \\\\frac{1}{u} + \\\\frac{1}{v}"
  "phy-dispersion" -> "n(\\\\lambda) = A + \\\\frac{B}{\\\\lambda^2}"
  "phy-magnetism" -> "B = \\\\mu_0 nI"
  "phy-conduction" -> "\\\\frac{Q}{t} = \\\\frac{kA\\\\Delta T}{L}"
  else -> "I \\\\approx \\\\frac{V - 0.7}{R}"
 }
 val output = when(topicId) {
  "phy-temperature" -> "Absolute temperature = \${"%.2f".format(a+273.15f)} K"
  "phy-heat-quantity" -> "Heat transferred = \${"%.0f".format(a*b*c)} J"
  "phy-change-state" -> "Energy required = \${"%.0f".format(a * if(phase=="Fusion") 334000f else 2260000f)} J"
  "phy-vapours" -> "Actual vapour pressure = \${"%.2f".format(a*b/100f)} kPa"
  "phy-optical-instruments" -> {
   val u=b.coerceAtLeast(a+1f)
   "Image distance = \${"%.2f".format(a*u/(u-a))} cm; magnification = \${"%.2f".format((a*u/(u-a))/u)}"
  }
  "phy-dispersion" -> {
   val micrometres=a/1000f
   val n=1.5046f+0.0042f/(micrometres*micrometres)
   "Approximate refractive index = \${"%.4f".format(n)}"
  }
  "phy-magnetism" -> "Flux density = \${"%.3f".format(4f*PI.toFloat()*1e-7f*a*b*1000f)} mT"
  "phy-conduction" -> {
   val k=when(material) { "Copper"->400f; "Aluminium"->205f; "Glass"->1f; else->.12f }
   "Heat-transfer rate = \${"%.2f".format(k*a*b/c)} W"
  }
  else -> "Estimated forward current = \${"%.2f".format(if(a>.7f) (a-.7f)/b*1000f else 0f)} mA"
 }
 val primaryLabel = when(topicId) {
  "phy-temperature" -> "Temperature in degrees Celsius"
  "phy-heat-quantity" -> "Mass (kg)"
  "phy-change-state" -> "Mass changing state (kg)"
  "phy-vapours" -> "Saturated vapour pressure (kPa)"
  "phy-optical-instruments" -> "Focal length (cm)"
  "phy-dispersion" -> "Wavelength (nm)"
  "phy-magnetism" -> "Current (A)"
  "phy-conduction" -> "Cross-sectional area (m²)"
  else -> "Applied voltage (V)"
 }
 val primaryRange = when(topicId) {
  "phy-temperature" -> -100f..200f
  "phy-heat-quantity" -> .1f..5f
  "phy-change-state" -> .1f..2f
  "phy-vapours" -> .5f..6f
  "phy-optical-instruments" -> 5f..20f
  "phy-dispersion" -> 400f..700f
  "phy-magnetism" -> 0f..5f
  "phy-conduction" -> .001f..0.05f
  else -> -5f..5f
 }
 val secondLabel = when(topicId) {
  "phy-heat-quantity" -> "Specific heat capacity (J kg⁻¹ K⁻¹)"
  "phy-vapours" -> "Relative humidity (%)"
  "phy-optical-instruments" -> "Object distance (cm)"
  "phy-magnetism" -> "Turns per metre (m⁻¹)"
  "phy-conduction" -> "Temperature difference (K)"
  else -> "Series resistance (Ω)"
 }
 val secondRange = when(topicId) {
  "phy-heat-quantity" -> 400f..4200f
  "phy-vapours" -> 0f..100f
  "phy-optical-instruments" -> 21f..100f
  "phy-magnetism" -> 100f..2000f
  "phy-conduction" -> 1f..100f
  else -> 100f..1000f
 }
 Card(Modifier.fillMaxWidth()) {
  Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
   Text(title,style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
   Text(primaryLabel + ": " + "%.2f".format(a))
   Slider(a,{a=it},valueRange=primaryRange)
   if(topicId in setOf("phy-heat-quantity","phy-vapours","phy-optical-instruments","phy-magnetism","phy-conduction","phy-electronics")) {
    Text(secondLabel + ": " + "%.2f".format(b))
    Slider(b,{b=it},valueRange=secondRange)
   }
   if(topicId=="phy-heat-quantity" || topicId=="phy-conduction") {
    val label=if(topicId=="phy-heat-quantity") "Temperature change (K)" else "Thickness (m)"
    val range=if(topicId=="phy-heat-quantity") 1f..100f else .005f..0.1f
    Text(label + ": " + "%.3f".format(c))
    Slider(c,{c=it},valueRange=range)
   }
   if(topicId=="phy-change-state") {
    Row(horizontalArrangement=Arrangement.spacedBy(6.dp)) {
     FilterChip(phase=="Fusion",{phase="Fusion"},{Text("Fusion")})
     FilterChip(phase=="Vaporisation",{phase="Vaporisation"},{Text("Vaporisation")})
    }
   }
   if(topicId=="phy-conduction") {
    Row(horizontalArrangement=Arrangement.spacedBy(5.dp)) {
     listOf("Copper","Aluminium","Glass","Wood").forEach { FilterChip(material==it,{material=it},{Text(it)}) }
    }
   }
   EquationDisplayCard(formula,caption="GOVERNING RELATIONSHIP")
   Text(output,style=MaterialTheme.typography.titleMedium,fontWeight=FontWeight.Bold,color=MaterialTheme.colorScheme.primary)
   Text(when(topicId) {
    "phy-temperature" -> "Kelvin is an absolute scale; an interval of 1 K equals an interval of 1 °C."
    "phy-heat-quantity" -> "Assumes no phase change and negligible heat exchange with the surroundings."
    "phy-change-state" -> "During a phase change at constant pressure, temperature remains approximately constant while latent energy changes the state."
    "phy-vapours" -> "At fixed temperature, saturation vapour pressure is the equilibrium maximum."
    "phy-optical-instruments" -> "Assumes a thin converging lens and an object beyond its focal point; sign conventions are suppressed."
    "phy-dispersion" -> "The Cauchy constants are illustrative for transparent glass; actual refractive index depends on material and wavelength."
    "phy-magnetism" -> "Assumes a long air-cored solenoid and a nearly uniform field near its centre."
    "phy-conduction" -> "Assumes steady one-dimensional conduction and approximate material conductivity values."
    else -> "Piecewise silicon-diode teaching model; reverse leakage, breakdown and the exponential diode equation are omitted."
   },style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant)
  }
 }
}
