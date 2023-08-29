package com.safe_buddy.safebuddy.ui.sign_in_with_google


data class GoogleSignInResult(
    val data: UserData?,
    val errorMessage: String?,

    )

data class UserData (
    val userID: String,
    val displayName: String?,
    val photoURL: String?,
)