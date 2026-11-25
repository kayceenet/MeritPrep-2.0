package com.kaycee.meritprep

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun ClassroomPillarNavigation(
    stage: LessonStage,
    completed: Set<LessonStage>,
    bodyScroll: androidx.compose.foundation.ScrollState,
    onSelect: (LessonStage) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier.fillMaxWidth()) {
        if (maxWidth >= 720.dp) {
            Column(
                Modifier.width(268.dp).fillMaxHeight().padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("SECTIONS", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                LessonStage.entries.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(item.label, fontWeight = FontWeight.SemiBold)
                                Text(if (item in completed) "Completed" else if (index == 0 || LessonStage.entries.take(index).all { it in completed }) "Ready to study" else "Complete earlier section first", style = MaterialTheme.typography.labelSmall)
                            }
                        },
                        selected = stage == item,
                        onClick = { if (index == 0 || LessonStage.entries.take(index).all { it in completed }) onSelect(item) },
                        badge = {
                            Text(if (item in completed) "✓" else if (index == 0 || LessonStage.entries.take(index).all { it in completed }) "›" else "•")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        } else {
            var manualCompact by remember { mutableStateOf<Boolean?>(null) }
            LaunchedEffect(bodyScroll.value) {
                if (bodyScroll.value <= 56 && manualCompact == true) manualCompact = null
            }
            val compact = manualCompact ?: (bodyScroll.value > 56)
            Column(Modifier.fillMaxWidth().padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("LESSON MODES", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    TextButton(onClick = { manualCompact = !compact }) { Text(if (compact) "Expand modes" else "Compact modes") }
                }
                AnimatedVisibility(visible = !compact) {
                    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                        LessonStage.entries.chunked(3).forEach { rowItems ->
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                                rowItems.forEach { item ->
                                    val enabled = LessonStage.entries.take(LessonStage.entries.indexOf(item)).all { it in completed }
                                    Card(
                                        onClick = { if (enabled) onSelect(item) },
                                        modifier = Modifier.weight(1f),
                                        enabled = enabled,
                                        colors = CardDefaults.cardColors(
                                            containerColor = if (stage == item) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                                        )
                                    ) {
                                        Column(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 11.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                            Text(if (item in completed) "✓" else if (enabled) "○" else "🔒", style = MaterialTheme.typography.labelMedium)
                                            Text(item.shortLabel, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold, maxLines = 1)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                AnimatedVisibility(visible = compact) {
                    Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                        LessonStage.entries.forEach { item ->
                            val enabled = LessonStage.entries.take(LessonStage.entries.indexOf(item)).all { it in completed }
                            FilterChip(
                                selected = stage == item,
                                onClick = { if (enabled) onSelect(item) },
                                enabled = enabled,
                                label = { Text((if (item in completed) "✓ " else "") + item.shortLabel) }
                            )
                        }
                    }
                }
            }
        }
    }
}
