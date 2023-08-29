package com.safe_buddy.safebuddy.ui.models

data class SignInPageStateModel(
    val email: String = "",
    val password: String = "",
    val isSignInBtnLoading: Boolean = false,
    val isGoogleBtnLoading: Boolean = false,
)

data class RegisterPageStateModel(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isSignInBtnLoading: Boolean = false,
    val isGoogleBtnLoading: Boolean = false,
)
