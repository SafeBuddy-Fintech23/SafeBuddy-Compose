package com.safe_buddy.safebuddy.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CreditScore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.HomeWork
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.PersonOff
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.safe_buddy.safebuddy.ui.composables.CircleUrlImage

@Composable
fun ProfilePage(navController: NavController) {
    Scaffold(topBar = { ProfilePageTopAppbar(navController = navController) }) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                ProfilePageHeader(
                    "",
                    "username",
                    "username@gmail.com"
                )
                Spacer(modifier = Modifier.height(16.dp))
                ProfileBodyItem(iconData = Icons.Outlined.History, label = "History")
                ProfileBodyItem(iconData = Icons.Outlined.LocalOffer, label = "Offers & Rewards")
                ProfileBodyItem(iconData = Icons.Outlined.FavoriteBorder, label = "Saved Products")
                ProfileBodyItem(iconData = Icons.Outlined.Settings, label = "Settings")
                ProfileBodyItem(
                    iconData = Icons.Outlined.CreditScore,
                    label = "Credit Limit & Eligibility"
                )
                ProfileBodyItem(iconData = Icons.Outlined.Payment, label = "Add Payment Method")
                ProfileBodyItem(iconData = Icons.Outlined.HomeWork, label = "Saved Addresses")
                ProfileBodyItem(iconData = Icons.Outlined.HelpOutline, label = "Help & Support")
            }
            Column {
                ProfileBodyItem(
                    iconData = Icons.Outlined.Logout,
                    label = "Log Out",
                    leadingIconColor = Color.Red,
                    headlineColor = Color.Red,
                )
                ProfileBodyItem(
                    iconData = Icons.Outlined.PersonOff,
                    label = "Delete Account",
                    leadingIconColor = Color.Red,
                    headlineColor = Color.Red,
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilePagePreview() {
    ProfilePage(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePageTopAppbar(navController: NavController) {
    CenterAlignedTopAppBar(title = { Text(text = "Profile") }, navigationIcon = {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
    })
}

@Preview
@Composable
fun ProfilePageTopAppbarPreview() {
    ProfilePageTopAppbar(rememberNavController())
}

@Composable
fun ProfilePageHeader(
    photoUrl: String,
    userName: String,
    email: String,
    profileImageSize: Dp = 60.dp
) {
    ListItem(
        leadingContent = { CircleUrlImage(imageUrl = photoUrl, size = profileImageSize) },
        headlineContent = { Text(text = userName, fontSize = 18.sp) },
        supportingContent = { Text(text = email) })
}

@Preview
@Composable
fun ProfilePageHeaderPreview() {
    ProfilePageHeader(
        "",
        "username",
        "username@gmail.com"
    )
}

@Composable
fun ProfileBodyItem(
    modifier: Modifier = Modifier,
    iconData: ImageVector,
    label: String,
    headlineColor: Color = MaterialTheme.typography.headlineSmall.color,
    leadingIconColor: Color = Color.Unspecified,
    onClick: (() -> Unit)? = null
) {
    ListItem(
        modifier = Modifier
            .clickable {
                if (onClick != null) {
                    onClick()
                }
            }
            .then(modifier),
        leadingContent = {
            Icon(imageVector = iconData, contentDescription = null)
        }, headlineContent = { Text(text = label) },
        colors = ListItemDefaults.colors(
            headlineColor = headlineColor,
            leadingIconColor = leadingIconColor,
        )
    )
}

@Preview
@Composable
fun ProfileBodyItemPreview() {
    ProfileBodyItem(iconData = Icons.Outlined.History, label = "History")
}