package com.safe_buddy.safebuddy.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController, onSignOut: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeScreenTopBar(
                title = "username",
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "profile"
                    )
                },
                onClickNavigationIcon = {},
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = "options"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "options"
                        )
                    }
                })
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(text = "Home Screen")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(onClick = onSignOut) {
                Text(text = "Sign out")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    title: String,
    navigationIcon: @Composable () -> Unit,
    onClickNavigationIcon: () -> Unit,
    actions: @Composable RowScope.() -> Unit,
) {
    TopAppBar(title = { Text(text = title) }, navigationIcon = {
        IconButton(onClick = onClickNavigationIcon, content = {
            navigationIcon()
        })
    }, actions = actions)
}

@Preview
@Composable
fun HomeScreenTopBarPreview() {
    HomeScreenTopBar(
        title = "username",
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "profile"
            )
        },
        onClickNavigationIcon = {},
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "options")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "options")
            }
        })
}