package com.safe_buddy.safebuddy.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String = "",
    errorText: String = "",
    showError: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    onNext: (KeyboardActionScope.() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = label)
        },
        singleLine = true,
        isError = showError,
        supportingText = if (showError) {
            { Text(text = errorText) }
        } else null,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
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
fun CustomOutlinedTextFieldPreview() {
    CustomOutlinedTextField(modifier = Modifier.padding(8.dp))
}