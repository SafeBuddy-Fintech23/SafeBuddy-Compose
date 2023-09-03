package com.safe_buddy.safebuddy.ui.pages.start

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.safe_buddy.safebuddy.R
import com.safe_buddy.safebuddy.ui.composables.CustomDialogBox
import com.safe_buddy.safebuddy.ui.composables.CustomOutlinedTextField
import com.safe_buddy.safebuddy.ui.viewmodels.SignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordPage(navController: NavHostController, viewModel: SignInViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.value.signInError, block = {
        uiState.value.signInError?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    })

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Forgot Password") }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_back)
                )
            }
        })
    }) {
        Box(Modifier.padding(it)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_round),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit,
                alpha = 0.02f
            )

            Column(
                Modifier
                    .padding(
                        horizontal = 8.dp,
                    )
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Text("Enter your email to get password reset link")
                Spacer(modifier = Modifier.height(8.dp))
                CustomOutlinedTextField(
                    value = uiState.value.email,
                    modifier = Modifier.width(400.dp),
                    label = "Email",
                    onValueChange = { viewModel.updateEmailText(it) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Email, contentDescription = null)
                    },
                    trailingIcon = {
                        if (uiState.value.isSignInBtnLoading) CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(20.dp),
                        )
                        else IconButton(onClick = { viewModel.sendPasswordResetLink() }) {
                            Icon(
                                imageVector = Icons.Outlined.Send,
                                contentDescription = stringResource(R.string.send)
                            )
                        }
                    },
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                    onDone = { viewModel.sendPasswordResetLink() }
                )

                if (viewModel.showDialogEmailSent) {
                    CustomDialogBox(
                        icon = {
                            Icon(imageVector = Icons.Outlined.Email, contentDescription = null)
                        },
                        title = "Email Sent",
                        text = "An email containing password reset link has been sent to your email address.",
                        confirmButtonText = "Okay",
                        dismissButtonText = null,
                        onConfirmClick = {
                            navController.navigateUp()
                        },
                        onDismissClick = { },
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ForgotPasswordPagePreview() {
    ForgotPasswordPage(
        navController = rememberNavController(), viewModel = viewModel<SignInViewModel>()
    )
}

