package com.safe_buddy.safebuddy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.safe_buddy.safebuddy.ui.Routes
import com.safe_buddy.safebuddy.ui.pages.ForgotPasswordPage
import com.safe_buddy.safebuddy.ui.pages.HomeScreen
import com.safe_buddy.safebuddy.ui.pages.RegisterPage
import com.safe_buddy.safebuddy.ui.pages.SignInPage
import com.safe_buddy.safebuddy.ui.sign_in_with_google.GoogleAuthUiClient
import com.safe_buddy.safebuddy.ui.theme.SafeBuddyTheme
import com.safe_buddy.safebuddy.ui.viewmodels.HomeScreenViewModel
import com.safe_buddy.safebuddy.ui.viewmodels.SignInViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUserSignedIn: () -> Boolean = { FirebaseAuth.getInstance().currentUser != null }

        setContent {
            SafeBuddyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = if (isUserSignedIn()) Routes.HomeScreen.name else Routes.SignInPage.name
                    ) {
                        composable(Routes.SignInPage.name) {
                            val viewModel = viewModel<SignInViewModel>()
                            val uiState = viewModel.uiState.collectAsState()

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = {
                                    if (it.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInByIntent(
                                                intent = it.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = uiState.value.isSuccessful, block = {
                                if (uiState.value.isSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in Successful",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    navController.navigate(Routes.HomeScreen.name) {
                                        launchSingleTop = true
                                        popUpTo(route = Routes.SignInPage.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            })

                            SignInPage(
                                navController,
                                viewModel = viewModel, onSignInWithGoogle = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                })
                        }

                        composable(Routes.RegisterPage.name) {
                            val viewModel = viewModel<SignInViewModel>()
                            val uiState = viewModel.uiState.collectAsState()

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = {
                                    if (it.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInByIntent(
                                                intent = it.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = uiState.value.isSuccessful, block = {
                                if (uiState.value.isSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Registration Successful",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    navController.navigate(Routes.HomeScreen.name) {
                                        launchSingleTop = true
                                        popUpTo(route = Routes.RegisterPage.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            })

                            RegisterPage(navController,
                                viewModel = viewModel, onSignInWithGoogle = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                })
                        }

                        composable(Routes.HomeScreen.name) {
                            val viewModel = viewModel<HomeScreenViewModel>()
                            HomeScreen(
                                navController = navController,
                                viewModel = viewModel
                            ) {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed out",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate(Routes.SignInPage.name) {
                                        launchSingleTop = true
                                        popUpTo(route = Routes.HomeScreen.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }

                        composable(Routes.ForgotPasswordPage.name) {
                            ForgotPasswordPage(
                                navController = navController,
                                viewModel = viewModel<SignInViewModel>()
                            )
                        }
                    }
                }
            }
        }
    }
}
