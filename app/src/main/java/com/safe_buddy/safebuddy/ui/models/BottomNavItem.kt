package com.safe_buddy.safebuddy.ui.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNotification: Boolean = false,
    val badgeCount: Int? = null,
)
