package com.kaycee.meritprep

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ScienceMediaScreen(onOpenLabs: () -> Unit) {
    val context = LocalContext.current
    var selectedSubject by remember { mutableIntStateOf(0) }
    val subjectCodes = listOf("ALL", "BIO", "CHEM", "PHY")
    val subjectLabels = listOf("All", "Biology", "Chemistry", "Physics")
    val resources = if (selectedSubject == 0) ScienceMediaCatalog.resources
        else ScienceMediaCatalog.forSubject(subjectCodes[selectedSubject])

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Explore visual science", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text("Animations, interactive models and virtual practicals connected to your science topics.")
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("How to use these resources", fontWeight = FontWeight.Bold)
                    Text("Before opening a resource, read the prediction prompt. Explore the original activity, then return to MeritPrep and answer the reflection prompt. These are curated external links, not copied media.")
                    Text("Internet access is required for external resources. Each card includes a local MeritPrep alternative.")
                    OutlinedButton(onClick = onOpenLabs, modifier = Modifier.fillMaxWidth()) {
                        Text("Open MeritPrep virtual laboratories")
                    }
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                subjectLabels.forEachIndexed { index, label ->
                    FilterChip(
                        selected = selectedSubject == index,
                        onClick = { selectedSubject = index },
                        label = { Text(label) }
                    )
                }
            }
        }
        items(resources, key = { it.id }) { resource ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(resource.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text("${resource.provider} · ${resource.mediaType.name.lowercase().replace('_', ' ')}", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    Text(resource.summary)
                    Text("Before you explore", fontWeight = FontWeight.SemiBold)
                    Text(resource.beforeYouStart, style = MaterialTheme.typography.bodyMedium)
                    Text("After you explore", fontWeight = FontWeight.SemiBold)
                    Text(resource.afterYouExplore, style = MaterialTheme.typography.bodyMedium)
                    Text("Offline alternative", fontWeight = FontWeight.SemiBold)
                    Text(resource.offlineAlternative, style = MaterialTheme.typography.bodySmall)
                    Text("Rights: ${resource.licenceName}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Use: ${resource.permittedUse}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Button(
                        onClick = {
                            try {
                                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(resource.sourceUrl)))
                            } catch (_: ActivityNotFoundException) {
                                Toast.makeText(context, "No browser is available to open this resource.", Toast.LENGTH_LONG).show()
                            } catch (_: Exception) {
                                Toast.makeText(context, "Unable to open this resource. Check your connection and try again.", Toast.LENGTH_LONG).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Open original resource")
                    }
                }
            }
        }
        item {
            Text(
                "Rights and attribution are checked at resource level before any media is embedded, downloaded or redistributed in the APK. A free-to-view resource is not automatically free to copy.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
