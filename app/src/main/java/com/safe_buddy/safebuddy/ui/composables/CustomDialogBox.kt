package com.safe_buddy.safebuddy.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomDialogBox(
    icon: (@Composable () -> Unit)?,
    title: String?,
    text: String?,
    confirmButtonText: String,
    dismissButtonText: String?,

    ) {
    AlertDialog(
        icon = icon,
        title = { if (title != null) Text(text = title) },
        text = { if (text != null) Text(text = text) },
        onDismissRequest = { /*an empty lambda prevents user from dismissing dialog by clicking outside*/ },
        confirmButton = { TextButton(onClick = { }) { Text(text = confirmButtonText) } },
        dismissButton = { if (dismissButtonText != null) TextButton(onClick = { }) { Text(text = dismissButtonText) } }
    )
}

@Preview
@Composable
fun CustomDialogBoxPreview() {
    CustomDialogBox(
        icon = {
            Icon(imageVector = Icons.Outlined.Email, contentDescription = null)
        },
        title = "Email Sent",
        text = "An email containing password reset link has been sent to your email address.",
        confirmButtonText = "Okay",
        dismissButtonText = null,
    )
}