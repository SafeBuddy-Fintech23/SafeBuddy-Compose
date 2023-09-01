package com.safe_buddy.safebuddy.ui.model

data class SignInPageStateModel(
    val email: String = "",
    val password: String = "",
    val isSignInBtnLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val signInError: String? = null,
    val isPasswordResetSend: Boolean = false,
)