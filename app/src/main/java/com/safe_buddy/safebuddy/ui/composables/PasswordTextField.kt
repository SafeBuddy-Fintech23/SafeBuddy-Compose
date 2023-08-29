package com.safe_buddy.safebuddy.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.safe_buddy.safebuddy.R

@Composable
fun PasswordTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier,
    label: String = "Password",
    errorText: String = "Password Required",
    showError: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    onNext: (KeyboardActionScope.() -> Unit)? = null,
) {
    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }

    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = label)
        },
        singleLine = true,
        visualTransformation =
        if (showPassword) VisualTransformation.None
        else  PasswordVisualTransformation(),
        isError = showError,
        supportingText = if (showError) {
            { Text(text = errorText) }
        } else null,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock, contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    painter = painterResource(
                        id = if (showPassword) R.drawable.outline_visibility_off_24
                        else R.drawable.outline_visibility_24
                    ), contentDescription = if (showPassword) "hide password"
                    else "show password"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            autoCorrect = false,
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onNext = onNext,
            onDone = onDone,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    var password by remember { mutableStateOf("Sam") }

    PasswordTextField(
        value = password, onValueChange = { password = it }, modifier = Modifier.padding(8.dp)
    )
}