package com.example.last25kg.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TrajectoryDisplayScreen(navController: NavHostController) {
    val isPlaying = remember { mutableStateOf(false) }
    val currentTime = remember { mutableStateOf(0.0) }
    val totalTime = remember { mutableStateOf(10.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "轨迹展示",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* 更多选项 */ }) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.MoreVert,
                    contentDescription = "更多选项"
                )
            }
        }

        // Video Player Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(9f / 16f)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            // Video Placeholder
            Text(text = "🎥 视频播放区域", fontSize = 24.sp)

            // Trajectory Overlay
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path()
                path.moveTo(0f, size.height)
                path.lineTo(size.width / 4f, size.height * 0.7f)
                path.lineTo(size.width / 2f, size.height * 0.4f)
                path.lineTo(size.width * 3f / 4f, size.height * 0.7f)
                path.lineTo(size.width, size.height)
                drawPath(
                    path = path,
                    color = Color.Red,
                    style = Stroke(width = 4f)
                )

                // Simulated Barbell Dots
                drawCircle(
                    color = Color.Green,
                    radius = 10f,
                    center = Offset(size.width / 2f, size.height * 0.4f)
                )
            }
        }

        // Playback Controls
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Progress Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "00:00", fontSize = 12.sp)
                Slider(
                    value = currentTime.value.toFloat(),
                    onValueChange = { currentTime.value = it.toDouble() },
                    valueRange = 0f..totalTime.value.toFloat(),
                    modifier = Modifier.weight(1f)
                )
                Text(text = "00:10", fontSize = 12.sp)
            }

            // Control Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(onClick = { /* 上一帧 */ }) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.SkipPrevious,
                        contentDescription = "上一帧"
                    )
                }
                IconButton(
                    onClick = { isPlaying.value = !isPlaying.value },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = if (isPlaying.value) {
                            androidx.compose.material.icons.Icons.Default.Pause
                        } else {
                            androidx.compose.material.icons.Icons.Default.PlayArrow
                        },
                        contentDescription = if (isPlaying.value) "暂停" else "播放",
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = { /* 下一帧 */ }) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.SkipNext,
                        contentDescription = "下一帧"
                    )
                }
                IconButton(onClick = { /* 重置 */ }) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.RestartAlt,
                        contentDescription = "重置"
                    )
                }
            }
        }

        // Trajectory Graph Preview
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "轨迹图预览",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val path = Path()
                        path.moveTo(0f, size.height * 0.8f)
                        path.lineTo(size.width * 0.2f, size.height * 0.5f)
                        path.lineTo(size.width * 0.4f, size.height * 0.2f)
                        path.lineTo(size.width * 0.6f, size.height * 0.5f)
                        path.lineTo(size.width * 0.8f, size.height * 0.8f)
                        path.lineTo(size.width, size.height * 0.5f)
                        drawPath(
                    path = path,
                    color = Color.Red,
                    style = Stroke(width = 3f)
                )
                    }
                }
            }
        }

        // Bottom Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Text(text = "返回")
            }
            Button(
                onClick = { navController.navigate("metricsDisplay") },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "查看指标")
            }
        }
    }
}
