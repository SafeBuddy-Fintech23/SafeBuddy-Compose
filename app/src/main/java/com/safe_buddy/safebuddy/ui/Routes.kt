package com.safe_buddy.safebuddy.ui

sealed class Routes(val name: String) {
    object SignInPage : Routes("sign_in_page")
    object RegisterPage : Routes("register_page")
    object HomeScreen: Routes("home_screen")

    object ForgotPasswordPage: Routes("forgot_password_page")
}