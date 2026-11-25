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
