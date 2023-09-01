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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.safe_buddy.safebuddy.ui.models.BottomNavItem

@Composable
fun HomeScreen(navController: NavHostController, onSignOut: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        HomeScreenTopBar(title = "username", navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.AccountCircle, contentDescription = "profile"
            )
        }, onClickNavigationIcon = {}, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart, contentDescription = "options"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications, contentDescription = "options"
                )
            }
        })
    },
        bottomBar = {
            HomeScreenBottomBar(
                bottomNavItems = listOf(
                    BottomNavItem(
                        title = "Home",
                        selectedIcon = Icons.Default.Home,
                        unselectedIcon = Icons.Outlined.Home,
                    ),
                    BottomNavItem(
                        title = "Shop",
                        selectedIcon = Icons.Default.Store,
                        unselectedIcon = Icons.Outlined.Store,
                    ),
                    BottomNavItem(
                        title = "Payment",
                        selectedIcon = Icons.Default.Payments,
                        unselectedIcon = Icons.Outlined.Payments,
                    ),
                    BottomNavItem(
                        title = "More",
                        selectedIcon = Icons.Default.Menu,
                        unselectedIcon = Icons.Outlined.MoreVert,
                    ),
                ),
            )
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
    HomeScreenTopBar(title = "username", navigationIcon = {
        Icon(
            imageVector = Icons.Outlined.AccountCircle, contentDescription = "profile"
        )
    }, onClickNavigationIcon = {}, actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "options")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "options")
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenBottomBar(
    bottomNavItems: List<BottomNavItem>,
) {

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        bottomNavItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    // navController.navigate()
                },
                icon = {
                    BadgedBox(badge = {
                        if (bottomNavItem.hasNotification) {
                            Badge()
                        }

                        if (bottomNavItem.badgeCount != null) {
                            Badge {
                                Text(text = bottomNavItem.badgeCount.toString())
                            }
                        }

                    }) {
                        Icon(
                            imageVector = if (selectedItemIndex == index) bottomNavItem.selectedIcon
                            else bottomNavItem.unselectedIcon,
                            contentDescription = bottomNavItem.title
                        )
                    }
                },
                label = { Text(text = bottomNavItem.title) }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenBottomBarPreview() {
    HomeScreenBottomBar(
        bottomNavItems = listOf(
            BottomNavItem(
                title = "Home",
                selectedIcon = Icons.Default.Home,
                unselectedIcon = Icons.Outlined.Home,
            ),
            BottomNavItem(
                title = "Shop",
                selectedIcon = Icons.Default.Store,
                unselectedIcon = Icons.Outlined.Store,
            ),
            BottomNavItem(
                title = "Payment",
                selectedIcon = Icons.Default.Payments,
                unselectedIcon = Icons.Outlined.Payments,
            ),
            BottomNavItem(
                title = "More",
                selectedIcon = Icons.Default.Menu,
                unselectedIcon = Icons.Outlined.MoreVert,
            ),
        ),

        )
}