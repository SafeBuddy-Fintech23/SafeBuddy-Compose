package com.safe_buddy.safebuddy.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfilePage(navController: NavController) {
    Scaffold(topBar = { ProfilePageTopAppbar(navController = navController) }) {
        Column(Modifier.padding(it)) {
            Text(text = "Profile Page")
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