package com.example.last25kg.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ExportScreen(navController: NavHostController) {
    val selectedExportOption = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "导出训练结果",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "选择要导出的内容，支持视频、截图和指标报告",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Main Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Export Options
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Video with Trajectory Option
                ExportOptionCard(
                    icon = "🎬",
                    title = "带轨迹的视频",
                    description = "导出包含轨迹叠加的完整训练视频",
                    isSelected = selectedExportOption.value == "video",
                    onClick = { selectedExportOption.value = "video" }
                )

                // Screenshot with Trajectory Option
                ExportOptionCard(
                    icon = "📸",
                    title = "轨迹截图",
                    description = "导出包含轨迹的关键帧截图",
                    isSelected = selectedExportOption.value == "screenshot",
                    onClick = { selectedExportOption.value = "screenshot" }
                )

                // Metrics Report Option
                ExportOptionCard(
                    icon = "📊",
                    title = "指标报告",
                    description = "导出包含所有训练指标的PDF报告",
                    isSelected = selectedExportOption.value == "report",
                    onClick = { selectedExportOption.value = "report" }
                )
            }

            // Export Settings Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "导出设置",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    // Resolution Setting
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "分辨率", fontSize = 14.sp)
                            Text(
                                text = "影响导出文件大小和质量",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        MenuAnchor {
                            // Dropdown Menu for Resolution
                            Button(onClick = { /* 打开分辨率选择菜单 */ }) {
                                Text(text = "1080p")
                                Icon(
                                    imageVector = androidx.compose.material.icons.Icons.Default.ArrowDropDown,
                                    contentDescription = "分辨率选项"
                                )
                            }
                        }
                    }

                    // Quality Setting
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "质量", fontSize = 14.sp)
                            Text(
                                text = "高/中/低，影响文件大小",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Slider(
                            value = 0.8f,
                            onValueChange = { /* 调整质量 */ },
                            valueRange = 0f..1f,
                            modifier = Modifier.width(120.dp)
                        )
                    }
                }
            }
        }

        // Bottom Navigation Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Export Button
            Button(
                onClick = { /* 执行导出 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = MaterialTheme.shapes.medium,
                enabled = selectedExportOption.value != null
            ) {
                Text(text = "开始导出", fontSize = 20.sp)
            }

            // Back Button
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Text(text = "返回")
            }
        }
    }
}

@Composable
fun ExportOptionCard(icon: String, title: String, description: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.large),
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 28.sp)
            }

            // Content
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Selection Indicator
            RadioButton(
                selected = isSelected,
                onClick = onClick
            )
        }
    }
}

@Composable
fun MenuAnchor(content: @Composable () -> Unit) {
    // Simplified menu anchor implementation
    Box {
        content()
    }
}
