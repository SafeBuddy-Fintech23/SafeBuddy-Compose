package com.safe_buddy.safebuddy.ui.pages

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safe_buddy.safebuddy.R
import com.safe_buddy.safebuddy.ui.composables.GoogleSignInButton
import com.safe_buddy.safebuddy.ui.composables.PasswordTextField

@Composable
fun SignInPage() {
    Column(
        Modifier
            .fillMaxHeight()
            .width(600.dp)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
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
                text = "Sign Up",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                ),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable { },
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = "", modifier = Modifier.fillMaxWidth(), onValueChange = {})
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(modifier = Modifier.fillMaxWidth())
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
                    .clickable { }
                    .padding(horizontal = 4.dp),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        var isLoading by rememberSaveable {
            mutableStateOf(false)
        }
        OutlinedButton(
            onClick = { isLoading = !isLoading /*TODO*/ },
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
        ) {
            Text(text = "Sign In", fontSize = 18.sp)
            if (isLoading) Spacer(modifier = Modifier.width(16.dp))
            if (isLoading) CircularProgressIndicator(
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        GoogleSignInButton(modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInPagePreview() {
    SignInPage()
}