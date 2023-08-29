package com.safe_buddy.safebuddy.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.safe_buddy.safebuddy.ui.models.SignInPageStateModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignInViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(SignInPageStateModel())
    val uiState: StateFlow<SignInPageStateModel> = _uiState.asStateFlow()

     var emailErrorText by mutableStateOf("Email is required")
    var passwordErrorText by mutableStateOf("Password is required")

    var showEmailError by mutableStateOf(false)
    var showPasswordError by mutableStateOf(false)

    fun updateEmailText(value: String) {
        _uiState.update { currentSate -> currentSate.copy(email = value) }
    }

    fun updatePasswordText(value: String) {
        _uiState.update { currentSate -> currentSate.copy(password = value) }
    }

    fun signInWithGoogle() {

    }

    fun signInWithEmailPassword() {

    }

    fun registerWithEmailPassword() {

    }

    fun forgotPassword() {

    }
}