package com.safe_buddy.safebuddy.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.safe_buddy.safebuddy.ui.Routes
import com.safe_buddy.safebuddy.ui.composables.CircleUrlImage
import com.safe_buddy.safebuddy.ui.model.BottomNavItem
import com.safe_buddy.safebuddy.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel,
    onSignOut: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val bottomNavController = rememberNavController()

    Log.d("USER PHOTO URL", "url is:" + uiState.value.photoUrl.toString())

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        HomeScreenTopBar(
            title = uiState.value.userName ?: "SafeBuddy",
            navigationIcon = {
                uiState.value.photoUrl?.let {
                    CircleUrlImage(
                        imageUrl = it
                    )
                }
            },
            onClickNavigationIcon = { navController.navigate(Routes.ProfilePage.name) },
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
    },
        bottomBar = {
            HomeScreenBottomBar(
                bottomNavItems = listOf(
                    BottomNavItem(
                        route = Routes.HomePage.name,
                        title = "Home",
                        selectedIcon = Icons.Default.Home,
                        unselectedIcon = Icons.Outlined.Home,
                    ),
                    BottomNavItem(
                        route = Routes.ShopPage.name,
                        title = "Shop",
                        selectedIcon = Icons.Default.Store,
                        unselectedIcon = Icons.Outlined.Store,
                    ),
                    BottomNavItem(
                        route = Routes.PaymentPage.name,
                        title = "Payment",
                        selectedIcon = Icons.Default.Payments,
                        unselectedIcon = Icons.Outlined.Payments,
                    ),
                    BottomNavItem(
                        route = Routes.MorePage.name,
                        title = "More",
                        selectedIcon = Icons.Default.Menu,
                        unselectedIcon = Icons.Outlined.MoreVert,
                    ),
                ),
                navController = bottomNavController,
                onItemClick = {
                    bottomNavController.navigate(it.route) {
                        bottomNavController.popBackStack() // to allow user to close the app on navigating back
                    }
                }
            )
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            NavHost(navController = bottomNavController, startDestination = Routes.HomePage.name) {
                composable(Routes.HomePage.name) {
                    HomePage()
                }
                composable(Routes.ShopPage.name) {
                    ShopPage()
                }
                composable(Routes.PaymentPage.name) {
                    PaymentPage()
                }
                composable(Routes.MorePage.name) {
                    MorePage()
                }

            }


        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        viewModel = viewModel<HomeScreenViewModel>()
    ) {}
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
    navController: NavHostController,
    onItemClick: (BottomNavItem) -> Unit,
) {


    val backStackEntry = navController.currentBackStackEntryAsState()


    NavigationBar {
        bottomNavItems.forEachIndexed { index, bottomNavItem ->
            val isSelected = bottomNavItem.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onItemClick(bottomNavItem)
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
                            imageVector = if (isSelected) bottomNavItem.selectedIcon
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
                route = Routes.HomePage.name,
                title = "Home",
                selectedIcon = Icons.Default.Home,
                unselectedIcon = Icons.Outlined.Home,
            ),
            BottomNavItem(
                route = Routes.ShopPage.name,
                title = "Shop",
                selectedIcon = Icons.Default.Store,
                unselectedIcon = Icons.Outlined.Store,
            ),
            BottomNavItem(
                route = Routes.PaymentPage.name,
                title = "Payment",
                selectedIcon = Icons.Default.Payments,
                unselectedIcon = Icons.Outlined.Payments,
            ),
            BottomNavItem(
                route = Routes.MorePage.name,
                title = "More",
                selectedIcon = Icons.Default.Menu,
                unselectedIcon = Icons.Outlined.MoreVert,
            ),
        ), navController = rememberNavController(),
        onItemClick = {}
    )
}