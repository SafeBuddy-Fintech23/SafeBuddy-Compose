package com.safe_buddy.safebuddy.ui.models

data class SignInPageStateModel(
    val email: String = "",
    val password: String = "",
    val isSignInBtnLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val signInError: String? = null,
    val isPasswordResetSend: Boolean = false,
)