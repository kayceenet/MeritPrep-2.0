package com.kaycee.meritprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

private val subjects = listOf(
    Subject("Mathematics", "Numbers, algebra, geometry and statistics", "MATH"),
    Subject("English Language", "Comprehension, lexis, grammar and oral English", "ENG"),
    Subject("Biology", "Living organisms, ecology and genetics", "BIO"),
    Subject("Chemistry", "Matter, reactions and quantitative chemistry", "CHEM"),
    Subject("Physics", "Mechanics, waves, electricity and modern physics", "PHY"),
    Subject("Economics", "Microeconomics, macroeconomics and development", "ECO"),
    Subject("Government", "Political concepts and Nigerian government", "GOV")
)
private data class Subject(val name:String,val description:String,val code:String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MeritPrepApp() }
    }
}

@Composable
private fun MeritPrepApp() {
    val nav = rememberNavController()
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFF155EEF),
            secondary = androidx.compose.ui.graphics.Color(0xFF0F766E),
            background = androidx.compose.ui.graphics.Color(0xFFF7F8FA)
        )
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    listOf("Home" to "home", "Learn" to "learn", "Practice" to "practice", "Profile" to "profile").forEach { (label, route) ->
                        NavigationBarItem(
                            selected = nav.currentBackStackEntryAsState().value?.destination?.route == route,
                            onClick = { nav.navigate(route) { launchSingleTop = true } },
                            icon = { Icon(when(route) { "home" -> Icons.Default.Home; "learn" -> Icons.Default.MenuBook; "practice" -> Icons.Default.Quiz; else -> Icons.Default.Person }, contentDescription = label) },
                            label = { Text(label) }
                        )
                    }
                }
            }
        ) { padding ->
            NavHost(navController = nav, startDestination = "home", modifier = Modifier.padding(padding)) {
                composable("home") { HomeScreen(nav) }
                composable("learn") { LearnScreen(nav) }
                composable("subject/{code}") { entry ->
                    val code = entry.arguments?.getString("code").orEmpty()
                    SubjectScreen(subjects.firstOrNull { it.code == code } ?: subjects.first(), nav)
                }
                composable("practice") { PracticeScreenV2() }
                composable("profile") { ProfileScreen() }
            }
        }
    }
}

@Composable
private fun HomeScreen(nav: NavHostController) {
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Text("MERITPREP", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Your learning journey starts here.", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Prepare with understanding. Practise with purpose. Improve with evidence.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Your study workspace", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Continue learning, review difficult topics and practise for your examinations.")
                    Button(onClick = { nav.navigate("learn") }) { Text("Explore subjects") }
                }
            }
        }
        item { Text("Study areas", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) }
        items(subjects.take(4)) { subject ->
            SubjectCard(subject) { nav.navigate("subject/${subject.code}") }
        }
    }
}

@Composable
private fun LearnScreen(nav: NavHostController) {
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("Learn", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Choose a subject to explore its topics and lessons.")
        }
        items(subjects) { subject -> SubjectCard(subject) { nav.navigate("subject/${subject.code}") } }
    }
}

@Composable
private fun SubjectCard(subject: Subject, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.large) {
                Text(subject.code, Modifier.padding(12.dp), fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(subject.name, fontWeight = FontWeight.SemiBold)
                Text(subject.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = "Open subject")
        }
    }
}

@Composable
private fun SubjectScreen(subject: Subject, nav: NavHostController) {
    val topics = when(subject.code) {
        "BIO" -> listOf("Cell structure and organisation", "Nutrition", "Transport systems", "Ecology", "Genetics")
        "CHEM" -> listOf("Particulate nature of matter", "Atomic structure", "Chemical bonding", "Stoichiometry", "Acids, bases and salts")
        "PHY" -> listOf("Measurements and units", "Motion", "Forces and equilibrium", "Waves", "Electricity")
        "MATH" -> listOf("Number bases", "Fractions and percentages", "Algebra", "Geometry", "Statistics")
        "ENG" -> listOf("Comprehension", "Summary", "Lexis and structure", "Oral English")
        "ECO" -> listOf("Basic economic concepts", "Demand and supply", "Production", "Market structures")
        else -> listOf("Political concepts", "Constitution", "Arms of government", "Nigerian political development")
    }
    LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text(subject.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Topic catalogue starter. Full syllabus mapping and reviewed lesson content will be added in subsequent releases.")
        }
        items(topics) { topic ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(topic, fontWeight = FontWeight.SemiBold)
                    Text("Lesson content pending academic review", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
private fun PracticeScreen() {
    var selected by remember { mutableIntStateOf(-1) }
    var submitted by remember { mutableStateOf(false) }
    val options = listOf("Mitochondrion", "Ribosome", "Cell wall", "Nucleus")
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Quick practice", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Biology · Cell biology")
        Text("Which cell organelle is the principal site of aerobic respiration?")
        options.forEachIndexed { index, option ->
            OutlinedButton(onClick = { selected = index; submitted = false }, modifier = Modifier.fillMaxWidth()) {
                Text((if(selected == index) "✓  " else "") + option)
            }
        }
        Button(onClick = { submitted = true }, enabled = selected >= 0) { Text("Check answer") }
        if(submitted) {
            val correct = selected == 0
            Text(if(correct) "Correct. The mitochondrion is the main site of aerobic respiration in eukaryotic cells." else "Not quite. Review the function of the mitochondrion, then try again.", color = if(correct) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
        }
        Text("Demonstration question only. Production question banks require source verification and academic review.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun ProfileScreen() {
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Learner profile", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Your study activity and progress will appear here.")
        LinearProgressIndicator(progress = { 0f }, modifier = Modifier.fillMaxWidth())
        Text("No verified learning progress recorded yet.")
    }
}
