package com.safe_buddy.safebuddy.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.safe_buddy.safebuddy.ui.models.SignInPageStateModel
import com.safe_buddy.safebuddy.ui.sign_in_with_google.GoogleSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignInViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(SignInPageStateModel())
    val uiState = _uiState.asStateFlow()

    var emailErrorText by mutableStateOf("Email is required")
    var passwordErrorText by mutableStateOf("Password is required")

    var showEmailError by mutableStateOf(false)
    var showPasswordError by mutableStateOf(false)

    init {
        resetState()
    }

    private fun resetState() {
        showEmailError = false
        showPasswordError = false
        _uiState.update { SignInPageStateModel() }
    }

    fun updateEmailText(value: String) {
        showEmailError = false
        _uiState.update { currentSate -> currentSate.copy(email = value) }
    }

    fun updatePasswordText(value: String) {
        showPasswordError = false
        _uiState.update { currentSate -> currentSate.copy(password = value) }
    }

    fun onSignInResult(result: GoogleSignInResult) {
        _uiState.update {
            it.copy(
                isSuccessful = result.data != null,
                signInError = result.errorMessage,
            )
        }
    }

    fun signInWithEmailPassword() {
        val email = uiState.value.email
        val password = uiState.value.password

        if (email.length < 6 && password.length < 6) {
            showEmailError = true
            showPasswordError = true
        } else {
            showEmailError = false
            showPasswordError = false

            _uiState.update { it.copy(isSignInBtnLoading = true, signInError = null) }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // on updating the UI state, user will be navigated automatically to the HomeScreen
                        _uiState.update { currentState -> currentState.copy(isSuccessful = true) }
                    }
                    if (it.isCanceled) {
                        _uiState.update { currentState ->
                            currentState.copy(signInError = "Sign in cancelled. Please try again later")
                        }
                    }
                }.addOnFailureListener {
                    _uiState.update { currentState ->
                        currentState.copy(
                            signInError = it.message, // A Toast will show this error
                            isSignInBtnLoading = false,
                        )
                    }
                }
        }

    }

    fun registerWithEmailPassword() {
        val email = uiState.value.email
        val password = uiState.value.password

        if (email.length < 6 && password.length < 6) {
            showEmailError = true
            showPasswordError = true
        } else {
            showEmailError = false
            showPasswordError = false

            _uiState.update { it.copy(isSignInBtnLoading = true, signInError = null) }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // on updating the UI state, user will be navigated automatically to the HomeScreen
                        _uiState.update { currentState -> currentState.copy(isSuccessful = true) }
                    }
                    if (it.isCanceled) {
                        _uiState.update { currentState ->
                            currentState.copy(signInError = "Registration cancelled. Please try again later")
                        }
                    }
                }.addOnFailureListener {
                    _uiState.update { currentState ->
                        currentState.copy(
                            signInError = it.message, // A Toast will show this error
                            isSignInBtnLoading = false,
                        )
                    }
                }
        }

    }
}