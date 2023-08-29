package com.safe_buddy.safebuddy.ui.pages

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.safe_buddy.safebuddy.R
import com.safe_buddy.safebuddy.ui.Routes
import com.safe_buddy.safebuddy.ui.composables.CustomOutlinedTextField
import com.safe_buddy.safebuddy.ui.composables.GoogleSignInButton
import com.safe_buddy.safebuddy.ui.composables.PasswordTextField
import com.safe_buddy.safebuddy.ui.theme.SafeBuddyTheme
import com.safe_buddy.safebuddy.ui.viewmodels.SignInViewModel

@Composable
fun SignInPage(
    navController: NavController,
    viewModel: SignInViewModel,
    onSignInWithGoogle: () -> Unit
) {
    val signInUIState = viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // This will show error message
    LaunchedEffect(key1 = (signInUIState.value.signInError)) {
        signInUIState.value.signInError?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_round),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            alpha = 0.02f
        )

        Column(
            Modifier
                .fillMaxHeight()
                .width(600.dp)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PageHeader()
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Sign In",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Don't have an account? ")
                Text(
                    text = "Register",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline,
                    ),
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable {
                            navController.navigate(Routes.RegisterPage.name) {
                                launchSingleTop = true
                                popUpTo(route = Routes.SignInPage.name) {
                                    inclusive = true
                                }
                            }
                        },
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = signInUIState.value.email,
                onValueChange = { viewModel.updateEmailText(it) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                label = "Email",
                errorText = viewModel.emailErrorText,
                showError = viewModel.showEmailError,
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = signInUIState.value.password,
                onValueChange = { viewModel.updatePasswordText(it) },
                errorText = viewModel.passwordErrorText,
                showError = viewModel.showPasswordError,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    text = "Forgot password?",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline,
                    ),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Routes.ForgotPasswordPage.name)
                        }
                        .padding(horizontal = 4.dp),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = {
                    viewModel.signInWithEmailPassword()
                }, modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth()
            ) {
                Text(text = "Sign In", fontSize = 18.sp)
                if (signInUIState.value.isSignInBtnLoading) Spacer(modifier = Modifier.width(16.dp))
                if (signInUIState.value.isSignInBtnLoading) CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            GoogleSignInButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSignInWithGoogle
            )
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
)
@Composable
fun SignInPagePreview() {
    SafeBuddyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val viewModel = viewModel<SignInViewModel>()
            SignInPage(
                viewModel = viewModel,
                onSignInWithGoogle = {},
                navController = rememberNavController()
            )
        }
    }
}

@Composable
fun PageHeader() = Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_round),
        contentDescription = null,
        modifier = Modifier.size(200.dp),
        contentScale = ContentScale.Fit
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.app_name),
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        style = TextStyle(
            fontFamily = FontFamily.Cursive, // compose inbuilt
            shadow = Shadow(
                blurRadius = 12f
            ),
        )
    )
    Text(
        text = "Buy now, pay later",
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        style = TextStyle(
            fontFamily = FontFamily.Cursive, // compose inbuilt
            shadow = Shadow(
                blurRadius = 12f
            ),
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PageHeaderPreview() {
    PageHeader()
}